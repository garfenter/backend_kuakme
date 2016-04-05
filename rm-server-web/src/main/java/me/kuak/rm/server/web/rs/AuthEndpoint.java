package me.kuak.rm.server.web.rs;

import java.net.URI;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.validation.ValidationException;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;
import me.kuak.rm.server.model.AccessToken;
import me.kuak.rm.server.svc.AuthSvc;
import me.kuak.rm.server.svc.QuestionSvc;

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
    @Context
    UriInfo uri;

    @POST
    @Path("authenticate")
    @Produces(MediaType.APPLICATION_JSON)
    public Response authenticate(@FormParam("user") String user, @FormParam("password") String password) {
        try {
            AccessToken token = authSvc.authenticate(user, password);
            //NewCookie cookie = new NewCookie("at", token.getToken(), "/", uri.getBaseUri().getHost(), "No comment", 360000, false);
            NewCookie cookie = new NewCookie("at", token.getToken(), "/", "jirolabs.io", "No comment", 360000, false);
            return Response.temporaryRedirect(new URI("/auth/welcome.html")).cookie(cookie).build();
        } catch (ValidationException v) {
            return Response.status(Status.FORBIDDEN).build();
        } catch (Throwable t) {
            return Response.status(Status.FORBIDDEN).build();
        }
    }

    @POST
    @Path("logoff")
    @Produces(MediaType.APPLICATION_JSON)
    public Response logoff() {
        try {
            return Response.ok().cookie(new NewCookie("at", "")).build();
        } catch (ValidationException v) {
            return Response.status(Status.FORBIDDEN).build();
        } catch (Throwable t) {
            return Response.status(Status.FORBIDDEN).build();
        }
    }

}
