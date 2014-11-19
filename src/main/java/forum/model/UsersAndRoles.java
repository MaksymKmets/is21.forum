package forum.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Maxym on 10/23/2014.
 */
@Entity
@Table(name = "role_has_user", schema = "", catalog = "course_work")
public class UsersAndRoles implements Serializable{
    private int roleIdrole;
    private int userIduser;
    private Role roleByRoleIdrole;

    @Id
    @Column(name = "role_idrole")
    public int getRoleIdrole() {
        return roleIdrole;
    }

    public void setRoleIdrole(int roleIdrole) {
        this.roleIdrole = roleIdrole;
    }

    @Id
    @Column(name = "user_iduser")
    public int getUserIduser() {
        return userIduser;
    }

    public void setUserIduser(int userIduser) {
        this.userIduser = userIduser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersAndRoles that = (UsersAndRoles) o;

        if (roleIdrole != that.roleIdrole) return false;
        if (userIduser != that.userIduser) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = roleIdrole;
        result = 31 * result + userIduser;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "role_idrole", referencedColumnName = "idrole", nullable = false)
    public Role getRoleByRoleIdrole() {
        return roleByRoleIdrole;
    }

    public void setRoleByRoleIdrole(Role roleByRoleIdrole) {
        this.roleByRoleIdrole = roleByRoleIdrole;
    }
}
