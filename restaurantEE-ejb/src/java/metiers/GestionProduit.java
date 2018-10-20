package metiers;

import entities.CategorieProduit;
import entities.Facture;
import entities.LigneCommande;
import entities.Produit;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class GestionProduit implements GestionProduitLocal {

    private List<Produit> produits;
    private Produit produitPersist;
    @PersistenceContext(unitName = "restaurantEE-ejbPU")
    private EntityManager em;
    

    @Override
    public List<Produit> listProduits(){
        String query = "select p from Produit p";
        Query qr = em.createQuery(query);
        return qr.getResultList();
    }
    
    @Override
    public List<Produit> listCarteProduits(CategorieProduit id){
        String query = "select p from Produit p WHERE p.sousCategorie.categorieProduit.id = :id";
        Query qr = em.createQuery(query);
        qr.setParameter("id", id.getId());
        return qr.getResultList();
    }
    
    @Override
    public Produit ProduitById(Long id){
        return em.find(Produit.class, id);
    }
        
//    @Override
//    public void persistCommande() {
//        em.merge(produitPersist);
//    }
//    
//    @Override
//    public Produit getPersistCommande() {
//        return produitPersist;
//    }
//
//    @Override
//    public void setPersistCommande(Produit produitPersist) {
//        this.produitPersist = produitPersist;
//    }
}
