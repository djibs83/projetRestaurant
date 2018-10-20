package metiers;

import entities.CategorieProduit;
import entities.Menu;
import java.util.List;
import javax.ejb.Local;


@Local
public interface GestionCategorieProduitLocal {
    
    public List<CategorieProduit> listCategorieProduitsByMenu(Menu m);

    public CategorieProduit categorieById(Long id);

    public List<CategorieProduit> listCategorieProduits();
}
