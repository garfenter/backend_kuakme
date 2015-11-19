package me.kuak.rm.server.model;

import java.util.List;

/**
 *
 * @author Juan Luis Cano <garfenter at adstter.com>
 */
public class Country {

    private Integer id;
    private String name;
    private List<RmResource> resources;

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

    public List<RmResource> getResources() {
        return resources;
    }

    public void setResources(List<RmResource> resources) {
        this.resources = resources;
    }

}
