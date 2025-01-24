package ghostnetfishing;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Geisternetz implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int geisternetzID;
	@OneToOne
	private GPSKoordinate standort;
	private int groesse;
	private Status status;
	@OneToOne
	private MeldendePerson meldenePerson;
	@OneToOne
	private Person alsVerschollenMeldendePeron;

	
	public Geisternetz() {
		this.standort=new GPSKoordinate();
		this.groesse=100;
		this.status=Status.GEMELDET;
		this.meldenePerson=new MeldendePerson();
		this.alsVerschollenMeldendePeron=new Person();
	}
	
	public Geisternetz(GPSKoordinate standort, int groesse, MeldendePerson meldendePerson) {
		this();
		this.standort = standort;
		this.groesse = groesse;
		this.meldenePerson = meldendePerson;	
	}
	
	
	public Geisternetz(GPSKoordinate standort, int groesse, MeldendePerson meldendePerson, Person alsVerschollenMeldendePeron) {
		this(standort,groesse,meldendePerson);
		this.alsVerschollenMeldendePeron=alsVerschollenMeldendePeron;
	}
	

	public GPSKoordinate getStandort() {
		return standort;
	}

	public void setStandort(GPSKoordinate standort) {
		this.standort = standort;
	}

	public int getGroesse() {
		return groesse;
	}

	public void setGroesse(int groesse) {
		this.groesse = groesse;
	}

	public String getStatus() {
		 switch (status) {  
		 case GEMELDET:
			 return "gemeldet";
         case BERGUNG_BEVORHSTEHEND:
             return "Bergung bevorstehend";                  
         case GEBORGEN: 
             return "geborgen";
         case VERSCHOLLEN:
             return "verschollen";           
         default:
             return null;
     }
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Person getMeldenePerson() {
		return meldenePerson;
	}

	public void setMeldenePerson(MeldendePerson meldenePerson) {
		this.meldenePerson = meldenePerson;
	}


	public int getGeisternetzID() {
		return geisternetzID;
	}

	public Person getAlsVerschollenMeldendePeron() {
		return alsVerschollenMeldendePeron;
	}

	public void setAlsVerschollenMeldendePeron(Person alsVerschollenMeldendePeron) {
		this.alsVerschollenMeldendePeron = alsVerschollenMeldendePeron;
	}

	

}
