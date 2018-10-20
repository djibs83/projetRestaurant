package controleurs.secondaires.controleurCuisine;

import controleurs.secondaires.ControleurInterface;
import entities.InterfaceAccueil;
import entities.LigneCommande;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import metiers.GestionCuisineLocal;
import metiers.GestionLigneCommandeLocal;

public class AffichageCuisineControleur implements Serializable, ControleurInterface {

    GestionLigneCommandeLocal gestionLigneCommande = lookupGestionLigneCommandeLocal();
    GestionCuisineLocal gestionCuisine = lookupGestionCuisineLocal();

    @Override
    public String executer(HttpServletRequest request, HttpServletResponse response) {
        // Ne pas toucher cette partie, sert à verifier l'acces a cette interface.
        HttpSession session = request.getSession();
        InterfaceAccueil ia = (InterfaceAccueil) session.getAttribute("interface");

        if (!"cuisine".equalsIgnoreCase(ia.getType())) {
            return "/FrontControleur?section=interface-gestion";
        }
        ///////////////////////////////Coder en dessous///////////////////////////////////////////////
        
        String action = request.getParameter("action");
        String page = "";

        List<LigneCommande> ligneCommandes = gestionCuisine.listLigneCommandes();
        request.setAttribute("ligneCommandes", ligneCommandes);

        if ("pers".equalsIgnoreCase(action)) {
            long id = Long.valueOf(request.getParameter("id"));
            LigneCommande lc = gestionLigneCommande.LigneCommandeById(id);
            gestionCuisine.persistLigneCommande(lc);
        }

        return "/WEB-INF/cuisine/accueilCuisine.jsp";
    }

    private GestionCuisineLocal lookupGestionCuisineLocal() {
        try {
            Context c = new InitialContext();
            return (GestionCuisineLocal) c.lookup("java:global/restaurantEE/restaurantEE-ejb/GestionCuisine!metiers.GestionCuisineLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }

    }

    private GestionLigneCommandeLocal lookupGestionLigneCommandeLocal() {
        try {
            Context c = new InitialContext();
            return (GestionLigneCommandeLocal) c.lookup("java:global/restaurantEE/restaurantEE-ejb/GestionLigneCommande!metiers.GestionLigneCommandeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
