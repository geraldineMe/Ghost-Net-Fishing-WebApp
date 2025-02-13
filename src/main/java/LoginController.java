package ghostnetfishing;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UIInput;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.AbortProcessingException;
import jakarta.faces.event.ComponentSystemEvent;
import jakarta.faces.validator.ValidatorException;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

@Named
@ApplicationScoped
public class LoginController implements Serializable {
   
	private List<Person> benutzerListe;
	private PersonDAO personDAO;
	private Person neuePerson;
	private BergendePerson bergendePerson;
	private BergendePerson eingeloggterUser=null;	
	private String name;
	private String passwort;


	public LoginController() {
		this.personDAO=new PersonDAO();
		this.benutzerListe = new ArrayList<Person>();
		this.benutzerListe=this.getBenutzerliste();
		this.neuePerson=new Person();
		this.bergendePerson = new BergendePerson();
	}

	public List<Person> getBenutzerliste(){
		return personDAO.findAll();
	}
	
	public String login() {
		return "index";
    }
	
	public void postValidateName(ComponentSystemEvent ev) throws AbortProcessingException {
		UIInput temp = (UIInput)ev.getComponent();
		this.name = (String)temp.getValue();
	}
	
	public void validateLogin(FacesContext context, UIComponent component, Object value) 
	throws ValidatorException {
		for(Person p:benutzerListe) {
			Person temp=new BergendePerson(this.name, (String)value);
			if(p instanceof BergendePerson) {
			if(p.equals(temp)) {
				this.eingeloggterUser=new BergendePerson();
			    this.eingeloggterUser=(BergendePerson) p;
				return;
			}
			}
		}
		throw new ValidatorException(new FacesMessage("Login falsch!"));
	}

	
	public String einloggen() {
		return "login";
}
	
	public String ausloggen() {
		this.eingeloggterUser=null;
		return "index";
}
	public String startRegistrieren() {
		return "registrieren";
}
    public String registrieren() {  
    	this.benutzerListe.add(this.bergendePerson);
    	this.eingeloggterUser=this.bergendePerson;
    	this.personDAO.saveUser(this.bergendePerson);
        return "index";
    }
    
	public void postValidateNewUserName(ComponentSystemEvent ev) throws AbortProcessingException {
		UIInput temp = (UIInput)ev.getComponent();
		this.name = (String)temp.getValue();
	}
	
	
    public void validateNewUser(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		String newUserPhone = (String)value;
		
		for (Person p : benutzerListe) {
			if(p instanceof BergendePerson) {
				if(p.getName()!=null && p.getTelefonnummer()!=null) {
				if(((BergendePerson) p).getName().equals(this.name)&& ((BergendePerson) p).getTelefonnummer().equals(newUserPhone) ) {
					throw new ValidatorException(new FacesMessage("Es ist bereits ein Account für den Namen "+name+" mit der Handynummer "+newUserPhone+" vorhanden."));
				}
				}
			}
		}
    }
    
    public void validateNewUserUsername(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		String username = (String)value;
		
		for (Person p : benutzerListe) {
			if(p instanceof BergendePerson) {
				if(((BergendePerson) p).getUsername().equals(username)) {
					throw new ValidatorException(new FacesMessage("Username bereits vorhanden!"));
				}
			}
		}
    }
	public void postValidateNewUserPasswort(ComponentSystemEvent ev) throws AbortProcessingException {
		UIInput temp = (UIInput)ev.getComponent();
		this.passwort = (String)temp.getValue();
	}
	
    public void validatePassword(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		String passwort2 = (String)value;
		
		if(!passwort2.equals(this.passwort)) {
			throw new ValidatorException(new FacesMessage("Die Passwörter stimmen nicht überein!"));
		}
    }
    
    
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

	public Person getNeuePerson() {
		return neuePerson;
	}

	public void setNeuePerson(Person neuePerson) {
		this.neuePerson = neuePerson;
	}

	public BergendePerson getBergendePerson() {
		return bergendePerson;
	}

	public void setBergendePerson(BergendePerson bergendePerson) {
		this.bergendePerson = bergendePerson;
	}
	public BergendePerson getEingeloggterUser() {
		return eingeloggterUser;
	}

	public void setEingeloggterUser(BergendePerson eingeloggterUser) {
		this.eingeloggterUser = eingeloggterUser;
	}
	public List<Person> getBenutzerListe() {
		return benutzerListe;
	}

	public void setBenutzerListe(List<Person> benutzerListe) {
		this.benutzerListe = benutzerListe;
	}

	public PersonDAO getBenutzerDAO() {
		return personDAO;
	}

	public void setBenutzerDAO(PersonDAO personDAO) {
		this.personDAO = personDAO;
	}

	public String getPasswort() {
		return passwort;
	}

	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}
	
	
}
