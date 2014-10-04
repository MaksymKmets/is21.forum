package forum.beans;

import forum.facade.RoleFacade;
import forum.facade.UserFacade;
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
import javax.faces.validator.ValidatorException;
import javax.xml.bind.ValidationException;
import java.util.ArrayList;
import java.util.List;

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
    private List<Role> roles;
    private UserFacade userFacade;          //for add user to DB
    private RoleFacade roleFacade;          //for add Role to DB


    /*Validate password and confirm password*/
    public  void validatePassword(ComponentSystemEvent event)
    {
        UIComponent source = event.getComponent();
        UIInput passwordInput = (UIInput)source.findComponent("password");
        UIInput confirmPasswordInput = (UIInput)source.findComponent("confirmpassword");

        String password=passwordInput.getLocalValue().toString();
        String confirmPassword =confirmPasswordInput.getLocalValue().toString();
        loger.info(password);
        loger.info(confirmPassword);

        if(!password.equals(confirmPassword))
        {
            throw new ValidatorException(new FacesMessage("Passwords diferent"));
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
        Role role = new Role("USER",newUser);
        Role role1 = new Role("ADMIN",newUser);
        roles.add(role);
        roles.add(role1);
        newUser.setRoles(roles);


        loger.info(newUser.getFirstName());
        loger.info(newUser.getLastName());
        loger.info(newUser.getEmail());
        loger.info(newUser.getPassword());
        loger.info(newUser.getKey());
        loger.info(newUser.getId());

        MailUtil.sendMail(email,"registration","Your registration  was successful. To activate your account click next link\n <a href=\"http://localhost:8080/security/successActivation.xhtml?key="+key+"\">Activation Link</a>");
        getUserFacade().addUser(newUser);
        getRoleFacade().addRoles(roles);
        return "/security/successRegistration.xhtml";
    }

    /*Activation on webapplication*/
    public void activation()
    {
        FacesContext fc = FacesContext.getCurrentInstance();
        User user = getUserFacade().findUserByKey(key);
        if (user==null){
            ConfigurableNavigationHandler nav= (ConfigurableNavigationHandler)fc.getApplication().getNavigationHandler();
            nav.performNavigation("failureActivation");
        }
        else
        {
            user.setKey(null);
            getUserFacade().updateUser(user);
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

    public UserFacade getUserFacade() {
        if(userFacade==null)
        {
            userFacade = new UserFacade();
        }
        return userFacade;
    }

    public void setUserFacade(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    public RoleFacade getRoleFacade() {
        if(roleFacade==null)
        {
            roleFacade=new RoleFacade();
        }
        return roleFacade;
    }

    public void setRoleFacade(RoleFacade roleFacade) {
        this.roleFacade = roleFacade;
    }
}
