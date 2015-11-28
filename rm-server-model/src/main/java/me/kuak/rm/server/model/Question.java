package me.kuak.rm.server.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Juan Luis Cano <garfenter at adstter.com>
 */
@Entity
@Table(name = "QUESTION")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "TYPE")
@DiscriminatorValue("question")
public class Question extends RallyObject implements Serializable {

    @Column(name = "plain_text")
    private String plainText;
    @Column(name = "html_text")
    private String htmlText;
    @Column(name = "max_score")
    private Integer maxScore;
    @Column(name = "type")
    private String type;
    @Column(name = "input_type")
    private String inputType;
    @Column(name = "background")
    private String background;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rally_country")
    private RallyCountry rallyCountry;
    @Column(name = "position")
    private Integer position;

    public Integer getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(Integer maxScore) {
        this.maxScore = maxScore;
    }

    public String getPlainText() {
        return plainText;
    }

    public void setPlainText(String plainText) {
        this.plainText = plainText;
    }

    public String getHtmlText() {
        return htmlText;
    }

    public void setHtmlText(String htmlText) {
        this.htmlText = htmlText;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getInputType() {
        return inputType;
    }

    public void setInputType(String inputType) {
        this.inputType = inputType;
    }

    @XmlTransient
    public RallyCountry getRallyCountry() {
        return rallyCountry;
    }

    public void setRallyCountry(RallyCountry rallyCountry) {
        this.rallyCountry = rallyCountry;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

}
