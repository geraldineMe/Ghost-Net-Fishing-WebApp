package ghostnetfishing;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class GeisternetzDAO  {
	
	private  static EntityManagerFactory emf=Persistence.createEntityManagerFactory("ghostnetFishingAppPersistenceUnit");
	
	public List<Geisternetz> findAll(){
		EntityManager em=emf.createEntityManager();
		Query q=em.createQuery("select a from Geisternetz a");
		List<Geisternetz> alleGeisternetze=q.getResultList();
		return alleGeisternetze;
	}

    public void saveNewGeisternetz(Geisternetz g, GPSKoordinate gps, Person p) {
     	EntityManager em = emf.createEntityManager();
         EntityTransaction t = em.getTransaction();
         t.begin();
         em.persist(g);
         em.persist(gps);
         em.merge(p);
         t.commit();
    }
    public void updateGeisternetz(Geisternetz g, GPSKoordinate gps) {
    	 EntityManager em = emf.createEntityManager();
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.merge(g);
        em.merge(gps);
        t.commit();
   }

    public void updateGeisternetz(Geisternetz g, GPSKoordinate gps, Person p) {
     	 EntityManager em = emf.createEntityManager();
         EntityTransaction t = em.getTransaction();
         t.begin();
         em.merge(g);
         em.merge(gps);
         em.merge(p);
         t.commit();
    }

}
