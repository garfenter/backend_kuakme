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
import me.kuak.rm.server.model.Country;
import me.kuak.rm.server.model.RallyObject;
import me.kuak.rm.server.model.StateType;

/**
 *
 * @author Juan Luis Cano <garfenter at adstter.com>
 */
@Path("config/country")
public class CountryEndpoint implements BaseConfigEndpoint<Country> {

    @EJB
    RallyObjectDao rallyObjectDao;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public void create(Country t) {
        rallyObjectDao.createRallyObject(t);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public List<Country> find(@QueryParam("position") Integer position, @QueryParam("limit") Integer limit) {
        return (List<Country>) rallyObjectDao.findRallyObjectByClass(position, limit, Country.class);
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public void update(@PathParam("id") Integer id, Country t) {
        rallyObjectDao.updateRallyObject(t);
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public void delete(@PathParam("id") Integer id) {
        RallyObject obj = rallyObjectDao.findRallyObjectById(id, Country.class);
        obj.setStatus(StateType.INACTIVE);
        rallyObjectDao.updateRallyObject(obj);
    }

    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public Country findById(@PathParam("id") Integer id) {
        return (Country) rallyObjectDao.findRallyObjectById(id, Country.class);
    }

}
