package me.kuak.rm.server.web.rs;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.validation.ValidationException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import me.kuak.rm.server.model.Country;
import me.kuak.rm.server.model.MultipleValueQuestion;
import me.kuak.rm.server.svc.AuthSvc;
import me.kuak.rm.server.svc.QuestionSvc;
import me.kuak.rm.server.web.rs.model.RsResponse;
import static me.kuak.rm.server.web.rs.model.RsResponse.CODE_UNKNOWN_ERROR;
import static me.kuak.rm.server.web.rs.model.RsResponse.CODE_VALIDATION_ERROR;

/**
 *
 * @author Juan Luis Cano <garfenter at adstter.com>
 */
@Path("auth")
@Stateless
public class AuthEndpoint {

    @EJB
    AuthSvc authSvc;
    @EJB
    QuestionSvc questionSvc;

    @POST
    @Path("authenticate")
    @Produces(MediaType.APPLICATION_JSON)
    public RsResponse<String> authenticate(@FormParam("user") String user, @FormParam("password") String password) {
        try {
            RsResponse result = new RsResponse(authSvc.authenticate(user, password).getToken());
            result.setCode(RsResponse.CODE_OK);
            return result;
        } catch (ValidationException v) {
            RsResponse<String> result = new RsResponse<>(null);
            result.setDescription(v.getMessage());
            result.setCode(CODE_VALIDATION_ERROR);
            return result;
        } catch (Throwable t) {
            RsResponse<String> result = new RsResponse<>(null);
            result.setDescription(t.getMessage());
            result.setCode(CODE_UNKNOWN_ERROR);
            return result;
        }
    }

    @GET
    @Path("test")
    public String test() {
        MultipleValueQuestion mvq = new MultipleValueQuestion();
        mvq.setDescription("test");
        mvq.setHtmlText("test");
        mvq.setMaxScore(1);
        questionSvc.createQuestion(mvq);
        return "OK";
    }

    @GET
    @Path("test2")
    public Country test2() {
        Country tmp = new Country();
        tmp.setId(1);
        tmp.setName("Guatemala");
        tmp.setDescription("Guatemala!!!");
        return tmp;
    }

}
