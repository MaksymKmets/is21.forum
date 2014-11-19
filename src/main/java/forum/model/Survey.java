package forum.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

/**
 * Created by Maxym on 10/23/2014.
 */
@Entity
public class Survey  implements Serializable {
    private int idsurvey;
    private String question;
    private Timestamp date;
    private List<Message> messages;
    private Collection<Option> optionsByIdsurvey;
    private Collection<SurveyHasUser> surveyHasUsersByIdsurvey;

    @Id
    @Column(name = "idsurvey")
    public int getIdsurvey() {
        return idsurvey;
    }

    public void setIdsurvey(int idsurvey) {
        this.idsurvey = idsurvey;
    }

    @Basic
    @Column(name = "question")
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @Basic
    @Column(name = "date")
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @ManyToMany
    @JoinTable(name="survey_has_message",joinColumns = @JoinColumn(name = "survey_idsurvey")
            ,inverseJoinColumns = @JoinColumn(name = "message_idmessage"))
    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Survey survey = (Survey) o;

        if (idsurvey != survey.idsurvey) return false;
        if (date != null ? !date.equals(survey.date) : survey.date != null) return false;
        if (question != null ? !question.equals(survey.question) : survey.question != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idsurvey;
        result = 31 * result + (question != null ? question.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }


    @OneToMany( fetch = FetchType.LAZY, mappedBy = "surveyBySurveyIdsurvey" , cascade=CascadeType.ALL)
    public Collection<Option> getOptionsByIdsurvey() {
        return optionsByIdsurvey;
    }

    public void setOptionsByIdsurvey(Collection<Option> optionsByIdsurvey) {
        this.optionsByIdsurvey = optionsByIdsurvey;
    }

    @OneToMany(mappedBy = "surveyBySurveyIdsurvey")
    public Collection<SurveyHasUser> getSurveyHasUsersByIdsurvey() {
        return surveyHasUsersByIdsurvey;
    }

    public void setSurveyHasUsersByIdsurvey(Collection<SurveyHasUser> surveyHasUsersByIdsurvey) {
        this.surveyHasUsersByIdsurvey = surveyHasUsersByIdsurvey;
    }
}
