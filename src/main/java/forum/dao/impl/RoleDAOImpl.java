package forum.dao.impl;

import forum.dao.RoleDAO;
import forum.model.Role;
import forum.util.HibernateUtil;
import org.hibernate.Session;

/**
 * Created by Макс on 06.09.14.
 */
public class RoleDAOImpl implements RoleDAO{

    @Override
    public void addRole(Role role) {
        Session session =null;
        try {
            session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(role);
            session.getTransaction().commit();
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        } finally {
            if(session!=null &&session.isOpen())
            {
                session.close();
            }
        }
    }
}
