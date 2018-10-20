package metiers;

import entities.CategorieProduit;
import entities.Produit;
import java.util.List;
import javax.ejb.Local;


@Local
public interface GestionProduitLocal {

    public List<Produit> listProduits();

    public List<Produit> listCarteProduits(CategorieProduit id);
    
    public Produit ProduitById(Long id);
    
//    public void persistCommande();
//    
//    public Produit getPersistCommande();
//    
//    public void setPersistCommande(Produit produitPersist);
    
}

