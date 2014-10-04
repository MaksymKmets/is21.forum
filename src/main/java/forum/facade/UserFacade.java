package forum.facade;

import forum.dao.UserDAO;
import forum.dao.impl.UserDAOImpl;
import forum.model.User;

/**
 * Created by Макс on 14.08.14.
 */
public class UserFacade {
    private UserDAO userDAO=new UserDAOImpl();
    public User isValidLogin(String email,String password){
        User user = userDAO.findUserByEmail(email, password);

        if(user==null || !user.getPassword().equals(password))
        {
            return null;
        }
        return user;
    }
    public boolean isEmailExist(String email)
    {
        return userDAO.isEmailExist(email);
    }
    public User findUserByKey(String key)
    {
        return userDAO.findUserByKey(key);
    }
    public void  addUser(User user)
    {
        userDAO.addUser(user);
    }
    public void updateUser(User user)
    {
        userDAO.updateUser(user);
    }
}
