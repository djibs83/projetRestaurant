package metiers;

import entities.LigneCommande;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class GestionCuisine implements GestionCuisineLocal {

    private ArrayList<LigneCommande> ligneCommandes;
    @PersistenceContext(unitName = "restaurantEE-ejbPU")
    private EntityManager em;

    @Override
    public List<LigneCommande> listLigneCommandes() {
        String query = "select l from LigneCommande l";
        Query qr = em.createQuery(query);
        return qr.getResultList();
    }

    @Override
    public LigneCommande MenuById(Long id) {
        return em.find(LigneCommande.class, id);
    }

    @Override
    public void persistLigneCommande(LigneCommande lc) {
        Date d1 = new Date();
        lc.setDebutPreparation(d1);
        Date d2 = new Date();
        lc.setFinPreparation(d2);
        em.merge(lc);
    } 
   
}
