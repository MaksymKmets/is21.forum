package forum.facade;

import forum.dao.RoleDAO;
import forum.dao.impl.RoleDAOImpl;
import forum.model.Role;

import java.util.List;

/**
 * Created by Макс on 06.09.14.
 */
public class RoleFacade {
    RoleDAO roleDAO = new RoleDAOImpl();
    public void addRoles(List<Role> roles)
    {
        for(Role role : roles)
        {
            roleDAO.addRole(role);
        }
    }
}
