package me.kuak.rm.server.model;

import java.util.Date;

/**
 *
 * @author Juan Luis Cano <garfenter at adstter.com>
 */
public class Registration {

    private Rally rally;
    private Group group;
    private Date registrationDate;
    private StatusType status;

    public Rally getRally() {
        return rally;
    }

    public void setRally(Rally rally) {
        this.rally = rally;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public StatusType getStatus() {
        return status;
    }

    public void setStatus(StatusType status) {
        this.status = status;
    }

}
