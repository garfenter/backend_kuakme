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
import me.kuak.rm.server.dao.AdminDao;
import me.kuak.rm.server.model.admin.EntityConfiguration;

/**
 *
 * @author Juan Luis Cano <garfenter at adstter.com>
 */
@Path("config/entity-configuration")
public class EntityConfigurationEndpoint implements BaseConfigEndpoint<EntityConfiguration> {

    @EJB
    AdminDao adminDao;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public void create(EntityConfiguration t) {
        adminDao.createEntityConfiguration(t);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public List<EntityConfiguration> find(@QueryParam("position") Integer position, @QueryParam("limit") Integer limit) {
        return adminDao.findEntitiesConfiguration(position, limit);
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public void update(@PathParam("id") Integer id, EntityConfiguration t) {
        adminDao.updateEntityConfiguration(t);
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public void delete(@PathParam("id") Integer id) {
        adminDao.deleteEntityConfiguration(id);
    }

    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public EntityConfiguration findById(@PathParam("id") Integer id) {
        return adminDao.findEntityConfigurationById(id);
    }

    @GET
    @Path("/name/{name}/")
    @Consumes(MediaType.APPLICATION_JSON)
    public EntityConfiguration findByName(@PathParam("name") String name) {
        return adminDao.findConfigurationByEntityName(name);
    }
}
