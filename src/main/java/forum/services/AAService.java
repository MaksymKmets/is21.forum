package forum.services;


import forum.dao.impl.RoleDAO;
import forum.dao.impl.UserDAO;
import forum.model.Role;
import forum.model.User;

import java.util.List;

/**
 * Created by Макс on 14.08.14.
 */
public class AAService {            //managging of user rights

    private UserDAO userDAO=new UserDAO();                //work with User
    private RoleDAO roleDAO=new RoleDAO();                //work with Model
    public void addRoles(List<Role> roles)          //add rolles
    {
        for(Role role : roles)
        {
            roleDAO.addRole(role);
        }
    }
    public User isValidLogin(String email,String password){                     //validation by email and password
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
    } //validation of existing
    public User findUserByKey(String key)                       //find user By activation key
    {
        return userDAO.findUserByKey(key);
    }       //find by activation key
    public void  addUser(User user)
    {
        userDAO.save(user);
    }                           //addUser to db
    public void updateUser(User user)
    {
        userDAO.update(user);
    }                       //update user


}
