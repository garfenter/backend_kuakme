package me.kuak.rm.server.web.rs.model;

import java.util.Date;
import java.util.List;
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
public class RallyCountryWrapper {

    private RallyCountry rallyCountry;

    public RallyCountryWrapper() {
        rallyCountry = new RallyCountry();
    }

    public Rally getRally() {
        return rallyCountry.getRally();
    }

    public void setRally(Rally rally) {
        rallyCountry.setRally(rally);
    }

    public Country getCountry() {
        return rallyCountry.getCountry();
    }

    public void setCountry(Country country) {
        rallyCountry.setCountry(country);
    }

    public List<Question> getQuestions() {
        return rallyCountry.getQuestions();
    }

    public void setQuestions(List<Question> questions) {
        rallyCountry.setQuestions(questions);
    }

    public List<RmResource> getResources() {
        return rallyCountry.getResources();
    }

    public void setResources(List<RmResource> resources) {
        rallyCountry.setResources(resources);
    }

    public Integer getId() {
        return rallyCountry.getId();
    }

    public void setId(Integer id) {
        rallyCountry.setId(id);
    }

    public String getName() {
        return rallyCountry.getName();
    }

    public void setName(String name) {
        rallyCountry.setName(name);
    }

    public String getDescription() {
        return rallyCountry.getDescription();
    }

    public void setDescription(String description) {
        rallyCountry.setDescription(description);
    }

    public String getType() {
        return rallyCountry.getType();
    }

    public void setType(String type) {
        rallyCountry.setType(type);
    }

    public StatusType getStatus() {
        return rallyCountry.getStatus();
    }

    public void setStatus(StatusType status) {
        rallyCountry.setStatus(status);
    }

    public Date getCreationDate() {
        return rallyCountry.getCreationDate();
    }

    public void setCreationDate(Date creationDate) {
        rallyCountry.setCreationDate(creationDate);
    }

    public RallyCountry getRallyCountry() {
        return rallyCountry;
    }

}
