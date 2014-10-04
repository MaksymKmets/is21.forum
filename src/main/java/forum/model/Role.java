package forum.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Макс on 13.08.14.
 */
@Entity
@Table(name="roles")
public class Role implements Serializable{
    private Integer id;         //id
    private String roleCase;    // Role (user or admin)
    private User user;          // user object
    public Role() {
    }

    public Role(String roleCase, User user) {
        this.roleCase = roleCase;
        this.user = user;
    }

    public Role(Integer id, String roleCase, User user) {
        this.id = id;
        this.roleCase = roleCase;
        this.user = user;
    }

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name = "role_id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "role_name")
    public String getRoleCase() {
        return roleCase;
    }

    public void setRoleCase(String roleCase) {
        this.roleCase = roleCase;
    }
    @ManyToOne(fetch = FetchType.LAZY)      //many to one with user table
    @JoinColumn(name = "user_id",nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
