package forum.dao;

import forum.model.User;

import java.util.List;

/**
 * Created by Макс on 14.08.14.
 */
public interface UserDAO {
    public void addUser(User user);
    public void updateUser(User user);
    public void deleteUser(User user);
    public List<User> getAllUsers();
    public User findUserByEmail(String email, String password);
    public boolean isEmailExist(String email);
    public User findUserByKey(String key);
}
