
package ghostnetfishing;


import java.io.Serializable;
import java.util.regex.Pattern;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UIInput;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.AbortProcessingException;
import jakarta.faces.event.ComponentSystemEvent;
import jakarta.faces.validator.ValidatorException;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;


@Named
@ApplicationScoped
public class GeisternetzController implements Serializable
{
	@Inject 
	private GeisternetzListing geisternetzListing;
	@Inject
	private GeisternetzDAO geisternetzDAO;
	@Inject
	private PersonDAO personDAO;
	@Inject
	private LoginController loginController;
    private Geisternetz geisternetz;
    private String breitengrad;
    private DoubleConverter doubleconverter;
   
	
	public GeisternetzController() {
		super();
	}

	
	public String startGeisternetzMelden() {
	        return "melden";
	    }	
	    
	public String stopGeisternetzMelden() {  
		    this.geisternetz= geisternetzListing.getNeuesGeisternetz();
	        this.geisternetzListing.getListe().add(this.geisternetz);
	    	this.geisternetz.setAlsVerschollenMeldendePeron(null);
	        this.personDAO.savePerson(this.geisternetz.getMeldenePerson());
	     	this.geisternetzDAO.saveNewGeisternetz(this.geisternetz,
	     	this.geisternetz.getStandort(),
	        this.geisternetz.getMeldenePerson());
	    	return "index";
	    }
	    
	    
	public String startZurBergungEintragen(Geisternetz g) {
		int index=geisternetzListing.getListe().indexOf(g);
		this.geisternetz=geisternetzListing.getListe().get(index);
		
		if(loginController.getEingeloggterUser()!=null) {
			return "bergen";
		} else return "login";
    }
    
    public String stopZurBergungEintragen() {
    	this.geisternetz.setStatus(Status.BERGUNG_BEVORHSTEHEND);
    	this.loginController.getEingeloggterUser().getZuBergendeGeisternetze().add(this.geisternetz);
    	this.geisternetzDAO.updateGeisternetz(this.geisternetz,this.geisternetz.getStandort(),
    			this.loginController.getEingeloggterUser());
        return "index";
    }
    
    public String geisternetzGeborgen(Geisternetz g) {
    	for (Geisternetz geisternetz:this.geisternetzListing.getListe()) {
    		if (geisternetz.getGeisternetzID()==g.getGeisternetzID()) {
    			geisternetz.setStatus(Status.GEBORGEN);
    	    	this.geisternetzDAO.updateGeisternetz(g, g.getStandort());
    		}
    	}
    	for (Geisternetz gn:loginController.getEingeloggterUser().getZuBergendeGeisternetze()) {
    		if (gn.getGeisternetzID()==g.getGeisternetzID()) {
    			gn.setStatus(Status.GEBORGEN);
    		}
    	}
    	return "index";
    }
    
	public String startAlsVerschollenMelden(Geisternetz g) {
		int index=geisternetzListing.getListe().indexOf(g);
		this.geisternetz=geisternetzListing.getListe().get(index);
        return "verschollen";
    }
	
	public String stopAlsVerschollenMelden() {
		int index=geisternetzListing.getListe().indexOf(this.geisternetz);
		this.geisternetzListing.getListe().get(index).setStatus(Status.VERSCHOLLEN);
		this.geisternetzListing.getListe().get(index).setAlsVerschollenMeldendePeron
		(loginController.getNeuePerson());
		this.personDAO.savePerson(loginController.getNeuePerson());
		this.geisternetzDAO.updateGeisternetz(this.geisternetz,this.geisternetz.getStandort(),
		this.geisternetz.getAlsVerschollenMeldendePeron());
        return "index";
	}
	
	public String abbrechen() {
        return "index";
	}

	public void validateGPSBreitengrad(FacesContext context, UIComponent component, Object value) 
	throws ValidatorException {
		double inputValue = (double)value;
        if ( (inputValue>=91 || inputValue<=-91) || !hatMindestensFuenfNachkommastellen(inputValue) ) {
            FacesMessage message = new FacesMessage("Ungültige GPS-Koordinate! "
            		+ "Bitte geben Sie einen gültigen Breitengrad als Dezimalgrad "
            		+ "mit mindestens 5 Nachkommastellen (z.B.59.95243) ein.");
            throw new ValidatorException(message);
        }
	}
	
	public void validateGPSLaengengrad(FacesContext context, UIComponent component, Object value) 
			throws ValidatorException {
		double inputValue = (double)value;
        if ( (inputValue>=181 || inputValue<=-181) || !hatMindestensFuenfNachkommastellen(inputValue)) {
            FacesMessage message = new FacesMessage("Ungültige GPS-Koordinate! "
            		+ "Bitte geben Sie einen gültigen Längengrad als Dezimalgrad "
            		+ "mit mindestens 5 Nachkommastellen (z.B.59.95243) ein.");
            throw new ValidatorException(message);
        }
	}
	
	public boolean hatMindestensFuenfNachkommastellen(double zahl) {
        String zahlString = Double.toString(zahl);
        int nachkommastelleIndex = zahlString.indexOf('.');
        if (nachkommastelleIndex != -1) {
            String nachkommastellen = zahlString.substring(nachkommastelleIndex + 1);
            return nachkommastellen.length() >= 5;
        }
      return false; 
    }
	
	public String getStatusColor(Geisternetz geisternetz) {
        switch (geisternetz.getStatus()) {         
            case "Bergung bevorstehend":
                return "gelb";                  
            case "geborgen": 
                return "gruen";
            case"verschollen":
                return "grau";           
            default:
                return null;
        }
	}

	public GeisternetzListing getGeisternetzListing() {
		return geisternetzListing;
	}

	public void setGeisternetzListing(GeisternetzListing geisternetzListing) {
		this.geisternetzListing = geisternetzListing;
	}

	public LoginController getLoginController() {
		return loginController;
	}

	public void setLoginController(LoginController loginController) {
		this.loginController = loginController;
	}
	
	public Geisternetz getGeisternetz() {
		return geisternetz;
	}

	public void setGeisternetz(Geisternetz geisternetz) {
		this.geisternetz = geisternetz;
	}

	public String getBreitengrad() {
		return breitengrad;
	}

	public void setBreitengrad(String breitengrad) {
		this.breitengrad = breitengrad;
	}

	public DoubleConverter getDoubleconverter() {
		return doubleconverter;
	}

	public void setDoubleconverter(DoubleConverter doubleconverter) {
		this.doubleconverter = doubleconverter;
	}
    
    
}