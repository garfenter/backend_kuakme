package me.kuak.rm.server.model;

import java.util.Date;

/**
 *
 * @author Juan Luis Cano <garfenter at adstter.com>
 */
public class AccessToken {

    private Date authenticationDate;
    private Date validDate;
    private String token;
    private String user;

    public Date getAuthenticationDate() {
        return authenticationDate;
    }

    public void setAuthenticationDate(Date authenticationDate) {
        this.authenticationDate = authenticationDate;
    }

    public Date getValidDate() {
        return validDate;
    }

    public void setValidDate(Date validDate) {
        this.validDate = validDate;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

}
