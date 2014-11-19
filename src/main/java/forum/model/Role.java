package forum.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Maxym on 10/23/2014.
 */
@Entity
public class Role  implements Serializable {
    private int idrole;
    private RoleName name;
    private List<Right> rights;

    public Role() {
    }

    public Role(RoleName name) {
        this.name = name;
    }

    @Id
    @Column(name = "idrole")
    public int getIdrole() {
        return idrole;
    }

    public void setIdrole(int idrole) {
        this.idrole = idrole;
    }

    @Basic
    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    public RoleName getName() {
        return name;
    }

    public void setName(RoleName name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "role")
    public List<Right> getRights() {
        return rights;
    }

    public void setRights(List<Right> rights) {
        this.rights = rights;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        if (idrole != role.idrole) return false;
        if (name != null ? !name.equals(role.name) : role.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idrole;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
