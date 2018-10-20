package metiers;

import entities.Employe;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class GestionEmploye implements GestionEmployeLocal {

    @PersistenceContext(unitName = "restaurantEE-ejbPU")
    private EntityManager em;


    @Override
    public List<Employe> listEmploye(){
        String query = "select e from Employe e";
        Query qr = em.createQuery(query);
        System.out.println(" resultat " +qr.getResultList() );
        return qr.getResultList();
      
    }

    @Override
    public Employe employeById(Long id){
        return em.find(Employe.class, id);
    }
    
    
}
