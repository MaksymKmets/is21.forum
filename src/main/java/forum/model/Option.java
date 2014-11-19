package forum.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by Maxym on 10/23/2014.
 */
@Entity
public class Option implements Serializable {
    private Integer idoption;
    private String content;
    private Timestamp number;
    private int surveyIdsurvey;
    private Survey surveyBySurveyIdsurvey;

    @Id
    @Column(name = "idoption")
    public Integer getIdoption() {
        return idoption;
    }

    public void setIdoption(Integer idoption) {
        this.idoption = idoption;
    }






    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "number")
    public Timestamp getNumber() {
        return number;
    }

    public void setNumber(Timestamp number) {
        this.number = number;
    }

    @Id
    @Column(name = "survey_idsurvey")
    public int getSurveyIdsurvey() {
        return surveyIdsurvey;
    }

    public void setSurveyIdsurvey(int surveyIdsurvey) {
        this.surveyIdsurvey = surveyIdsurvey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Option option = (Option) o;

        if (idoption != option.idoption) return false;
        if (surveyIdsurvey != option.surveyIdsurvey) return false;
        if (content != null ? !content.equals(option.content) : option.content != null) return false;
        if (number != null ? !number.equals(option.number) : option.number != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idoption;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + surveyIdsurvey;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "survey_idsurvey", referencedColumnName = "idsurvey", nullable = false)
    public Survey getSurveyBySurveyIdsurvey() {
        return surveyBySurveyIdsurvey;
    }

    public void setSurveyBySurveyIdsurvey(Survey surveyBySurveyIdsurvey) {
        this.surveyBySurveyIdsurvey = surveyBySurveyIdsurvey;
    }
}
