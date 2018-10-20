package controleurs.secondaires;

import entities.LigneCommande;
import entities.Menu;
import entities.Produit;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import metiers.GestionMenuLocal;
import metiers.GestionProduitLocal;
import utilitaire.PanierMenu;
import utilitaire.PanierWar;

public class OperationPanierMenuControleur implements Serializable, ControleurInterface {

    GestionProduitLocal gestionProduit = lookupGestionProduitLocal();
    GestionMenuLocal gestionMenu = lookupGestionMenuLocal();

    @Override
    public String executer(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        PanierMenu gestionPanierMenu = (PanierMenu) session.getAttribute("panierMenu");
        PanierWar gestionPanier = (PanierWar) session.getAttribute("panierWar");
        ArrayList<LigneCommande> produitsPanier = gestionPanier.getLigneCommandesProduits();

        request.setAttribute("produitsPanier", produitsPanier);
        String action = request.getParameter("action");
        String com = "null";
        Menu menu = null;
        if (request.getParameter("commentaire") != null) {
            com = request.getParameter("commentaire");
        }
        if (request.getParameter("menu") != null) {
            menu = (Menu) gestionMenu.MenuById(Long.valueOf(request.getParameter("menu")));
        }

        Long id = 0l;

        if (request.getParameter("id") != null) {
            id = Long.valueOf(request.getParameter("id"));
        }

        if ("add".equalsIgnoreCase(action)) {
            try {
                Produit p = gestionProduit.ProduitById(id);
//                gestionPanier.add(cat, p, com);
                gestionPanierMenu.add(p, com);
                request.setAttribute("choixProduits", gestionPanierMenu.getChoix().values());

            } catch (IllegalArgumentException ex) {

            }
            return "/FrontControleur?section=affichage-catalogue-menu&menuId=" + menu.getId();

        }

        if ("annuler".equalsIgnoreCase(action)) {
            gestionPanierMenu.vider();
            return "/FrontControleur?section=affichage-catalogue-menu";
        }

        return null;

    }

    private GestionMenuLocal lookupGestionMenuLocal() {
        try {
            Context c = new InitialContext();
            return (GestionMenuLocal) c.lookup("java:global/restaurantEE/restaurantEE-ejb/GestionMenu!metiers.GestionMenuLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private GestionProduitLocal lookupGestionProduitLocal() {
        try {
            Context c = new InitialContext();
            return (GestionProduitLocal) c.lookup("java:global/restaurantEE/restaurantEE-ejb/GestionProduit!metiers.GestionProduitLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
