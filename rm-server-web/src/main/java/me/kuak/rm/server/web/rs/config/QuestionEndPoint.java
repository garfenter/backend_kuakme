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
import me.kuak.rm.server.model.Question;
import me.kuak.rm.server.model.RallyObject;
import me.kuak.rm.server.model.StatusType;

/**
 *
 * @author Juan Luis Cano <garfenter at adstter.com>
 */
@Path("config/question")
public class QuestionEndPoint implements BaseConfigEndpoint<Question> {

    @EJB
    RallyObjectDao rallyObjectDao;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public void create(Question t) {
        rallyObjectDao.createRallyObject(t);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public List<Question> find(@QueryParam("position") Integer position, @QueryParam("limit") Integer limit) {
        return (List<Question>) rallyObjectDao.findRallyObjectByClass(position, limit, Question.class);

    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public void update(@PathParam("id") Integer id, Question t) {
        rallyObjectDao.updateRallyObject(t);
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public void delete(@PathParam("id") Integer id) {
        RallyObject obj = rallyObjectDao.findRallyObjectById(id, Question.class);
        obj.setStatus(StatusType.INACTIVE);
        rallyObjectDao.updateRallyObject(obj);
    }

    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public Question findById(@PathParam("id") Integer id) {
        return (Question) rallyObjectDao.findRallyObjectById(id, Question.class);
    }
}
