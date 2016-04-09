package me.kuak.rm.server.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlTransient;
import org.eclipse.persistence.oxm.annotations.XmlInverseReference;

/**
 *
 * @author Juan Luis Cano <garfenter at adstter.com>
 */
@Entity
@Table(name = "person")
@DiscriminatorValue("person")
public class Person extends RallyObject implements Serializable {

    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "birthdate")
    private Date birthdate;
    @Column(name = "school")
    private String school;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_")
    @XmlInverseReference(mappedBy="person")
    private Group group;

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

}
