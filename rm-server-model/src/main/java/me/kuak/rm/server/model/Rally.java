package me.kuak.rm.server.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Juan Luis Cano <garfenter at adstter.com>
 */
public class Rally {

    private Integer id;
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;
    private List<RmResource> resources;
    private Map<String, String> properties;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<RmResource> getResources() {
        return resources;
    }

    public void setResources(List<RmResource> resources) {
        this.resources = resources;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }

}
