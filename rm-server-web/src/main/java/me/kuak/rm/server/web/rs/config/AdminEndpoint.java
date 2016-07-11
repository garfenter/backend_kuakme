package me.kuak.rm.server.web.rs.config;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import javax.ejb.EJB;
import javax.validation.ValidationException;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import me.kuak.rm.server.dao.AdminDao;
import me.kuak.rm.server.dao.GroupDao;
import me.kuak.rm.server.model.AccessToken;
import me.kuak.rm.server.model.Group;
import me.kuak.rm.server.model.admin.EntityConfiguration;
import me.kuak.rm.server.svc.AuthSvc;

/**
 *
 * @author Juan Luis Cano <garfenter at adstter.com>
 */
@Path("/admin")
public class AdminEndpoint {

    @EJB
    AdminDao adminDao;
    @EJB
    AuthSvc authSvc;
    @Context
    UriInfo uri;
    @EJB
    GroupDao groupDao;

    @GET
    @Path("/{name}/")
    public EntityConfiguration findConfigurationByName(@PathParam("name") String name) {
        return adminDao.findConfigurationByEntityName(name);
    }

    @POST
    @Path("authenticate")
    @Produces(MediaType.APPLICATION_JSON)
    public Response authenticate(@FormParam("user") String user, @FormParam("password") String password) throws URISyntaxException {
        try {
            AccessToken token = authSvc.authenticate(user, password, "admin");

            //NewCookie cookie = new NewCookie("at", token.getToken(), "/", uri.getBaseUri().getHost(), "No comment", 360000, false);
            NewCookie cookie = new NewCookie("atAdmin", token.getToken(), "/", "rallymatematico.com", "No comment", 360000, false);
            return Response.temporaryRedirect(new URI("/rm-admin/index.html")).cookie(cookie).build();
        } catch (ValidationException v) {
            return Response.temporaryRedirect(new URI("/rm-admin/login.html?error=1")).build();
        } catch (Throwable t) {
            return Response.temporaryRedirect(new URI("/rm-admin/login.html?error=1")).build();
        }
    }

    @POST
    @Path("logoff")
    @Produces(MediaType.APPLICATION_JSON)
    public Response logoff() {
        try {
            return Response.temporaryRedirect(new URI("/")).cookie(new NewCookie("atAdmin", "")).build();
        } catch (ValidationException v) {
            return Response.status(Response.Status.FORBIDDEN).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/users/{role}")
    public List<Group> findByType(@QueryParam("position") Integer position, @QueryParam("limit") Integer limit, @PathParam("role") String role) {
        return groupDao.findByUserRole(position, limit, role);
    }
}
