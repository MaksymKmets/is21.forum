package forum.services;

import forum.dao.impl.MessageDAO;
import forum.model.Message;
import forum.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Maxym on 10/25/2014.
 */
public class MessageService {
    final static Logger loger = LogManager.getLogger(MessageDAO.class.getName());   //logger
    private Message message;                                     //current messsage
    MessageDAO messageDAO=new MessageDAO();                     //work with message Model
    public void addMessage() {
        messageDAO.save(message);
    }      //save message
    public Message createMessage(User currentUser){             //create message (set date,content,user)
        message.setUser(currentUser);
        return message;
    }
    public void deleteMessage(Message message){
        messageDAO.delete(message);
    }
    public void editMessage(Message message){
        messageDAO.update(message);
    }
    //==================================GETTERS SETTERS==========================================
    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
