package forum.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Created by Maxym on 10/23/2014.
 */
@Entity(name = "user")
public class User  implements Serializable {
    private int iduser;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String activateKey;
    private List<Role> roles;
    private Collection<SurveyHasUser> surveyHasUsersByIduser;
    private Collection<Message> messagesByIduser;

    public User() {

    }

    public User(String firstName, String lastName, String email, String password, String activateKey) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.activateKey = activateKey;
    }

    @Id
    @Column(name = "iduser")
    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    @Basic
    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "activate_key")
    public String getActivateKey() {
        return activateKey;
    }

    public void setActivateKey(String activateKey) {
        this.activateKey = activateKey;
    }

    @ManyToMany
    @JoinTable(name="role_has_user",joinColumns = @JoinColumn(name = "user_iduser")
            ,inverseJoinColumns = @JoinColumn(name = "role_idrole"))
    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (iduser != user.iduser) return false;
        if (activateKey != null ? !activateKey.equals(user.activateKey) : user.activateKey != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
        if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = iduser;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (activateKey != null ? activateKey.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "userByUserIduser",fetch = FetchType.LAZY)
    public Collection<SurveyHasUser> getSurveyHasUsersByIduser() {
        return surveyHasUsersByIduser;
    }

    public void setSurveyHasUsersByIduser(Collection<SurveyHasUser> surveyHasUsersByIduser) {
        this.surveyHasUsersByIduser = surveyHasUsersByIduser;
    }

    @OneToMany(mappedBy = "user")
    public Collection<Message> getMessagesByIduser() {
        return messagesByIduser;
    }

    public void setMessagesByIduser(Collection<Message> messagesByIduser) {
        this.messagesByIduser = messagesByIduser;
    }
}
