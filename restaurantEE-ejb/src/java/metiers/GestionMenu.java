package metiers;

import entities.Menu;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class GestionMenu implements GestionMenuLocal {

    @PersistenceContext(unitName = "restaurantEE-ejbPU")
    private EntityManager em;
    

    @Override
    public List<Menu> listMenus(){
        String query = "select m from Menu m";
        Query qr = em.createQuery(query);
        return qr.getResultList();
    }
    
    @Override
    public Menu MenuById(Long id){
        return em.find(Menu.class, id);
    }    
   
}
