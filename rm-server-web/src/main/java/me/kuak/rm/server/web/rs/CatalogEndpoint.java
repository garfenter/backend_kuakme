package me.kuak.rm.server.web.rs;

import java.util.ArrayList;
import java.util.Date;
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
import me.kuak.rm.server.model.AnimatedResource;
import me.kuak.rm.server.model.Country;
import me.kuak.rm.server.model.Question;
import me.kuak.rm.server.model.Rally;
import me.kuak.rm.server.model.RallyCountry;
import me.kuak.rm.server.model.RallyObject;
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
        try {
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
                question1.setPosition(1);
                question1.setRallyCountry(rallyCountry);
                question1.setBackground("day");
                question1.setResources(createResourcesStage1(question1));
                rallyCountry.getQuestions().add(question1);
                Question question2 = new Question();
                question2.setCreationDate(new Date());
                question2.setPlainText("¿Cuantos lagos hay en ${country.name}?");
                question2.setInputType("input");
                question2.setMaxScore(10);
                question2.setStatus(StatusType.ACTIVE);
                question2.setPosition(2);
                question2.setRallyCountry(rallyCountry);
                question2.setBackground("night");
                question2.setResources(createResourcesStage2(question2));
                rallyCountry.getQuestions().add(question2);
                Question question3 = new Question();
                question3.setCreationDate(new Date());
                question3.setPlainText("¿Cual es el ave nacional de ${country.name}? Envia una foto");
                question3.setInputType("input");
                question3.setMaxScore(10);
                question3.setStatus(StatusType.ACTIVE);
                question3.setPosition(3);
                question3.setRallyCountry(rallyCountry);
                question3.setBackground("day");
                question3.setResources(createResourcesStage1(question3));
                rallyCountry.getQuestions().add(question3);
            }
            rallyObjectDao.createRallyObject(rally);
            return rally;
        } catch (Throwable t) {
            t.printStackTrace();
            return null;
        }
    }

    public List<AnimatedResource> createResourcesStage1(RallyObject parent) {
        List<AnimatedResource> result = new ArrayList<>();
        result.add(createResource("/assets/images/nube.png", 150, 15, 1, 150, 100, "animate-top-down", parent));
        result.add(createResource("/assets/images/nube.png", 250, 150, 2, 150, 100, "animate-top-down", parent));
        result.add(createResource("/assets/images/nube.png", 550, 150, 3, 150, 100, "animate-top-down", parent));
        result.add(createResource("/assets/images/nube.png", 600, 150, 1, 150, 100, "animate-top-down", parent));
        result.add(createResource("/assets/images/sol.png", 600, 150, 1, 200, 200, "animate-left-right", parent));
        result.add(createResource("/assets/images/montanas.png", 600, 150, 1, 1280, 300, "animate-top-down", parent));
        return result;
    }
    
    public List<AnimatedResource> createResourcesStage2(RallyObject parent) {
        List<AnimatedResource> result = new ArrayList<>();
        result.add(createResource("/assets/images/nube.png", 150, 15, 1, 150, 100, "animate-top-down", parent));
        result.add(createResource("/assets/images/nube.png", 250, 150, 2, 150, 100, "animate-top-down", parent));
        result.add(createResource("/assets/images/nube.png", 550, 150, 3, 150, 100, "animate-top-down", parent));
        result.add(createResource("/assets/images/nube.png", 600, 150, 1, 150, 100, "animate-top-down", parent));
        result.add(createResource("/assets/images/luna.png", 600, 150, 1, 200, 200, "animate-left-right", parent));
        result.add(createResource("/assets/images/edificios.png", 600, 150, 1, 1280, 300, "animate-top-down", parent));
        return result;
    }

    public AnimatedResource createResource(String url, Integer posx, Integer posy, Integer posz, Integer width, Integer height, String animation, RallyObject parent) {
        AnimatedResource animatedResource = new AnimatedResource();
        animatedResource.setAnimation(animation);
        animatedResource.setDownloadUrl(url);
        animatedResource.setPosx(posx);
        animatedResource.setPosy(posy);
        animatedResource.setPosz(posz);
        animatedResource.setWidth(width);
        animatedResource.setHeight(height);
        animatedResource.setParent(parent);
        return animatedResource;
    }

}
