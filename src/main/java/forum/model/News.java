package forum.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;


/**
 * Created by Maxym on 10/23/2014.
 */
@Entity
public class News  implements Serializable {
    private int idnews;
    private String theme;
    private String content;
    private Timestamp date;
    private List<Message> messages;

    @Id
    @Column(name = "idnews")
    public int getIdnews() {
        return idnews;
    }

    public void setIdnews(int idnews) {
        this.idnews = idnews;
    }

    @Basic
    @Column(name = "theme")
    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "date")
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }


    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="message_has_news",joinColumns = @JoinColumn(name = "news_idnews")
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

        News news = (News) o;

        if (idnews != news.idnews) return false;
        if (content != null ? !content.equals(news.content) : news.content != null) return false;
        if (date != null ? !date.equals(news.date) : news.date != null) return false;
        if (theme != null ? !theme.equals(news.theme) : news.theme != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idnews;
        result = 31 * result + (theme != null ? theme.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }
}
