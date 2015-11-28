package me.kuak.rm.server.web.rs;

import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import me.kuak.rm.server.dao.RallyDao;
import me.kuak.rm.server.dao.RallyObjectDao;
import me.kuak.rm.server.model.Country;
import me.kuak.rm.server.model.Question;
import me.kuak.rm.server.model.Rally;
import me.kuak.rm.server.model.Registration;

/**
 *
 * @author Juan Luis Cano <garfenter at adstter.com>
 */
@Path("rallies")
public class RallyEndpoint {

    @EJB
    RallyObjectDao rallyObjectDao;
    @EJB
    RallyDao rallyDao;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Rally findRally(@PathParam("id") Integer id) {
        return (Rally) rallyObjectDao.findRallyObjectById(id, Rally.class);
    }

    @POST
    @Path("/{id}/register")
    @Produces(MediaType.APPLICATION_JSON)
    public Registration register(@PathParam("id") Integer id) {
        return rallyDao.register(id, id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Rally> findActiveRallies() {
        return rallyDao.findActiveRallies();
    }

    @GET
    @Path("/{rallyId}/countries/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Country> findCountries(@PathParam("rallyId") Integer rallyId) {
        return rallyDao.findCountriesByRally(rallyId);
    }

    @GET
    @Path("/{rallyId}/countries/{countryId}/questions")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Question> findRally(@PathParam("rallyId") Integer rallyId, @PathParam("countryId") Integer countryId) {
        return rallyDao.findQuestionsByRallyIdAndCountryId(rallyId, countryId);
    }
}
