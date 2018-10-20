package controleurs.secondaires;

import entities.LigneCommande;
import entities.Menu;
import entities.Produit;
import entities.Tabla;
import entities.TypeCuisson;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import metiers.GestionMenuLocal;
import metiers.GestionPanierMenuLocal;
import metiers.GestionProduitLocal;
import utilitaire.PanierMenu;
import utilitaire.PanierWar;

public class OperationPanierControleur implements Serializable, ControleurInterface {

    GestionProduitLocal gestionProduit = lookupGestionProduitLocal();
    GestionMenuLocal gestionMenu = lookupGestionMenuLocal();

    @Override
    public String executer(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession();
        PanierWar gestionPanier = (PanierWar) session.getAttribute("panierWar");
        PanierMenu gestionPanierMenu = (PanierMenu) session.getAttribute("panierMenu");

        String type = request.getParameter("type");
        String action = request.getParameter("action");
        String com = request.getParameter("commentaire");

        TypeCuisson typeCuisson = null;
        if (request.getParameter("typeCuisson") != null) {
//            typeCuisson = request.getParameter("typeCuisson");
        }

        Long id = 0l;
        if (request.getParameter("id") != null) {
            id = Long.valueOf(request.getParameter("id"));
        }
        int index = 0;
        if (request.getParameter("index") != null) {
            index = Integer.valueOf(request.getParameter("index"));
        }

        if ("add".equalsIgnoreCase(action)) {
            try {
                if ("carte".equalsIgnoreCase(type)) {
                    Produit p = gestionProduit.ProduitById(id);
                    gestionPanier.add(p, com, typeCuisson);

                } else if ("menu".equalsIgnoreCase(type)) {
                    Menu menu = (Menu) gestionMenu.MenuById(Long.valueOf(request.getParameter("menuId")));
                    gestionPanier.validerMenu(menu, gestionPanierMenu.getChoix().values(), com);
                    gestionPanierMenu.vider();
                }

            } catch (IllegalArgumentException ex) {

            }
        }
        if ("commander".equalsIgnoreCase(action)) {
            gestionPanier.persistePanier((Tabla) session.getAttribute("table"));
            gestionPanier.vider();
        }

        if ("subProduit".equalsIgnoreCase(action)) {
            gestionPanier.subProduit(index);
        }

//        if ("subMenu".equalsIgnoreCase(action)) {
//            gestionPanier.subMenu(index);
//        }
        if ("vider".equalsIgnoreCase(action)) {
            gestionPanier.vider();
        }

        String origine = request.getParameter("origine");
        request.setAttribute("redirect", true);
        String page = "";
        String demande = request.getParameter("demande");
        if ("ajax-menu".equalsIgnoreCase(demande)) {
            page = "FrontControleur?section=includes&include=nav-main";
        } else {
            if ("catalogue".equalsIgnoreCase(origine)) {
                return page = "FrontControleur?section=affichage-catalogue-produit&categorieId=" + request.getParameter("categorieId");
            }

            if ("detail-panier".equalsIgnoreCase(origine)) {
                return page = "FrontControleur?section=affichage-panier";
            }
        }
        return page;
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
