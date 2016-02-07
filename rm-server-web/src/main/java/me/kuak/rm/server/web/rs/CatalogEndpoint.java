    package me.kuak.rm.server.web.rs;

import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import me.kuak.rm.server.dao.CountryDao;
import me.kuak.rm.server.dao.RallyObjectDao;
import me.kuak.rm.server.dao.ResourceDao;
import me.kuak.rm.server.model.Country;
import me.kuak.rm.server.model.RallyObject;
import me.kuak.rm.server.model.RmResource;

/**
 *
 * @author Juan Luis Cano <garfenter at adstter.com>
 */
@Path("catalog")
public class CatalogEndpoint {

    @EJB
    private CountryDao countryDao;

    @EJB
    private RallyObjectDao rallyObjectDao;

    @EJB
    private ResourceDao resourceDao;

    @GET
    @Path("/countries")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Country> findAllCountries() {
        return countryDao.findAllCountries();
    }

    @PUT
    @Path("/countries")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Country createCountry(Country country) {
        rallyObjectDao.createRallyObject(country);
        return country;
    }

    @GET
    @Path("/assets/{type}s")
    @Produces(MediaType.APPLICATION_JSON)
    public List<RmResource> findResourcesByType(@PathParam("type") String type) {
        return resourceDao.findResourcesByType(type);
    }

    @PUT
    @Path("/assets/{type}s")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public RmResource addResource(@PathParam("type") String type, RmResource resource) {
        resource.setType(type);
        return resourceDao.createResource(resource);
    }

    @PUT
    @Path("/{type}/{id}/resources")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public RmResource addResourceToObject(@PathParam("type") String type, @PathParam("id") Integer id, RmResource resource) throws ClassNotFoundException {
        RallyObject object = rallyObjectDao.findRallyObjectById(id, Class.forName("me.kuak.rm.server.model." + type));
        object.getResources().add(resource);
        resource.setParent(object);
        resourceDao.createResource(resource);
        return resource;
    }
    
    
    @GET
    @Path("/generics/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public RallyObject findGenericObject(@PathParam("id") Integer id) {
        RallyObject object = rallyObjectDao.findRallyObjectById(id, RallyObject.class);
        return object;
    }
    
    @PUT
    @Path("/generics/{id}/resources")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public RmResource addResourceToGenericObject(@PathParam("id") Integer id, RmResource resource) {
        RallyObject object = rallyObjectDao.findRallyObjectById(id, RallyObject.class);
        resource.setParent(object);
        resourceDao.createResource(resource);
        return resource;
    }

}
