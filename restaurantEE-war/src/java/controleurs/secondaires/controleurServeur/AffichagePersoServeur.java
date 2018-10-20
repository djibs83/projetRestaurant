package controleurs.secondaires.controleurServeur;

import controleurs.secondaires.ControleurInterface;
import entities.Employe;
import entities.InterfaceAccueil;
import entities.Tabla;
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
import metiers.GestionAttributionTableServeurLocal;
import metiers.GestionEmployeLocal;
import metiers.GestionTableLocal;

public class AffichagePersoServeur implements Serializable, ControleurInterface {

    GestionAttributionTableServeurLocal gestionAttributionTableServeur = lookupGestionAttributionTableServeurLocal();
    GestionEmployeLocal gestionEmploye = lookupGestionEmployeLocal();
    GestionTableLocal gestionTable = lookupGestionTableLocal();

    @Override
    public String executer(HttpServletRequest request, HttpServletResponse response) {

        // Ne pas toucher cette partie, sert à verifier l'acces a cette interface.
        HttpSession session = request.getSession();
        InterfaceAccueil ia = (InterfaceAccueil) session.getAttribute("interface");
        if (!"serveur".equalsIgnoreCase(ia.getType())) {
            return "/FrontControleur?section=interface-gestion";
        }
        //////////////////////////////////Coder en dessous////////////////////////////////////////////
        return null;

    }

    private GestionEmployeLocal lookupGestionEmployeLocal() {
        try {
            Context c = new InitialContext();
            return (GestionEmployeLocal) c.lookup("java:global/restaurantEE/restaurantEE-ejb/GestionEmploye!metiers.GestionEmployeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private GestionTableLocal lookupGestionTableLocal() {
        try {
            Context c = new InitialContext();
            return (GestionTableLocal) c.lookup("java:global/restaurantEE/restaurantEE-ejb/GestionTable!metiers.GestionTableLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private GestionAttributionTableServeurLocal lookupGestionAttributionTableServeurLocal() {
        try {
            Context c = new InitialContext();
            return (GestionAttributionTableServeurLocal) c.lookup("java:global/restaurantEE/restaurantEE-ejb/GestionAttributionTableServeur!metiers.GestionAttributionTableServeurLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
