package utilitaire;

import entities.Commande;
import entities.LigneCommande;
import entities.Menu;
import entities.Produit;
import entities.Tabla;
import entities.TypeCuisson;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import metiers.GestionPanierLocal;

public class PanierWar implements Serializable {
    
    GestionPanierLocal gestionPanier = lookupGestionPanierLocal();
    
    public PanierWar() {
        gestionPanier = lookupGestionPanierLocal();
    }
    
    public ArrayList<LigneCommande> getLigneCommandesProduits() {
        return gestionPanier.getLigneCommandesProduits();
    }
    
    public void setCommande(Commande commande) {
        gestionPanier.setCommande(commande);
    }
    
    public float getPrixTotalHT() {
        return gestionPanier.getPrixTotalHT();
    }
    
    public void add(Produit p, String com, TypeCuisson typeCuisson) {
        gestionPanier.add(p, com, typeCuisson);
    }

//    public void add(Menu m, HashMap<String, ChoixProduit> choix) {
//        gestionPanier.add(m, choix);
//    }
    public void subProduit(int index) {
        gestionPanier.subProduit(index);
    }
    
    public void vider() {
        gestionPanier.vider();
    }
    
    public int panierSize() {
        return gestionPanier.panierSize();
    }
    
    public void persistePanier(Tabla table) {
        gestionPanier.persistePanier(table);
    }
    
    public void validerMenu(Menu menu, Collection<Produit> choix, String commentaire) {
        gestionPanier.validerMenu(menu, choix, commentaire);
    }
    
    private GestionPanierLocal lookupGestionPanierLocal() {
        try {
            Context c = new InitialContext();
            return (GestionPanierLocal) c.lookup("java:global/restaurantEE/restaurantEE-ejb/GestionPanier!metiers.GestionPanierLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
}
