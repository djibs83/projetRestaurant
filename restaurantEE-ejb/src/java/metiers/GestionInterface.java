package metiers;

import entities.InterfaceAccueil;
import java.util.List;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Singleton
public class GestionInterface implements GestionInterfaceLocal {

    @PersistenceContext(unitName = "restaurantEE-ejbPU")
    private EntityManager em;


    @Override
    public List<InterfaceAccueil> listInterfaceAccueil(){
        String query = "select i from InterfaceAccueil i";
        Query qr = em.createQuery(query);
        System.out.println(" resultat " +qr.getResultList() );
        return qr.getResultList();
      
    }

    @Override
    public InterfaceAccueil InterfaceAccueilById(String id){
        return em.find(InterfaceAccueil.class, id);
    }
}
