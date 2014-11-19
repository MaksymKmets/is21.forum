package forum.beans;

import forum.dao.impl.MessageDAO;
import forum.model.Message;
import forum.services.MessageService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Maxym on 10/25/2014.
 */
public abstract class Topic {
    final static Logger loger = LogManager.getLogger(MessageDAO.class.getName());
    protected Message message;
    protected List<Message> messages;
    protected MessageService messageService = new MessageService();

    public Message getMessage() {
        if(message==null){
            message=new Message();
            message.setDate(getTime());
        }
        return message;
    }
    protected Timestamp getTime()                         //set time of message
    {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        dateFormat.format(date);
        return new Timestamp(date.getTime());
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

}
