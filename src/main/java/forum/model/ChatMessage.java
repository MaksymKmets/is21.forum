package forum.model;

import javax.persistence.*;

/**
 * Created by Maxym on 10/24/2014.
 */
@Entity
@Table(name = "chatmessage")
public class ChatMessage {
    private Integer idchatMessage;
    private Message CMessage;


    @Id
    @GeneratedValue
    @Column(name = "idchatMessage")
    public Integer getIdchatMessage() {
        return idchatMessage;
    }

    public void setIdchatMessage(Integer idchatMessage) {
        this.idchatMessage = idchatMessage;
    }

    @ManyToOne
    @JoinColumn(name = "message_idmessage")
    public Message getCMessage() {
        return CMessage;
    }

    public void setCMessage(Message chatMessage) {
        this.CMessage = chatMessage;
    }


}
