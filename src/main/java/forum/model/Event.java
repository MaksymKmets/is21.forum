package forum.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by Maxym on 10/23/2014.
 */
@Entity
public class Event implements Serializable {
    private int idevent;
    private Timestamp date;
    private String info;
    private String header;
    private List<User> users;


    @Id
    @Column(name = "idevent")
    public int getIdevent() {
        return idevent;
    }

    public void setIdevent(int idevent) {
        this.idevent = idevent;
    }

    @Basic
    @Column(name = "date")
    public Timestamp getDate() {

        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Basic
    @Column(name = "info")
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Basic
    @Column(name = "header")
    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        if (idevent != event.idevent) return false;
        if (date != null ? !date.equals(event.date) : event.date != null) return false;
        if (header != null ? !header.equals(event.header) : event.header != null) return false;
        if (info != null ? !info.equals(event.info) : event.info != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idevent;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (info != null ? info.hashCode() : 0);
        result = 31 * result + (header != null ? header.hashCode() : 0);
        return result;
    }

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="user_has_event",joinColumns = @JoinColumn(name = "user_iduser") ,
            inverseJoinColumns = @JoinColumn(name = "event_idevent"))
    public List<User> getUsers() {
        return users;
    }
    public void setUsers(List<User> users) {
        this.users = users;
    }
}
