package me.kuak.rm.server.web.rs;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import me.kuak.rm.server.dao.CountryDao;
import me.kuak.rm.server.dao.RallyObjectDao;
import me.kuak.rm.server.dao.ResourceDao;
import me.kuak.rm.server.model.Country;
import me.kuak.rm.server.model.Question;
import me.kuak.rm.server.model.Rally;
import me.kuak.rm.server.model.RallyCountry;
import me.kuak.rm.server.model.RmResource;
import me.kuak.rm.server.model.StatusType;

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

    @GET
    @Path("/assets/{type}s")
    @Produces(MediaType.APPLICATION_JSON)
    public List<RmResource> findResourcesByType(@PathParam("type") String type) {
        return resourceDao.findResourcesByType(type);
    }

    @GET
    @Path("dummy/countries")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Country> createDummyCountries() {
        rallyObjectDao.createRallyObject(new Country("Guatemala"));
        rallyObjectDao.createRallyObject(new Country("Brazil"));
        rallyObjectDao.createRallyObject(new Country("Panama"));
        rallyObjectDao.createRallyObject(new Country("Mexico"));
        return countryDao.findAllCountries();
    }

    @GET
    @Path("dummy/assets")
    @Produces(MediaType.APPLICATION_JSON)
    public List<RmResource> createDummyResources() {
        resourceDao.createResource(new RmResource("/assets/images/avatars/avatar1.png", "avatar1.png", "png", "image/png", "avatar", "avatar1", "avatar1"));
        resourceDao.createResource(new RmResource("/assets/images/avatars/avatar2.png", "avatar2.png", "png", "image/png", "avatar", "avatar2", "avatar2"));
        resourceDao.createResource(new RmResource("/assets/images/avatars/avatar3.png", "avatar3.png", "png", "image/png", "avatar", "avatar3", "avatar3"));
        resourceDao.createResource(new RmResource("/assets/images/avatars/avatar4.png", "avatar4.png", "png", "image/png", "avatar", "avatar4", "avatar4"));
        resourceDao.createResource(new RmResource("/assets/images/avatars/avatar5.png", "avatar5.png", "png", "image/png", "avatar", "avatar5", "avatar5"));
        resourceDao.createResource(new RmResource("/assets/images/avatars/avatar6.png", "avatar6.png", "png", "image/png", "avatar", "avatar6", "avatar6"));
        resourceDao.createResource(new RmResource("/assets/images/avatars/avatar7.png", "avatar7.png", "png", "image/png", "avatar", "avatar7", "avatar7"));
        resourceDao.createResource(new RmResource("/assets/images/transports/transport1.png", "transport1.png", "png", "image/png", "transport", "transport1", "transport1"));
        resourceDao.createResource(new RmResource("/assets/images/transports/transport2.png", "transport2.png", "png", "image/png", "transport", "transport2", "transport2"));
        resourceDao.createResource(new RmResource("/assets/images/transports/transport3.png", "transport3.png", "png", "image/png", "transport", "transport3", "transport3"));
        return resourceDao.findResourcesByType("avatar");
    }

    @GET
    @Path("dummy/rally")
    @Produces(MediaType.APPLICATION_JSON)
    public Rally createRally() {
        Rally rally = new Rally();
        rally.setCreationDate(new Date());
        rally.setDescription("Rally de pruebas");
        rally.setName("Rally 2015");
        rally.setStartDate(new Date());
        rally.setStatus(StatusType.ACTIVE);
        rally.setRallyCountries(new ArrayList<RallyCountry>());
        for (Country country : findAllCountries()) {
            RallyCountry rallyCountry = new RallyCountry();
            rallyCountry.setRally(rally);
            rallyCountry.setCountry(country);
            rallyCountry.setQuestions(new ArrayList<Question>());
            rally.getRallyCountries().add(rallyCountry);
            Question question1 = new Question();
            question1.setCreationDate(new Date());
            question1.setPlainText("¿Cual es el volcan mas grande de ${country.name}? Sube una foto");
            question1.setInputType("file");
            question1.setMaxScore(10);
            question1.setStatus(StatusType.ACTIVE);
            
            
            
            
        }
        

        return rally;
    }
}
