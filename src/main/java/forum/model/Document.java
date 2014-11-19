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
public class Document implements Serializable{
    private int iddocument;
    private String adress;
    private String name;
    private String description;

    @Id
    @Column(name = "iddocument")
    public int getIddocument() {
        return iddocument;
    }

    public void setIddocument(int iddocument) {
        this.iddocument = iddocument;
    }

    @Basic
    @Column(name = "adress")
    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Document document = (Document) o;

        if (iddocument != document.iddocument) return false;
        if (adress != null ? !adress.equals(document.adress) : document.adress != null) return false;
        if (description != null ? !description.equals(document.description) : document.description != null)
            return false;
        if (name != null ? !name.equals(document.name) : document.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = iddocument;
        result = 31 * result + (adress != null ? adress.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
