package me.kuak.rm.server.model;

import java.util.List;

/**
 *
 * @author Juan Luis Cano <garfenter at adstter.com>
 */
public class Person {

    private String name;
    private String description;
    private String password;
    private List<RmResource> resources;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<RmResource> getResources() {
        return resources;
    }

    public void setResources(List<RmResource> resources) {
        this.resources = resources;
    }

}
