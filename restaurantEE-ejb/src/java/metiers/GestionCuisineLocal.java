package metiers;

import entities.LigneCommande;

import java.util.List;
import javax.ejb.Local;

@Local
public interface GestionCuisineLocal {
 
     public List<LigneCommande> listLigneCommandes();
     
     public LigneCommande MenuById(Long id);
     
     public void persistLigneCommande(LigneCommande lc);
    
    
    
}
