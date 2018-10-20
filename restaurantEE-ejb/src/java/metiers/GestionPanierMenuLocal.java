package metiers;

import entities.Produit;
import java.util.HashMap;
import javax.ejb.Local;

@Local
public interface GestionPanierMenuLocal {

    public HashMap<String, Produit> getChoix();

    public void add(Produit p, String com);

    public void vider();

    public int choixSize();

}
