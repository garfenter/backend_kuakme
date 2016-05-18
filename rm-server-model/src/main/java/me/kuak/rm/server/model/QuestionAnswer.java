package me.kuak.rm.server.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Juan Luis Cano <garfenter at adstter.com>
 */
@Entity
@Table(name = "question_answer")
@DiscriminatorValue(value = "question-answer")
public class QuestionAnswer extends RallyObject implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "registration")
    private Registration registration;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question")
    private Question question;
    @Column(name = "answer")
    private String answer;
    @Column(name = "points")
    private Integer points;
    @Column(name = "question_answer_state")
    private QuestionAnswerState questionAnswerState;

    public Registration getRegistration() {
        return registration;
    }

    public void setRegistration(Registration registration) {
        this.registration = registration;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public QuestionAnswerState getQuestionAnswerState() {
        return questionAnswerState;
    }

    public void setQuestionAnswerState(QuestionAnswerState questionAnswerState) {
        this.questionAnswerState = questionAnswerState;
    }

}
