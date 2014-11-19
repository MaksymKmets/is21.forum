package forum.dao.impl;


import forum.model.Message;
import forum.util.HibernateUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Макс on 09.10.14.
 */
public class MessageDAO extends GenericDAO<Message>
{
    final static Logger loger = LogManager.getLogger(MessageDAO.class.getName());

    public MessageDAO() {
        super(Message.class);
    }

    public List<Message> getAllMessagesForChat() {
        Session session =null;
        List<Message> messages=null;

            session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            messages=(ArrayList<Message>)session.createQuery(" select m.CMessage from ChatMessage m order by m.CMessage.date desc ").list();
            //select m.chatMessage from ChatMessage m order by m.chatMessage.date desc
            session.getTransaction().commit();
            loger.info("Messages of chat recieved");
            session.close();
        return messages;

        }
    }


