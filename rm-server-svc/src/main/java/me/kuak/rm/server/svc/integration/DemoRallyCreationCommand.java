package me.kuak.rm.server.svc.integration;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import me.kuak.rm.server.model.Country;
import me.kuak.rm.server.model.MultipleValueAnswer;
import me.kuak.rm.server.model.MultipleValueQuestion;
import me.kuak.rm.server.model.Question;
import me.kuak.rm.server.model.Rally;
import me.kuak.rm.server.model.RallyCountry;
import me.kuak.rm.server.model.RallyObject;
import me.kuak.rm.server.model.RmResource;
import me.kuak.rm.server.model.StateType;

/**
 *
 * @author Juan Luis Cano <garfenter at adstter.com>
 */
public class DemoRallyCreationCommand extends InitializationCommand {

    public DemoRallyCreationCommand(EntityManager em) {
        super(em);
    }

    @Override
    void execute() {
        Rally rally = new Rally();
        rally.setCreationDate(new Date());
        rally.setDescription("Rally de pruebas");
        rally.setName("Rally " + new GregorianCalendar().get(GregorianCalendar.YEAR));
        rally.setStartDate(new Date());
        rally.setStatus(StateType.ACTIVE);
        rally.setRallyCountries(new ArrayList<RallyCountry>());
        for (Country country : findAllCountries()) {
            RallyCountry rallyCountry = new RallyCountry();
            rallyCountry.setRally(rally);
            rallyCountry.setCountry(country);
            rallyCountry.setQuestions(new ArrayList<Question>());
            rally.getRallyCountries().add(rallyCountry);
            Question question1 = new Question();
            question1.setCreationDate(new Date());
            question1.setPlainText(replaceCountry("¿Cual es el volcan mas grande de ${country.name}? Sube una foto", country.getName()));
            question1.setName(question1.getPlainText());
            question1.setInputType("file");
            question1.setMaxScore(10);
            question1.setStatus(StateType.ACTIVE);
            question1.setPosition(1);
            question1.setRallyCountry(rallyCountry);
            question1.setBackground("day");
            question1.setResources(createResourcesStage1(question1));
            rallyCountry.getQuestions().add(question1);
            Question question2 = new Question();
            question2.setCreationDate(new Date());
            question2.setPlainText(replaceCountry("¿Cuantos lagos hay en ${country.name}?", country.getName()));
            question2.setName(question1.getPlainText());
            question2.setInputType("input");
            question2.setMaxScore(10);
            question2.setStatus(StateType.ACTIVE);
            question2.setPosition(2);
            question2.setRallyCountry(rallyCountry);
            question2.setBackground("night");
            question2.setResources(createResourcesStage2(question2));
            rallyCountry.getQuestions().add(question2);
            Question question3 = new Question();
            question3.setCreationDate(new Date());
            question3.setPlainText(replaceCountry("¿Cual es el ave nacional de ${country.name}? Envia una foto", country.getName()));
            question3.setName(question3.getPlainText());
            question3.setInputType("input");
            question3.setMaxScore(10);
            question3.setStatus(StateType.ACTIVE);
            question3.setPosition(3);
            question3.setRallyCountry(rallyCountry);
            question3.setBackground("day");
            question3.setResources(createResourcesStage1(question3));
            rallyCountry.getQuestions().add(question3);
            rallyCountry.getQuestions().add(createMultipleValueQuestions("¿Cual es la capital de ${country.name}?", new String[]{"Guatemala", "Mexico DF", "Brasilia", "Panama"}, 2, rallyCountry));
            rallyCountry.getQuestions().add(createMultipleValueQuestions("¿Cual es el ave nacional de ${country.name}?", new String[]{"Quetzal", "Aguila real", "Turus", "Aguila Arpia"}, 2, rallyCountry));
            rallyCountry.getQuestions().add(createMultipleValueQuestions("¿Cual es la comidia tipica de ${country.name}?", new String[]{"Pepian", "Tacos", "Viajejet", "Sancocho"}, 2, rallyCountry));
            rallyCountry.getQuestions().add(createMultipleValueQuestions("¿Cual es el volcan mas alto de ${country.name}?", new String[]{"Tajumulco", "Popocatepe", "Citlaltépetl", "Barú"}, 2, rallyCountry));
        }
        rally.setStatus(StateType.ACTIVE);
        rally.setCreationDate(new Date());
        getEntityManager().persist(rally);
        complete();
    }

    @Override
    String getName() {
        return "demo-rally-creation";
    }

    @Override
    Integer getVersion() {
        return 1;
    }

    private Iterable<Country> findAllCountries() {
        TypedQuery<Country> qry = getEntityManager().createQuery("SELECT c FROM Country c WHERE c.status = :status", Country.class);
        qry.setParameter("status", StateType.ACTIVE);
        return qry.getResultList();
    }

    public List<RmResource> createResourcesStage1(RallyObject parent) {
        List<RmResource> result = new ArrayList<>();
        result.add(createResource("/assets/images/nube.png", 150, 15, 1, 150, 100, "animate-top-down", parent));
        result.add(createResource("/assets/images/nube.png", 250, 150, 2, 150, 100, "animate-top-down", parent));
        result.add(createResource("/assets/images/nube.png", 550, 150, 3, 150, 100, "animate-top-down", parent));
        result.add(createResource("/assets/images/nube.png", 600, 150, 1, 150, 100, "animate-top-down", parent));
        result.add(createResource("/assets/images/sol.png", 600, 150, 1, 200, 200, "animate-left-right", parent));
        result.add(createResource("/assets/images/montanas.png", 0, 350, 1, 1280, 300, "animate-top-down", parent));
        return result;
    }

    public List<RmResource> createResourcesStage2(RallyObject parent) {
        List<RmResource> result = new ArrayList<>();
        result.add(createResource("/assets/images/nube.png", 150, 15, 1, 150, 100, "animate-top-down", parent));
        result.add(createResource("/assets/images/nube.png", 250, 150, 2, 150, 100, "animate-top-down", parent));
        result.add(createResource("/assets/images/nube.png", 550, 150, 3, 150, 100, "animate-top-down", parent));
        result.add(createResource("/assets/images/nube.png", 600, 150, 1, 150, 100, "animate-top-down", parent));
        result.add(createResource("/assets/images/luna.png", 600, 150, 1, 200, 200, "animate-left-right", parent));
        result.add(createResource("/assets/images/edificios.png", 0, 350, 1, 1280, 300, "animate-top-down", parent));
        return result;
    }

    public RmResource createResource(String url, Integer posx, Integer posy, Integer posz, Integer width, Integer height, String animation, RallyObject parent) {
        RmResource animatedResource = new RmResource();
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

    public String replaceCountry(String text, String country) {
        return text.replace("${country.name}", country);
    }

    public MultipleValueQuestion createMultipleValueQuestions(String question, String[] answers, Integer correctNumber, RallyCountry country) {
        MultipleValueQuestion result = new MultipleValueQuestion();
        result.setPlainText(replaceCountry(question, country.getCountry().getName()));
        result.setName(result.getPlainText());
        result.setPosibleAnswers(new ArrayList<MultipleValueAnswer>());
        result.setRallyCountry(country);
        for (int i = 0; i < answers.length; i++) {
            MultipleValueAnswer mva = new MultipleValueAnswer();
            mva.setText(answers[i]);
            mva.setValue(answers[i]);
            mva.setCorrect(i == correctNumber);
            mva.setQuestion(result);
            result.getPosibleAnswers().add(mva);
        }
        return result;
    }

}
