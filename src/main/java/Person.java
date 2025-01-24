package ghostnetfishing;

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Person implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int personID;
    private String name;
	private String telefonnummer;
	
	public Person() {
		
	}
	
	public Person(String name) {
		super();
		this.name = name;
	}
	
	
	public Person(String name, String telefonnummer) {
		super();
		this.name = name;
		this.telefonnummer = telefonnummer;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getTelefonnummer() {
		return telefonnummer;
	}


	public void setTelefonnummer(String telefonnummer) {
		this.telefonnummer = telefonnummer;
	}

	public int getPersonID() {
		return personID;
	}
	
}
