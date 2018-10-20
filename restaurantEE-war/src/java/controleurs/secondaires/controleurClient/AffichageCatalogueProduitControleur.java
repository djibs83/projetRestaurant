package controleurs.secondaires.controleurClient;

import controleurs.secondaires.ControleurInterface;
import entities.CategorieProduit;
import entities.InterfaceAccueil;
import entities.LigneCommande;
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
import metiers.GestionProduitLocal;
import utilitaire.PanierWar;

public class AffichageCatalogueProduitControleur implements Serializable, ControleurInterface {

    GestionCategorieProduitLocal gestionCategorieProduit = lookupGestionCategorieProduitLocal();
    GestionProduitLocal gestionProduit = lookupGestionProduitLocal();

    @Override
    public String executer(HttpServletRequest request, HttpServletResponse response) {

        // Ne pas toucher cette partie, sert à verifier l'acces a cette interface.
        HttpSession session = request.getSession();
        InterfaceAccueil ia = (InterfaceAccueil) session.getAttribute("interface");
        response.setContentType("text/html;charset=UTF-8");

        if (!"client".equalsIgnoreCase(ia.getType())) {
            return "/FrontControleur?section=interface-gestion";
        }
        //////////////////////////////Coder en dessous//////////////////////////////////////////
        List<CategorieProduit> catProduits = gestionCategorieProduit.listCategorieProduits();
        CategorieProduit catProduit = null;

        if (request.getParameter("categorieId") != null) {
            catProduit = gestionCategorieProduit.categorieById(Long.valueOf(request.getParameter("categorieId")));
        } else {
            catProduit = catProduits.get(0);
        }

        PanierWar gestionPanier = (PanierWar) session.getAttribute("panierWar");
        ArrayList<LigneCommande> produitsPanier = gestionPanier.getLigneCommandesProduits();
        List<Produit> lproduits = gestionProduit.listCarteProduits(catProduit);
        System.out.println(lproduits);

        request.setAttribute("catProduit", catProduit);
        request.setAttribute("catProduits", catProduits);
        request.setAttribute("lproduits", lproduits);
        request.setAttribute("produitsPanier", produitsPanier);
        request.setAttribute("totalHT", gestionPanier.getPrixTotalHT());

        return "/WEB-INF/carte/catalogueProduit.jsp";
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
