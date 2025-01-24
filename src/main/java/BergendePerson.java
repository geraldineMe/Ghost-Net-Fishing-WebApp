package ghostnetfishing;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class BergendePerson extends Person implements Serializable {
	
	private String username;
	private String passwort;
	
	@OneToMany
	private List<Geisternetz> zuBergendeGeisternetze;
	
	
	public BergendePerson() {
		super();
		this.zuBergendeGeisternetze=new ArrayList<Geisternetz>();
	}
	
	public BergendePerson(String name, String telefonnummer, String username, String passwort) {
		super(name,telefonnummer);
		this.username=username;
		this.passwort=passwort;
	}

	public BergendePerson(String username, String passwort) {
		this.username=username;
		this.passwort=passwort;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPasswort() {
		return passwort;
	}
	public void setPasswort(String passwort) {
        this.passwort = hashPasswort(passwort);
    }

	public List<Geisternetz> getZuBergendeGeisternetze() {
		return zuBergendeGeisternetze;
	}

	public void setZuBergendeGeisternetze(List<Geisternetz> zuBergendeGeisternetze) {
		this.zuBergendeGeisternetze = zuBergendeGeisternetze;
	}
	
    private String hashPasswort(String passwort) {
        return BCrypt.hashpw(passwort, BCrypt.gensalt());
    }

    public boolean checkPasswort(String passwort) {
        return BCrypt.checkpw(passwort, this.passwort);
    }
    
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof BergendePerson) {
			BergendePerson b = (BergendePerson)obj;
			if(b.getUsername().equals(this.username) && this.checkPasswort(b.getPasswort())) {
				return true;
			}
		}
		return false;
	}
	
	
}