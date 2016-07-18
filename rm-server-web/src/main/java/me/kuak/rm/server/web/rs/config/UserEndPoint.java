package me.kuak.rm.server.web.rs.config;

import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import me.kuak.rm.server.dao.RallyObjectDao;
import me.kuak.rm.server.model.Group;
import me.kuak.rm.server.model.StatusType;
import me.kuak.rm.server.svc.GroupSvc;

/**
 *
 * @author Juan Luis Cano <garfenter at adstter.com>
 */
@Path("config/user")
public class UserEndPoint implements BaseConfigEndpoint<Group> {

    @EJB
    RallyObjectDao rallyObjectDao;
    
    @EJB
    GroupSvc groupSvc;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public void create(Group t) {
        groupSvc.createGroup(t);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public List<Group> find(@QueryParam("position") Integer position, @QueryParam("limit") Integer limit) {
        return (List<Group>) rallyObjectDao.findRallyObjectByClass(position, limit, Group.class);
    }

    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public Group findById(@PathParam("id") Integer id) {
        return (Group) rallyObjectDao.findRallyObjectById(id, Group.class);
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public void update(@PathParam("id") Integer id, Group t) {
        rallyObjectDao.updateRallyObject(t);
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public void delete(@PathParam("id") Integer id) {
        Group obj = (Group) rallyObjectDao.findRallyObjectById(id, Group.class);
        obj.setStatus(StatusType.INACTIVE);
        rallyObjectDao.updateRallyObject(obj);
    }
}
