package me.kuak.rm.server.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Juan Luis Cano <garfenter at adstter.com>
 */
@Entity
@Table(name = "RALLY_COUNTRY")
public class RallyCountry extends RallyObject implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rally")
    private Rally rally;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country")
    private Country country;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "rallyCountry")
    private List<Question> questions;
    
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

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

}
