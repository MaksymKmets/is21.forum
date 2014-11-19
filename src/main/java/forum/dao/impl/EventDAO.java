package forum.dao.impl;

import forum.model.Event;
import forum.model.GalleryImage;
import forum.model.User;
import org.hibernate.Query;


/**
 * Created by Maxym on 11/7/2014.
 */
public class EventDAO extends GenericDAO<Event> {
    public EventDAO() {
        super(Event.class);
    }
    public void insertUserToEvent(int userId,int eventId){
        beginTransaction();
        session.createSQLQuery("insert into user_has_event () values("+userId+","+eventId+");").executeUpdate();
        commitAndCloseTransaction();
    }
}
