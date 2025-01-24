package ghostnetfishing;

import java.util.regex.Pattern;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;


@FacesValidator("NameRegexValidator")
public class NameRegexValidator implements Validator{
	
	private static final String REGEX_PATTERN = "^[A-Z][a-zäöüß]+(-[A-Z][a-zäöüß]+)?$";

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) 
    throws ValidatorException {
        String inputValue = (String) value;

        if (inputValue != null && !Pattern.matches(REGEX_PATTERN, inputValue)) {
            FacesMessage message = new FacesMessage("Ungültiger Name! "
            		+ "Bitte geben Sie einen gültigen Namen ein.");
            throw new ValidatorException(message);
        }
    }

}
