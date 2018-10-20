
package metiers;

import entities.Statut;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class GestionStatut implements GestionStatutLocal {

    @PersistenceContext(unitName = "restaurantEE-ejbPU")
    private EntityManager em;

    
    @Override
    public Statut statutByNumero(int numero) {

        String query = "SELECT s FROM Statut s WHERE s.numero = :id";
        Query qr = em.createQuery(query);
        qr.setParameter("id", numero);
        return (Statut) qr.getSingleResult();
    }
    
}
