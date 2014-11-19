package forum.services;

import forum.dao.impl.ChatMessageDAO;
import forum.dao.impl.MessageDAO;
import forum.model.ChatMessage;
import forum.model.Message;

import java.util.List;

/**
 * Created by Макс on 11.10.14.
 */
public class ChatService{
    MessageDAO messageDAO= new MessageDAO();                //work with message
    ChatMessageDAO chatMessageDAO=new ChatMessageDAO();     //work with Chat


    public List<Message> getAllMessages()
    {
        return getMessageDAO().getAllMessagesForChat();
    }           //get All MEssage of Chat
    public void addMessageAtChat(Message message)       //addMessage at chat
    {
        ChatMessage chatMessage= new ChatMessage();
        chatMessage.setCMessage(message);
        getMessageDAO().save(message);
        getChatMessageDAO().save(chatMessage);
    }












    //==================================Getters and Setters==========================================
    public MessageDAO getMessageDAO() {
        if(messageDAO==null){
            messageDAO=new MessageDAO();
        }
        return messageDAO;
    }

    public void setMessageDAO(MessageDAO messageDAO) {
        this.messageDAO = messageDAO;
    }

    public ChatMessageDAO getChatMessageDAO() {
        if(chatMessageDAO==null){
            chatMessageDAO=new ChatMessageDAO();
        }
        return chatMessageDAO;
    }

    public void setChatMessageDAO(ChatMessageDAO chatMessageDAO) {
        this.chatMessageDAO = chatMessageDAO;
    }
}
