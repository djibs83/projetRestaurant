package metiers;

import entities.Commande;
import entities.LigneCommande;
import entities.Menu;
import entities.Produit;
import entities.Tabla;
import entities.TypeCuisson;
import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.Local;

@Local
public interface GestionPanierLocal {

    public float getPrixTotalHT();

    public void add(Produit p, String com, TypeCuisson typeCuisson);

    public void subProduit(int index);

    public void vider();

    public int panierSize();

    public Commande getCommande();

    public void setCommande(Commande commande);

    public ArrayList<LigneCommande> getLigneCommandesProduits();

    public void persistePanier(Tabla table);

    public void validerMenu(Menu menu, Collection<Produit> choix, String commentaire);

}
