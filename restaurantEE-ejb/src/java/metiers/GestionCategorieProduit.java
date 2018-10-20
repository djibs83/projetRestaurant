package metiers;

import entities.CategorieProduit;
import entities.Menu;
import entities.Produit;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Stateless
public class GestionCategorieProduit implements GestionCategorieProduitLocal {

    @PersistenceContext(unitName = "restaurantEE-ejbPU")
    private EntityManager em;

    @Override
    public List<CategorieProduit> listCategorieProduits() {
        String query = "SELECT cp from CategorieProduit cp ORDER BY cp.position ASC";
        Query qr = em.createQuery(query);
        return qr.getResultList();
    }

    @Override
    public CategorieProduit categorieById(Long id){
        return em.find(CategorieProduit.class, id);
    }

    @Override
    public ArrayList<CategorieProduit> listCategorieProduitsByMenu(Menu m){
        ArrayList<CategorieProduit> lCatProduit = new ArrayList<>();
        for(Produit p : m.getProduits()){
            if(!lCatProduit.contains(p.getSousCategorie().getCategorieProduit())){
                lCatProduit.add(p.getSousCategorie().getCategorieProduit());
            }
        }
        return lCatProduit;
    }

}
