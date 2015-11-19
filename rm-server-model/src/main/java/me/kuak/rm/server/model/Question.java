package me.kuak.rm.server.model;

import java.util.List;

/**
 *
 * @author Juan Luis Cano <garfenter at adstter.com>
 */
public class Question {

    private Integer id;
    private String plainText;
    private String htmlText;
    private List<RmResource> resources;
    private Integer maxScore;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public List<RmResource> getResources() {
        return resources;
    }

    public void setResources(List<RmResource> resources) {
        this.resources = resources;
    }

}
