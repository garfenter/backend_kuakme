package me.kuak.rm.server.web.rs.config;

import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import me.kuak.rm.server.dao.AdminDao;
import me.kuak.rm.server.model.admin.FieldConfiguration;

/**
 *
 * @author Juan Luis Cano <garfenter at adstter.com>
 */
@Path("config/field-configuration")
public class FieldConfigurationEndPoint implements BaseConfigEndpoint<FieldConfiguration> {

    @EJB
    private AdminDao adminDao;
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public void create(FieldConfiguration t) {
        adminDao.createFieldConfiguration(t);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public List<FieldConfiguration> find(Integer position, Integer limit) {
        return adminDao.findFieldsConfiguration(position, limit);
    }
    
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public void update(Integer id, FieldConfiguration t) {
        adminDao.updateFieldConfiguration(t);
    }
    
    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public void delete(Integer id) {
        adminDao.deleteFieldConfiguration(id);
    }

}
