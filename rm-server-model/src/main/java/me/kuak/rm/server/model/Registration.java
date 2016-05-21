package me.kuak.rm.server.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.eclipse.persistence.oxm.annotations.XmlInverseReference;

/**
 *
 * @author Juan Luis Cano <garfenter at adstter.com>
 */
@Table(name = "registration")
@Entity
public class Registration implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rally")
    private Rally rally;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "group_")
    @XmlInverseReference(mappedBy="person")
    private Group group;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "registration_date")
    private Date registrationDate;
    @Column(name = "status")
    private StatusType status;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "registration")
    private List<RegistrationCountry> registrationCountries;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "selected_country")
    private Country selectedCountry;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<RegistrationCountry> getRegistrationCountries() {
        if(registrationCountries == null){
            registrationCountries = new ArrayList<>();
        }
        return registrationCountries;
    }

    public void setRegistrationCountries(List<RegistrationCountry> registrationCountries) {
        this.registrationCountries = registrationCountries;
    }

    public Country getSelectedCountry() {
        return selectedCountry;
    }

    public void setSelectedCountry(Country selectedCountry) {
        this.selectedCountry = selectedCountry;
    }

}
