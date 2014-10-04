package forum.beans;

import forum.facade.UserFacade;
import forum.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.omg.DynamicAny._DynUnionStub;
import org.primefaces.context.RequestContext;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.persistence.Column;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Макс on 14.08.14.
 */
@RequestScoped
@ManagedBean
public class LoginMB {
    @ManagedProperty(value = "#{userMB}")
    private UserMB userMB;
    private String email;
    private String password;
    private String LoginException="";
    private UserFacade userFacade;


    final static Logger loger = LogManager.getLogger(LoginMB.class.getName());

    public String login()       //login and start web session
    {

        User user =getUserFacade().isValidLogin(email, password);

        if (user != null) {
            if(user.getKey()!=null)
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
            return "/content/news.xhtml?faces-redirect=true";
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

    public UserFacade getUserFacade() {
        if(userFacade==null)
        {
            userFacade=new UserFacade();
        }
        return userFacade;
    }

}
