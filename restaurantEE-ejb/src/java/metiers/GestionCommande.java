package metiers;

import entities.Commande;
import entities.Statut;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author cdi205
 */
@Stateless
public class GestionCommande implements GestionCommandeLocal {
    @PersistenceContext(unitName = "restaurantEE-ejbPU")
    private EntityManager em;
    /**
     *
     * @param id
     * @return
     */
    @Override
    public Commande commandeById(int id) {
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param Client
     * @return
     */
    @Override
    public Commande commandeByClient(int Client) {
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param Tabla
     * @return
     */
    @Override
    public List<Commande> commandeByTabla(long tabla) {
        
        String query = "SELECT c FROM Commande c WHERE c.tabla.id = :id";
        Query qr = em.createQuery(query);
        qr.setParameter("id", tabla);
        //qr.setParameter("statut", 0);
        System.out.println();
        return qr.getResultList();
    }

    @Override
        public List<Commande> commandeByTablaStatus(long tabla, Statut status) {
        
        String query = "SELECT c FROM Commande c WHERE c.tabla.id = :id AND c.statut.numero = :sid";
        Query qr = em.createQuery(query);
        qr.setParameter("id", tabla);
        qr.setParameter("sid", status.getNumero());
        //qr.setParameter("statut", 0);
        System.out.println();
        return qr.getResultList();
    }
    
    
    
    /**
     *
     * @return
     */
    @Override
    public void validerCommande() {
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @return
     */
    @Override
    public void confirmerCommande() {
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @return
     */
    @Override
    public Commande modifierCommande() {
        throw new UnsupportedOperationException();
    }

}
