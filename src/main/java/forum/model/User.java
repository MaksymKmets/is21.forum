package forum.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Макс on 13.08.14.
 */
@Entity
@Table(name="user")
@NamedQuery(name = "findUserByEmail",query = "from User u where u.email=:email")
public class User {
    private Integer id;             //id
    private String firstName;       //name of uuser
    private String lastName;        //sename
    private String email;           //email
    private String password;        //password
    private String key;             //activation key

    private List<Role> roles;       //roles (admin, user)

    public User() {
    }

    public User( String firstName, String lastName, String email, String password,String key) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.key=key;
    }

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name = "id_user")
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }


    @Column(name = "activate_key")
    public String getKey() {return key;}
    public void setKey(String key) {this.key = key;}

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "user",cascade = CascadeType.ALL)
    public List<Role> getRoles() {
        return roles;
    }
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }


    public boolean hasRole(Role role)       //if have some role
    {
        return roles.contains(role);
    }
}
