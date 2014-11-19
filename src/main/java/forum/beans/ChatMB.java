package forum.beans;


import forum.beans.user.LoginMB;
import forum.model.Message;
import forum.model.User;
import forum.services.ChatService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;

/**
 * Created by Макс on 08.10.14.
 */
@ManagedBean(name = "chatMB")
@RequestScoped
public class ChatMB extends Topic implements Commentable {
    final static Logger loger = LogManager.getLogger(LoginMB.class.getName());

    private ChatService chatService =new ChatService();

    @Override
    public List<Message> getMessages() {
        return chatService.getAllMessages();
    }

    @Override
    public void sendMessage(User user) {
        messageService.setMessage(message);
        chatService.addMessageAtChat(messageService.createMessage(user));
    }
}
