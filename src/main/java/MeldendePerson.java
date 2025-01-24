package ghostnetfishing;

import java.io.Serializable;
import jakarta.persistence.Entity;


@Entity
public class MeldendePerson extends Person implements Serializable {
	
	public MeldendePerson() {
		super();
	}

	public MeldendePerson(Geisternetz gemeldetesGeisternetz) {
		this();
		
	}

	public MeldendePerson(String name, String telefonnummer) {
		super(name,telefonnummer);
	}
	

	
	
}