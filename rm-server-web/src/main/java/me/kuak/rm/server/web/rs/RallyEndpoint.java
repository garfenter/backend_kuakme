package me.kuak.rm.server.web.rs;

import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import me.kuak.rm.server.dao.RallyDao;
import me.kuak.rm.server.dao.RallyObjectDao;
import me.kuak.rm.server.dao.ResourceDao;
import me.kuak.rm.server.model.AccessToken;
import me.kuak.rm.server.model.Country;
import me.kuak.rm.server.model.MultipleValueQuestion;
import me.kuak.rm.server.model.Question;
import me.kuak.rm.server.model.QuestionAnswer;
import me.kuak.rm.server.model.Rally;
import me.kuak.rm.server.model.RallyCountry;
import me.kuak.rm.server.model.Ranking;
import me.kuak.rm.server.model.Registration;
import me.kuak.rm.server.model.RmResource;
import me.kuak.rm.server.svc.AuthSvc;
import me.kuak.rm.server.web.rs.model.QuestionAnswerResponse;
import me.kuak.rm.server.web.rs.model.RegistrationInfo;

/**
 *
 * @author Juan Luis Cano <garfenter at adstter.com>
 */
@Path("rallies")
public class RallyEndpoint {

    @EJB
    RallyObjectDao rallyObjectDao;
    @EJB
    RallyDao rallyDao;
    @EJB
    AuthSvc authSvc;
    @EJB
    ResourceDao resourceDao;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Rally createRally(Rally rally) {
        rallyObjectDao.createRallyObject(rally);
        return rally;
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{rallyId}/countries/{countryId}/add")
    public RallyCountry addCountryToRally(@PathParam("rallyId") Integer rallyId, @PathParam("countryId") Integer countryId) {
        Rally rally = findRally(rallyId);
        Country country = (Country) rallyObjectDao.findRallyObjectById(countryId, Country.class);
        RallyCountry rallyCountry = new RallyCountry();
        rallyCountry.setCountry(country);
        rallyCountry.setRally(rally);
        return rallyCountry;
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{rallyId}/countries/{countryId}/questions")
    public Question addQuestionToRallyCountry(@PathParam("rallyId") Integer rallyId, @PathParam("countryId") Integer countryId, Question question) {
        RallyCountry rallyCountry = rallyDao.findRallyCountry(rallyId, countryId);
        question.setRallyCountry(rallyCountry);
        rallyCountry.getQuestions().add(question);
        rallyObjectDao.createRallyObject(question);
        return question;
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{rallyId}/countries/{countryId}/resources")
    public RmResource addResourceToRallyCountry(@PathParam("rallyId") Integer rallyId, @PathParam("countryId") Integer countryId, RmResource resource) {
        RallyCountry rallyCountry = rallyDao.findRallyCountry(rallyId, countryId);
        resource.setParent(rallyCountry);
        rallyCountry.getResources().add(resource);
        resourceDao.createResource(resource);
        return resource;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Rally findRally(@PathParam("id") Integer id) {
        return (Rally) rallyObjectDao.findRallyObjectById(id, Rally.class);
    }

    @POST
    @Path("/{id}/register")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Registration register(@PathParam("id") Integer id, @CookieParam("at") Cookie cookie, RegistrationInfo registrationInfo) {
        AccessToken accessToken = authSvc.findAccessTokenByCode(cookie.getValue());
        return rallyDao.register(id, accessToken.getGroup().getId(), registrationInfo.getSelectedCountries());
    }
    
    @POST
    @Path("/{id}/register/countries")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Registration registerCountries(@PathParam("id") Integer id, @CookieParam("at") Cookie cookie, RegistrationInfo registrationInfo) {
        AccessToken accessToken = authSvc.findAccessTokenByCode(cookie.getValue());
        return rallyDao.registerCountries(id, accessToken.getGroup().getId(), registrationInfo.getSelectedCountries());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Rally> findActiveRallies() {
        return rallyDao.findActiveRallies();
    }

    @GET
    @Path("/{rallyId}/countries/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<RallyCountry> findCountries(@PathParam("rallyId") Integer rallyId) {
        return rallyDao.findCountriesByRally(rallyId);
    }

    @GET
    @Path("/{rallyId}/countries/{countryId}/questions")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Question> findRallyCountryQuestions(@PathParam("rallyId") Integer rallyId, @PathParam("countryId") Integer countryId) {
        return rallyDao.findQuestionsByRallyIdAndCountryId(rallyId, countryId);
    }

    @GET
    @Path("/{rallyId}/countries/{countryId}/multivalue-question")
    @Produces(MediaType.APPLICATION_JSON)
    public List<MultipleValueQuestion> findMultipleValueQuestions(@PathParam("rallyId") Integer rallyId, @PathParam("countryId") Integer countryId) {
        return rallyDao.findMultipleValueQuestionsByRallyIdAndCountryId(rallyId, countryId);
    }

    @GET
    @Path("/questions/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Question findQuestion(@PathParam("id") Integer id) {
        return rallyDao.findQuestionByQuestionId(id);
    }

    @POST
    @Path("/questions/{id}/answers")
    @Produces(MediaType.APPLICATION_JSON)
    public QuestionAnswerResponse addAnswer(@PathParam("id") Integer id, @CookieParam("at") Cookie cookie, QuestionAnswer answer) {
        AccessToken accessToken = authSvc.findAccessTokenByCode(cookie.getValue());
        QuestionAnswer qa = answer;
        for (RmResource resource : qa.getResources()) {
            resource.setParent(qa);
            qa.setAnswer("URL:" + resource.getDownloadUrl());
        }
        qa.setCreationDate(new Date());
        qa.setGroup(accessToken.getGroup());
        qa.setQuestion((Question) rallyObjectDao.findRallyObjectById(id, Question.class));
        rallyObjectDao.createRallyObject(qa);
//        List<RallyCountry> rallyCountries = qa.getQuestion().getRallyCountry().getRally().getRallyCountries();
//        Integer nextCountry = 0;
//        for (int i = 0; i < rallyCountries.size() - 1; i++) {
//            if (rallyCountries.get(i).getId().equals(qa.getQuestion().getRallyCountry().getId())) {
//                nextCountry = i + 1;
//            }
//        }
//        return new QuestionAnswerResponse(qa, rallyCountries.get(nextCountry).getId());
        return new QuestionAnswerResponse(qa);
    }

    @GET
    @Path("/questions/{id}/answers")
    @Produces(MediaType.APPLICATION_JSON)
    public List<QuestionAnswer> findQuestionAnswersByQuestionId(@PathParam("id") Integer id) {
        return rallyDao.findAnswersByQuestionId(id);
    }

    @GET
    @Path("/questions/{id}/answers/{answerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public QuestionAnswer findQuestionAnswerById(@PathParam("id") Integer id, @PathParam("answerId") Integer answerId) {
        return rallyDao.findAnswerById(answerId);
    }

    @POST
    @Path("/questions/{id}/answers/{answerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public QuestionAnswer updateAnswer(@PathParam("id") Integer id, @PathParam("answerId") Integer answerId, @CookieParam("at") Cookie cookie, QuestionAnswer answer) {
        AccessToken accessToken = authSvc.findAccessTokenByCode(cookie.getValue());
        QuestionAnswer questionAnswer = findQuestionAnswerById(id, answerId);
        questionAnswer.setPoints(answer.getPoints());
        rallyObjectDao.updateRallyObject(questionAnswer);
        return questionAnswer;
    }

    @GET
    @Path("{rallyId}/ranking")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Ranking> findRankingByRallyId(@PathParam("rallyId") Integer rallyId) {
        return rallyDao.findRankingsByRallyId(rallyId);
    }

}
