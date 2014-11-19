package forum.beans.user;

import forum.services.AAService;
import forum.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Макс on 14.08.14.
 */

@ManagedBean(name = "loginMB")
@RequestScoped
public class LoginMB {
    @ManagedProperty(value = "#{userMB}")
    private UserMB userMB;
    private String email;
    private String password;
    private String LoginException="";
    private AAService AAService;


    final static Logger loger = LogManager.getLogger(LoginMB.class.getName());

    public String login()       //login and start web session
    {

        User user = getAAService().isValidLogin(email, password);

        if (user != null) {
            if(user.getActivateKey()!=null)
            {
                LoginException= "User not activate";
                return null;
            }
            userMB.setUser(user);
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
            request.getSession().setAttribute("user",user);
            LoginException="";
            userMB.setLogged(true);
            loger.info(user.getEmail());
            loger.info(user.getPassword());
            loger.info("User is logged");
            return "/content/news/news.xhtml?faces-redirect=true";
        }
        LoginException = "Incorect Email or password";
        return null;
    }





    /*=======================GETERS and SETTERS=================================*/



    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public UserMB getUserMB() {
        return userMB;
    }
    public void setUserMB(UserMB userMB) {
        this.userMB = userMB;
    }

    public String getLoginException()
    {
        return LoginException;
    }

    public AAService getAAService() {
        if(AAService ==null)
        {
            AAService =new AAService();
        }
        return AAService;
    }

}
