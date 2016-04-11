package me.kuak.rm.server.web.rs.model;

import java.util.List;
import me.kuak.rm.server.model.Country;

/**
 *
 * @author Juan Luis Cano <garfenter at adstter.com>
 */
public class RegistrationInfo {

    private String groupId;
    private String rallyId;
    private List<Country> selectedCountries;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getRallyId() {
        return rallyId;
    }

    public void setRallyId(String rallyId) {
        this.rallyId = rallyId;
    }

    public List<Country> getSelectedCountries() {
        return selectedCountries;
    }

    public void setSelectedCountries(List<Country> selectedCountries) {
        this.selectedCountries = selectedCountries;
    }

}
