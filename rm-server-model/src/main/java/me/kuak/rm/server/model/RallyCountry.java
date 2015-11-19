package me.kuak.rm.server.model;

import java.util.List;

/**
 *
 * @author Juan Luis Cano <garfenter at adstter.com>
 */
public class RallyCountry {

    private Rally rally;
    private Country country;
    private List<RmResource> resources;

    public Rally getRally() {
        return rally;
    }

    public void setRally(Rally rally) {
        this.rally = rally;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public List<RmResource> getResources() {
        return resources;
    }

    public void setResources(List<RmResource> resources) {
        this.resources = resources;
    }

}
