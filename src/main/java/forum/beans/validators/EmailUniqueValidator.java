package forum.beans.validators;

import forum.facade.UserFacade;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Макс on 03.09.14.
 */
@FacesValidator("emailUniqueValidator")
public class EmailUniqueValidator implements Validator {
    private UserFacade userFacade;
    public EmailUniqueValidator(){
        userFacade = new UserFacade();
    }
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {

        if(userFacade.isEmailExist(o.toString()))
        {
            FacesMessage msg =
                    new FacesMessage("E-mail validation failed.",
                            "Email Exist");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }
}
