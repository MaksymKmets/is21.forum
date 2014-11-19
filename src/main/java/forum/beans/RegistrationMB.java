package forum.beans;

import forum.beans.user.LoginMB;
import forum.beans.user.UserMB;
import forum.model.RoleName;
import forum.services.AAService;
import forum.model.Role;
import forum.model.User;
import forum.util.MailUtil;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import java.util.ArrayList;

/**
 * Created by Макс on 08.08.14.
 */
@ManagedBean(name = "registrationMB")
@RequestScoped()
public class RegistrationMB{
    final static Logger loger = LogManager.getLogger(LoginMB.class.getName());
    @ManagedProperty(value ="#{userMB}")
    private UserMB userMB;
    private String email;
    private String password;
    private String confirmpassword;
    private String firstName;
    private String lastName;
    private String key;                     //Active code
    private ArrayList<Role> roles;
    private AAService AAService;          //for add user to DB


    /*Validate password and confirm password*/
    public  void validatePassword(ComponentSystemEvent event)
    {
        UIComponent source = event.getComponent();
        UIInput passwordInput = (UIInput)source.findComponent("password");
        UIInput confirmPasswordInput = (UIInput)source.findComponent("confirmpassword");

        String password=passwordInput.getLocalValue().toString();
        String passwordId = passwordInput.getClientId();
        String confirmPassword =confirmPasswordInput.getLocalValue().toString();
        loger.info(password);
        loger.info(confirmPassword);

        if(!password.equals(confirmPassword))
        {
            FacesMessage msg = new FacesMessage("Password must match confirm password");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext.getCurrentInstance().addMessage(passwordId, msg);
            FacesContext.getCurrentInstance().renderResponse();
        }
    }

    /*Registration on webapplication*/
    public  String registration()       //registration and redirect to login
    {
        User newUser;               //Object of new user
        key=new String(Base64.encodeBase64(DigestUtils.sha(email+"."+password)));
        loger.info(key);
        roles =new ArrayList<Role>();
        newUser = new User(firstName,lastName,email,password,key);
        Role role = new Role(RoleName.ADMIN);
        Role role1 = new Role(RoleName.USER);
        roles.add(role);
        roles.add(role1);
        newUser.setRoles(roles);
        MailUtil.sendMail(email,"registration","Your registration  was successful. To activate your account click next link\n <a href=\"http://localhost:8080/security/successActivation.xhtml?key="+key+"\">Activation Link</a>");
        getAAService().addUser(newUser);
        getAAService().addRoles(roles);
        return "/security/successRegistration.xhtml";
    }

    /*Activation on webapplication*/
    public void activation()
    {
        FacesContext fc = FacesContext.getCurrentInstance();
        User user = getAAService().findUserByKey(key);
        if (user==null){
            ConfigurableNavigationHandler nav= (ConfigurableNavigationHandler)fc.getApplication().getNavigationHandler();
            nav.performNavigation("failureActivation");
        }
        else
        {
            user.setActivateKey(null);
            getAAService().updateUser(user);
        }
    }





    /*=======================GETERS and SETTERS=================================*/



    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

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

    public String getConfirmpassword() {
        return confirmpassword;
    }

    public void setConfirmpassword(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }

    public UserMB getUserMB() {
        return userMB;
    }

    public void setUserMB(UserMB userMB) {
        this.userMB = userMB;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public AAService getAAService() {
        if(AAService ==null)
        {
            AAService = new AAService();
        }
        return AAService;
    }

    public void setAAService(AAService AAService) {
        this.AAService = AAService;
    }
}
