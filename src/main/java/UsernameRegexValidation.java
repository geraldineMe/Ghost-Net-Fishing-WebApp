package ghostnetfishing;

import java.util.regex.Pattern;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;

@FacesValidator("UsernameRegexValidator")
public class UsernameRegexValidation implements Validator{

	private static final String REGEX_PATTERN = "^[A-Za-z]{3}[A-Za-z0-9]*$";

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String inputValue = (String) value;

        if (inputValue != null && !Pattern.matches(REGEX_PATTERN, inputValue)) {
            FacesMessage message = new FacesMessage("Ungültiger Username! Mindestlänge 4 Zeichen, die ersten 3 Zeichen müssen Buchstaben sein.");
            throw new ValidatorException(message);
        }
    }
	
}
