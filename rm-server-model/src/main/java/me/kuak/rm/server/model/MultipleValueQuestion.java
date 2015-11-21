package me.kuak.rm.server.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Juan Luis Cano <garfenter at adstter.com>
 */
@Entity
@Table(name = "multople_value_question")
@DiscriminatorValue("multiple-value-question")
public class MultipleValueQuestion extends Question {

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "question")
    public List<MultipleValueAnswer> posibleAnswers;

    public List<MultipleValueAnswer> getPosibleAnswers() {
        return posibleAnswers;
    }

    public void setPosibleAnswers(List<MultipleValueAnswer> posibleAnswers) {
        this.posibleAnswers = posibleAnswers;
    }

}
