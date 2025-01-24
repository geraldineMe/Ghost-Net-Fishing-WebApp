package ghostnetfishing;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ApplicationScoped
public class GeisternetzListing implements Serializable {
	

	private List<Geisternetz> liste;
	private GeisternetzDAO geisternetzDAO;
	private Geisternetz neuesGeisternetz=null;
	
	public GeisternetzListing() {
		this.geisternetzDAO=new GeisternetzDAO();
	    this.liste=  new ArrayList<Geisternetz>(this.getGeisternetze());
	}

	public List<Geisternetz> getGeisternetze(){
		return geisternetzDAO.findAll();
	}
	
	public List<Geisternetz> getListe() {
		return liste;
	}

	public void setListe(List<Geisternetz> liste) {
		this.liste = liste;
	}


	public void setNeuesGeisternetz(Geisternetz neuesGeisternetz) {
		this.neuesGeisternetz = neuesGeisternetz;
	}

	public Geisternetz getNeuesGeisternetz() {
		if(null==neuesGeisternetz) {
	    this.neuesGeisternetz=new Geisternetz();
		}
		return this.neuesGeisternetz;
	}
	
}
	




