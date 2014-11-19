package forum.dao.impl;

import forum.model.ChatMessage;

/**
 * Created by Maxym on 10/24/2014.
 */
public class ChatMessageDAO extends GenericDAO<ChatMessage>{
    public ChatMessageDAO() {
        super(ChatMessage.class);
    }
}
