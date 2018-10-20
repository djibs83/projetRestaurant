package controleurs.secondaires.controleurServeur;

import controleurs.secondaires.ControleurInterface;
import entities.Commande;
import entities.Employe;
import entities.InterfaceAccueil;
import entities.Statut;
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
import metiers.GestionCommandeLocal;
import metiers.GestionEmployeLocal;
import metiers.GestionStatutLocal;
import metiers.GestionTableLocal;

public class AffichageServeurTableCommande implements Serializable, ControleurInterface {
    
    

    @Override
    public String executer(HttpServletRequest request, HttpServletResponse response) {

        // Ne pas toucher cette partie, sert à verifier l'acces a cette interface.
        HttpSession session = request.getSession();
        InterfaceAccueil ia = (InterfaceAccueil) session.getAttribute("interface");

        if (!"serveur".equalsIgnoreCase(ia.getType())) {
            return "/FrontControleur?section=interface-gestion";
        }
        //////////////////////////////////Coder en dessous////////////////////////////////////////////
        GestionEmployeLocal gestionEmploye = lookupGestionEmployeLocal();
        Long serveurId = Long.valueOf(request.getParameter("serveurId"));
        Employe employe = gestionEmploye.employeById(serveurId);
        System.out.println(employe);
        request.setAttribute("employe", employe);
        
        GestionTableLocal gestionTable = lookupGestionTableLocal();
        Long tableId = Long.valueOf(request.getParameter("tableId"));
        Tabla table = gestionTable.tableById(tableId);
        System.out.println(table);
        request.setAttribute("table", table);
        
        GestionStatutLocal gestionStatut = lookupGestionStatutLocal();
        Statut statut = gestionStatut.statutByNumero(90);
        GestionCommandeLocal gestionCommande = lookupGestionCommandeLocal();
        List<Commande> commandes = gestionCommande.commandeByTablaStatus(tableId,statut );
        request.setAttribute("commandes", commandes);
        

        
        
        
        
        
        
        
        
        return "/WEB-INF/serveur/serveurTableCommande.jsp";
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

    private GestionCommandeLocal lookupGestionCommandeLocal() {
        try {
            Context c = new InitialContext();
            return (GestionCommandeLocal) c.lookup("java:global/restaurantEE/restaurantEE-ejb/GestionCommande!metiers.GestionCommandeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private GestionStatutLocal lookupGestionStatutLocal() {
        try {
            Context c = new InitialContext();
            return (GestionStatutLocal) c.lookup("java:global/restaurantEE/restaurantEE-ejb/GestionStatut!metiers.GestionStatutLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
