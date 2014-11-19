package forum.beans.user;

import forum.model.User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * Created by Макс on 31.07.14.
 */
@ManagedBean(name = "userMB")
@SessionScoped
public class UserMB implements Serializable {
    private boolean Logged=false;
    private User user;


    /*Log OUT of system*/
    public String logOut()
    {
        getRequest().getSession().invalidate();
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("user");
        return "/security/login.xhtml?faces-redirect=true";
    }
    /*get current request*/
    private HttpServletRequest getRequest()
    {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }





    /*=======================GETERS and SETTERS=================================*/



    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isLogged() {
        return Logged;
    }

    public void setLogged(boolean logged) {
        Logged = logged;
    }

}
