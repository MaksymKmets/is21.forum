package forum.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Maxym on 10/23/2014.
 */
@Entity
public class Shedule  implements Serializable {
    private int idshedule;
    private String adress;

    @Id
    @Column(name = "idshedule")
    public int getIdshedule() {
        return idshedule;
    }

    public void setIdshedule(int idshedule) {
        this.idshedule = idshedule;
    }

    @Basic
    @Column(name = "adress")
    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Shedule shedule = (Shedule) o;

        if (idshedule != shedule.idshedule) return false;
        if (adress != null ? !adress.equals(shedule.adress) : shedule.adress != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idshedule;
        result = 31 * result + (adress != null ? adress.hashCode() : 0);
        return result;
    }
}
