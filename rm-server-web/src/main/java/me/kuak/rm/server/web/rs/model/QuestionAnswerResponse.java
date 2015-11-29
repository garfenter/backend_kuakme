package me.kuak.rm.server.web.rs.model;

import me.kuak.rm.server.model.QuestionAnswer;

/**
 *
 * @author Juan Luis Cano <garfenter at adstter.com>
 */
public class QuestionAnswerResponse {

    private QuestionAnswer source;
    private String redirectTo;
    private Integer nextCountry;

    public QuestionAnswer getSource() {
        return source;
    }

    public void setSource(QuestionAnswer source) {
        this.source = source;
    }

    public String getRedirectTo() {
        return redirectTo;
    }

    public void setRedirectTo(String redirectTo) {
        this.redirectTo = redirectTo;
    }

    public QuestionAnswerResponse(QuestionAnswer source, String redirectTo) {
        this.source = source;
        this.redirectTo = redirectTo;
    }

    public QuestionAnswerResponse(QuestionAnswer source) {
        this.source = source;
    }

    public QuestionAnswerResponse(QuestionAnswer source, Integer nextCountry) {
        this.source = source;
        this.nextCountry = nextCountry;
    }

    public Integer getNextCountry() {
        return nextCountry;
    }

    public void setNextCountry(Integer nextCountry) {
        this.nextCountry = nextCountry;
    }

}
