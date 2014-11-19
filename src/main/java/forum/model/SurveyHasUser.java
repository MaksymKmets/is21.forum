package forum.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Maxym on 10/23/2014.
 */
@Entity
@Table(name = "survey_has_user", schema = "", catalog = "course_work")
public class SurveyHasUser implements Serializable{

    private Integer answer;
    private Survey surveyBySurveyIdsurvey;
    private User userByUserIduser;
    private Integer id;
    @Id
    @GeneratedValue
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    @Basic
    @Column(name = "answer")
    public Integer getAnswer() {
        return answer;
    }

    public void setAnswer(Integer answer) {
        this.answer = answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SurveyHasUser that = (SurveyHasUser) o;

        if (id != that.id) return false;
        if (answer != null ? !answer.equals(that.answer) : that.answer != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + id;
        result = 31 * result + (answer != null ? answer.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "survey_idsurvey", referencedColumnName = "idsurvey", nullable = false )
    public Survey getSurveyBySurveyIdsurvey() {
        return surveyBySurveyIdsurvey;
    }

    public void setSurveyBySurveyIdsurvey(Survey surveyBySurveyIdsurvey) {
        this.surveyBySurveyIdsurvey = surveyBySurveyIdsurvey;
    }

    @ManyToOne
    @JoinColumn(name = "user_iduser", referencedColumnName = "iduser", nullable = false)
    public User getUserByUserIduser() {
        return userByUserIduser;
    }

    public void setUserByUserIduser(User userByUserIduser) {
        this.userByUserIduser = userByUserIduser;
    }
}
