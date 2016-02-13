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
import me.kuak.rm.server.dao.ResourceDao;
import me.kuak.rm.server.model.RmResource;

/**
 *
 * @author Juan Luis Cano <garfenter at adstter.com>
 */
@Path("config/resource")
public class ResourceEndPoint implements BaseConfigEndpoint<RmResource> {

    @EJB
    ResourceDao resourceDao;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public void create(RmResource t) {
        resourceDao.createResourceObject(t);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public List<RmResource> find(@QueryParam("position") Integer position, @QueryParam("limit") Integer limit) {
        return (List<RmResource>) resourceDao.findResources(position, limit);
    }

    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public RmResource findById(@PathParam("id") Integer id) {
        return (RmResource) resourceDao.findResourceById(id, RmResource.class);
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public void update(@PathParam("id") Integer id, RmResource t) {
        resourceDao.updateResource(t);
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public void delete(@PathParam("id") Integer id) {
        RmResource obj = resourceDao.findResourceById(id, RmResource.class);
        resourceDao.updateResource(obj);
    }
}
