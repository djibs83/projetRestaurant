package metiers;

import entities.Tabla;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class GestionTable implements GestionTableLocal {

    @PersistenceContext(unitName = "restaurantEE-ejbPU")
    private EntityManager em;

    @Override
    public List<Tabla> listTable() {
        String query = "select t from Tabla t ORDER BY t.numero ASC";
        Query qr = em.createQuery(query);
        return qr.getResultList();

    }

    @Override
    public Tabla tableById(Long id) {
        return em.find(Tabla.class, id);
    }
}
