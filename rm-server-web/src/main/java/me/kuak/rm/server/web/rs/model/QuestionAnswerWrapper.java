package me.kuak.rm.server.web.rs.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import me.kuak.rm.server.model.Question;
import me.kuak.rm.server.model.QuestionAnswer;
import me.kuak.rm.server.model.QuestionAnswerState;
import me.kuak.rm.server.model.Registration;
import me.kuak.rm.server.model.RmResource;
import me.kuak.rm.server.model.StatusType;

/**
 *
 * @author Juan Luis Cano <garfenter at adstter.com>
 */
public class QuestionAnswerWrapper {

    private final QuestionAnswer source;
    private String user;
    private String country;

    public QuestionAnswerWrapper() {
        this.source = new QuestionAnswer();
    }

    public QuestionAnswerWrapper(QuestionAnswer source) {
        this.source = source;
        if (source.getRegistration() != null) {
            this.user = source.getRegistration().getGroup().getUser();
            this.country = source.getRegistration().getGroup().getCountry().getName();
        }
    }

    public Registration getRegistration() {
        return source.getRegistration();
    }

    public void setRegistration(Registration registration) {
        source.setRegistration(registration);
    }

    public Question getQuestion() {
        return source.getQuestion();
    }

    public void setQuestion(Question question) {
        source.setQuestion(question);
    }

    public String getAnswer() {
        return source.getAnswer();
    }

    public void setAnswer(String answer) {
        source.setAnswer(answer);
    }

    public Integer getPoints() {
        return source.getPoints();
    }

    public void setPoints(Integer points) {
        source.setPoints(points);
    }

    public QuestionAnswerState getQuestionAnswerState() {
        return source.getQuestionAnswerState();
    }

    public void setQuestionAnswerState(QuestionAnswerState questionAnswerState) {
        source.setQuestionAnswerState(questionAnswerState);
    }

    public List<RmResource> getResources() {
        return source.getResources();
    }

    public void setResources(List<RmResource> resources) {
        source.setResources(resources);
    }

    public Integer getId() {
        return source.getId();
    }

    public void setId(Integer id) {
        source.setId(id);
    }

    public String getName() {
        return source.getName();
    }

    public void setName(String name) {
        source.setName(name);
    }

    public String getDescription() {
        return source.getDescription();
    }

    public void setDescription(String description) {
        source.setDescription(description);
    }

    public String getType() {
        return source.getType();
    }

    public void setType(String type) {
        source.setType(type);
    }

    public StatusType getStatus() {
        return source.getStatus();
    }

    public void setStatus(StatusType status) {
        source.setStatus(status);
    }

    public Date getCreationDate() {
        return source.getCreationDate();
    }

    public void setCreationDate(Date creationDate) {
        source.setCreationDate(creationDate);
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public static List<QuestionAnswerWrapper> convertToQuestionsAnswersWrapper(List<QuestionAnswer> questionAnwers) {
        List<QuestionAnswerWrapper> result = new ArrayList<>();
        for (QuestionAnswer question : questionAnwers) {
            result.add(new QuestionAnswerWrapper(question));
        }
        return result;
    }

}
