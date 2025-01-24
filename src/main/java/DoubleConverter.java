package ghostnetfishing;


import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

@FacesConverter("doubleConverter")
public class DoubleConverter implements Converter {

	  @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null; 
        }

        try {
            return Double.parseDouble(value);
        }  catch (NumberFormatException e) {   
            return null;
        }
    }
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }
        return value.toString();
    }
}
