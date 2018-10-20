package controleurs.secondaires.controleurClient;

import controleurs.secondaires.ControleurInterface;
import entities.CategorieProduit;
import entities.InterfaceAccueil;
import entities.LigneCommande;
import entities.Menu;
import entities.Produit;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import metiers.GestionCategorieProduitLocal;
import metiers.GestionMenuLocal;
import metiers.GestionProduitLocal;
import utilitaire.PanierWar;

public class AffichageCatalogueMenuControleur implements Serializable, ControleurInterface {
    
    GestionCategorieProduitLocal gestionCategorieProduit = lookupGestionCategorieProduitLocal();
    GestionProduitLocal gestionProduit = lookupGestionProduitLocal();
    GestionMenuLocal gestionMenu = lookupGestionMenuLocal();
    

    @Override
    public String executer(HttpServletRequest request, HttpServletResponse response) {

        // Ne pas toucher cette partie, sert à verifier l'acces a cette interface.
        HttpSession session = request.getSession();
        InterfaceAccueil ia = (InterfaceAccueil) session.getAttribute("interface");

        if (!"client".equalsIgnoreCase(ia.getType())) {
            return "/FrontControleur?section=interface-gestion";
        }
        //////////////////////////////Coder en dessous//////////////////////////////////////////

        PanierWar gestionPanier = (PanierWar) session.getAttribute("panierWar");
        ArrayList<LigneCommande> produitsPanier = gestionPanier.getLigneCommandesProduits();
//        ArrayList<LigneMenu> menusPanier = gestionPanier.getPanierMenu();
        List<Menu> menus = gestionMenu.listMenus();
        List<Produit> lproduits = gestionProduit.listProduits();

        request.setAttribute("lproduits", lproduits);
        request.setAttribute("produitsPanier", produitsPanier);
//        request.setAttribute("menusPanier", menusPanier);
        request.setAttribute("menus", menus);
        request.setAttribute("totalHT", gestionPanier.getPrixTotalHT());

        Long id = 0l;

        if (request.getParameter("menuId") != null) {
            id = Long.valueOf(request.getParameter("menuId"));
            request.setAttribute("menu", gestionMenu.MenuById(id));
            List<CategorieProduit> catProduits = gestionCategorieProduit.listCategorieProduitsByMenu(gestionMenu.MenuById(id));
            request.setAttribute("catProduits", catProduits);

            return "/WEB-INF/carte/choixProduitMenu.jsp";
        } else {
            return "/WEB-INF/carte/catalogueMenu.jsp";
        }

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

    private GestionCategorieProduitLocal lookupGestionCategorieProduitLocal() {
        try {
            Context c = new InitialContext();
            return (GestionCategorieProduitLocal) c.lookup("java:global/restaurantEE/restaurantEE-ejb/GestionCategorieProduit!metiers.GestionCategorieProduitLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
