package ghostnetfishing;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class PersonDAO  {
	
	private  static EntityManagerFactory emf=Persistence.createEntityManagerFactory("ghostnetFishingAppPersistenceUnit");
	
	public List<Person> findAll(){
		EntityManager em=emf.createEntityManager();
		Query q=em.createQuery("select a from Person a");
		List<Person> allePersonen=q.getResultList();
		return allePersonen;
	}
	
    public void savePerson(Person p) {
     	EntityManager em = emf.createEntityManager();
         EntityTransaction t = em.getTransaction();
         t.begin();
         em.persist(p);
         t.commit();
    	
    }
	
    public void saveUser(BergendePerson b) {
     	EntityManager em = emf.createEntityManager();
         EntityTransaction t = em.getTransaction();
         t.begin();
         em.persist(b);
         t.commit();
    	
    }
    
    public void updateUser(BergendePerson b) {
     	EntityManager em = emf.createEntityManager();
         EntityTransaction t = em.getTransaction();
         t.begin();
         em.merge(b);
         t.commit();
    	
    }

}
