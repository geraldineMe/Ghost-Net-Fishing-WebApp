package ghostnetfishing;

import java.util.regex.Pattern;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;

@FacesValidator("PasswortRegexValidator")
public class PasswortRegexValidation implements Validator {

	private static final String REGEX_PATTERN = "^(?=.*[0-9])(?=.*[!@#$%^&*(),.?\":{}|<>])[A-Za-z0-9!@#$%^&*(),.?\":{}|<>]{6,}$";

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String inputValue = (String) value;

        if (inputValue != null && !Pattern.matches(REGEX_PATTERN, inputValue)) {
            FacesMessage message = new FacesMessage("Ungültiges Passwort! Mindestlänge 6 Zeichen. Es muss mindestens eine Zahl und ein Sonderzeichen enthalten sein.");
            throw new ValidatorException(message);
        }
    }
	
}
