package me.kuak.rm.server.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Juan Luis Cano <garfenter at adstter.com>
 */
@Entity
@Table(name = "rally_country")
public class RallyCountry extends RallyObject implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rally")
    private Rally rally;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country")
    private Country country;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "rallyCountry")
    private List<Question> questions;

    @XmlTransient
    public Rally getRally() {
        return rally;
    }

    public void setRally(Rally rally) {
        if (rally != null && country != null) {
            setName(rally.getName() + "_" + country.getName());
        }
        this.rally = rally;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        if (rally != null && country != null) {
            setName(rally.getName() + "_" + country.getName());
        }
        this.country = country;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

}
