package forum.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Maxym on 10/23/2014.
 */
@Entity
@Table(name = "gallery_image", schema = "", catalog = "course_work")
public class GalleryImage implements Serializable {
    private int idgalleryImage;
    private String adress;
    private String name;
    private String desc;
    private List<Message> messages;

    @Id
    @Column(name = "idgallery_image")
    public int getIdgalleryImage() {
        return idgalleryImage;
    }

    public void setIdgalleryImage(int idgalleryImage) {
        this.idgalleryImage = idgalleryImage;
    }

    @Column(name = "adress")
    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "description")
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="gallery_image_has_message",joinColumns = @JoinColumn(name = "gallery_image_idgallery_image")
    ,inverseJoinColumns = @JoinColumn(name = "message_idmessage"))
    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GalleryImage that = (GalleryImage) o;

        if (idgalleryImage != that.idgalleryImage) return false;
        if (adress != null ? !adress.equals(that.adress) : that.adress != null) return false;
        if (desc != null ? !desc.equals(that.desc) : that.desc != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idgalleryImage;
        result = 31 * result + (adress != null ? adress.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (desc != null ? desc.hashCode() : 0);
        return result;
    }
}
