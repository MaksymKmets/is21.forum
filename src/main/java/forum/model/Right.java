package forum.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Maxym on 10/23/2014.
 */
@Entity
public class Right  implements Serializable {
    private int idRight;
    private String description;
    private Role role;

    @Id
    @Column(name = "id_right")
    public int getIdRight() {
        return idRight;
    }

    public void setIdRight(int idRight) {
        this.idRight = idRight;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToOne
    @JoinColumn(name = "role_idrole")
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Right right = (Right) o;

        if (idRight != right.idRight) return false;
        if (description != null ? !description.equals(right.description) : right.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idRight;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
