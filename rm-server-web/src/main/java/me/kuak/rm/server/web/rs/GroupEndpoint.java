package me.kuak.rm.server.web.rs;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import me.kuak.rm.server.model.AccessToken;
import me.kuak.rm.server.model.Group;
import me.kuak.rm.server.svc.AuthSvc;
import me.kuak.rm.server.svc.GroupSvc;

/**
 *
 * @author Juan Luis Cano <garfenter at adstter.com>
 */
@Path("groups")
@Stateless
public class GroupEndpoint {

    @EJB
    private GroupSvc groupSvc;

    @EJB
    private AuthSvc authSvc;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Group createGroup(Group group) {
        return groupSvc.createGroup(group);
    }

    @GET
    @Path("/test")
    @Produces(MediaType.TEXT_PLAIN)
    public String test() {
        return "OK";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Group> get() {
        return groupSvc.findAll();
    }

    @GET
    @Path("/0/")
    @Produces(MediaType.APPLICATION_JSON)
    public Group findGroupById(@CookieParam("at") Cookie cookie) {
        if(cookie != null){
            AccessToken accessToken = authSvc.findAccessTokenByCode(cookie.getValue());
            return accessToken.getGroup();
        }else{
            return null;
        }
    }

}
