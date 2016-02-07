package me.kuak.rm.server.web.rs.config;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import me.kuak.rm.server.dao.AdminDao;
import me.kuak.rm.server.model.admin.EntityConfiguration;

/**
 *
 * @author Juan Luis Cano <garfenter at adstter.com>
 */
@Path("/admin")
public class AdminEndpoint {

    @EJB
    AdminDao adminDao;

    @GET
    @Path("/{name}/")
    public EntityConfiguration findConfigurationByName(@PathParam("name") String name) {
        return adminDao.findConfigurationByEntityName(name);
    }
}
