/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleurs.secondaires.controleurCaisse;

import controleurs.secondaires.ControleurInterface;
import entities.InterfaceAccueil;
import utilitaire.FactureWar;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import metiers.GestionEmployeLocal;
import metiers.GestionLigneCommandeLocal;

/**
 *
 * @author cdi205
 */
public class AffichageCommandeTable implements Serializable, ControleurInterface{
    GestionEmployeLocal gestionEmploye = lookupGestionEmployeLocal();
    GestionLigneCommandeLocal gestionLigneCommande = lookupGestionLigneCommandeLocal();

    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    public String executer(HttpServletRequest request, HttpServletResponse response){ 
        
        response.setContentType("text/html;charset=UTF-8");

        // Ne pas toucher cette partie, sert à verifier l'acces a cette interface.
        HttpSession session = request.getSession();
        InterfaceAccueil ia = (InterfaceAccueil) session.getAttribute("interface");

        if (!"caisse".equalsIgnoreCase(ia.getType()) && !"serveur".equalsIgnoreCase(ia.getType())) {
            return "/FrontControleur?section=interface-gestion";
        }
        ///////////////////////////////Coder en dessous///////////////////////////////////////////////

        if(session.getAttribute("factureWar") == null){
            session.setAttribute("factureWar", new FactureWar());
        }
        
        System.out.println(">>>>!"+ request.getParameter("employe"));
        if(request.getParameter("employe") != null){
            long id = Long.valueOf(request.getParameter("employe"));
            session.setAttribute("serveur", gestionEmploye.employeById(id));
        }
        
        if(session.getAttribute("serveur") == null){
            request.setAttribute("liste",gestionEmploye.listEmploye());
            return "/WEB-INF/caisse/choixEmploye.jsp";
        }
        
        FactureWar gestionPanier = (FactureWar) session.getAttribute("factureWar");
        request.setAttribute("facture", gestionPanier.getFacture());
        if(request.getParameter("table") != null){
            long tabla = Long.valueOf(request.getParameter("table"));
            System.out.println(gestionLigneCommande.LigneCommandeByTabla(tabla));
            session.setAttribute("table", tabla);
            request.setAttribute("commandes", gestionLigneCommande.LigneCommandeByTabla(tabla));
        } else {
            return "/WEB-INF/caisse/accueil.jsp";
        }
        return "/WEB-INF/caisse/afficherCommande.jsp";
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

    private GestionEmployeLocal lookupGestionEmployeLocal() {
        try {
            Context c = new InitialContext();
            return (GestionEmployeLocal) c.lookup("java:global/restaurantEE/restaurantEE-ejb/GestionEmploye!metiers.GestionEmployeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    
}
    
    


