package utilitaire;

import entities.LigneCommande;
import entities.Menu;
import entities.Produit;
import java.io.Serializable;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import metiers.GestionPanierMenuLocal;

public class PanierMenu implements Serializable {

    GestionPanierMenuLocal gestionPanierMenu = lookupGestionPanierMenuLocal();

    public PanierMenu() {
        gestionPanierMenu = lookupGestionPanierMenuLocal();
    }

    public HashMap<String, Produit> getChoix() {
        return gestionPanierMenu.getChoix();
    }

    public void add(Produit p, String com) {
        gestionPanierMenu.add(p, com);
    }

    public void vider() {
        gestionPanierMenu.vider();
    }

    public int choixSize() {
        return gestionPanierMenu.choixSize();
    }

    private GestionPanierMenuLocal lookupGestionPanierMenuLocal() {
        try {
            Context c = new InitialContext();
            return (GestionPanierMenuLocal) c.lookup("java:global/restaurantEE/restaurantEE-ejb/GestionPanierMenu!metiers.GestionPanierMenuLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
