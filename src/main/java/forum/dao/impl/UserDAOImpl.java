package forum.dao.impl;

import forum.dao.UserDAO;
import forum.model.User;
import forum.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import javax.swing.*;
import java.util.List;

/**
 * Created by Макс on 14.08.14.
 */
public class UserDAOImpl implements UserDAO
{
    @Override
    public void addUser(User user) {
        Session session =null;
        try {
            session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(user);
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

    @Override
    public void updateUser(User user) {
        Session session =null;
        try {
            session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(user);
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

    @Override
    public void deleteUser(User user) {
        Session session =null;
        try {
            session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(user);
            session.getTransaction().commit();
        } catch (Exception exception) {
            throw new RuntimeException("User not deleted");
        } finally {
            if(session!=null &&session.isOpen())
            {
                session.close();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        Session session =null;
        try {
            session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.createCriteria(User.class).list();
            session.getTransaction().commit();
        } catch (Exception exception) {
            throw new RuntimeException("Users have not received");
        } finally {
            if(session!=null &&session.isOpen())
            {
                session.close();
            }
        }
        return null;
    }

    @Override
    public User findUserByEmail(String email, String password){
        Session session =null;
        User user=null;
        try {

            session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query=session.getNamedQuery("findUserByEmail");
            query.setString("email", email);
            user=(User)query.uniqueResult();
            session.getTransaction().commit();
        } catch (Exception exception) {
        } finally {

            if(session!=null && session.isOpen())
            {
                session.close();
            }
        }
        return user;
    }

    @Override
    public boolean isEmailExist(String email) {
        Session session =null;
        try {

            session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Criteria criteria = session.createCriteria(User.class);
            if(criteria.add(Restrictions.eq("email", email))
                    .uniqueResult()!=null)
            {
                session.getTransaction().commit();
                return true;
            }
        } catch (Exception exception) {
        } finally {

            if(session!=null && session.isOpen())
            {
                session.close();
            }
        }
        return false;
    }

    @Override
    public User findUserByKey(String key) {
        Session session =null;
        User user;
        try {
            session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Criteria criteria = session.createCriteria(User.class);
            user=(User)criteria.add(Restrictions.eq("key", key)).uniqueResult();
            if(user!=null)
            {
                session.getTransaction().commit();
                return user;
            }
        } catch (Exception exception) {
        } finally {

            if(session!=null && session.isOpen())
            {
                session.close();
            }
        }
        return null;
    }
}
