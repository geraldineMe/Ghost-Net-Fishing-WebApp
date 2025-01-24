package ghostnetfishing;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class GPSKoordinate implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int standortID;
	private double breitengrad;  
    private double laengengrad; 
    
    public GPSKoordinate() {
    }

    public GPSKoordinate(double breitengrad, double laengengrad) {
        this.breitengrad = breitengrad;
        this.laengengrad = laengengrad;
    }

    
    public void setBreitengrad(double breitengrad) {
		this.breitengrad = breitengrad;
	}

	public void setLaengengrad(double laengengrad) {
		this.laengengrad = laengengrad;
	}

	public double getBreitengrad() {
        return breitengrad;
    }

    public double getLaengengrad() {
        return laengengrad;
    }
    

	public int getStandortID() {
		return standortID;
	}

	public void setStandortID(int standortID) {
		this.standortID = standortID;
	}

	@Override
	public String toString() {
		return breitengrad + ", " + laengengrad ;
	}
    
    
}