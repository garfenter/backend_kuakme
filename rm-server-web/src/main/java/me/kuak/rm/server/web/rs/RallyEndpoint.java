package me.kuak.rm.server.web.rs;

import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import me.kuak.rm.server.dao.RallyDao;
import me.kuak.rm.server.dao.RallyObjectDao;
import me.kuak.rm.server.model.AccessToken;
import me.kuak.rm.server.model.Country;
import me.kuak.rm.server.model.MultipleValueQuestion;
import me.kuak.rm.server.model.Question;
import me.kuak.rm.server.model.Rally;
import me.kuak.rm.server.model.RallyCountry;
import me.kuak.rm.server.model.Registration;
import me.kuak.rm.server.svc.AuthSvc;

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
    public Question addCountryToRally(@PathParam("rallyId") Integer rallyId, @PathParam("countryId") Integer countryId, Question question) {
        RallyCountry rallyCountry = rallyDao.findRallyCountry(rallyId, countryId);
        question.setRallyCountry(rallyCountry);
        rallyCountry.getQuestions().add(question);
        return question;
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
    public Registration register(@PathParam("id") Integer id, @CookieParam("at") Cookie cookie) {
        AccessToken accessToken = authSvc.findAccessTokenByCode(cookie.getValue());
        return rallyDao.register(id, accessToken.getGroup().getId());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Rally> findActiveRallies() {
        return rallyDao.findActiveRallies();
    }

    @GET
    @Path("/{rallyId}/countries/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Country> findCountries(@PathParam("rallyId") Integer rallyId) {
        return rallyDao.findCountriesByRally(rallyId);
    }

    @GET
    @Path("/{rallyId}/countries/{countryId}/questions")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Question> findRally(@PathParam("rallyId") Integer rallyId, @PathParam("countryId") Integer countryId) {
        return rallyDao.findQuestionsByRallyIdAndCountryId(rallyId, countryId);
    }

    @GET
    @Path("/{rallyId}/countries/{countryId}/multivalue-question")
    @Produces(MediaType.APPLICATION_JSON)
    public List<MultipleValueQuestion> findMultipleValueQuestions(@PathParam("rallyId") Integer rallyId, @PathParam("countryId") Integer countryId) {
        return rallyDao.findMultipleValueQuestionsByRallyIdAndCountryId(rallyId, countryId);
    }

}
