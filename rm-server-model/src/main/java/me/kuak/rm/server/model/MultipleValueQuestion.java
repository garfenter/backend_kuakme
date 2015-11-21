package me.kuak.rm.server.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Juan Luis Cano <garfenter at adstter.com>
 */
@Entity
@Table
@DiscriminatorValue("multiple-value")
public class MultipleValueQuestion extends Question {

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "posibleAnswers", referencedColumnName = "ID")
    public List<MultipleValueAnswer> posibleAnswers;

    public List<MultipleValueAnswer> getPosibleAnswers() {
        return posibleAnswers;
    }

    public void setPosibleAnswers(List<MultipleValueAnswer> posibleAnswers) {
        this.posibleAnswers = posibleAnswers;
    }

}
