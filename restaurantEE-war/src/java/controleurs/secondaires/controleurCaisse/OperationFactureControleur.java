/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleurs.secondaires.controleurCaisse;

import controleurs.secondaires.ControleurInterface;
import entities.Employe;
import entities.LigneCommande;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import metiers.GestionEmployeLocal;
import metiers.GestionLigneCommandeLocal;
import utilitaire.FactureWar;

/**
 *
 * @author cdi205
 */
public class OperationFactureControleur implements Serializable, ControleurInterface {

    GestionLigneCommandeLocal gestionLigneCommande = lookupGestionLigneCommandeLocal();

    @Override
    public String executer(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        FactureWar fw = (FactureWar) session.getAttribute("factureWar");
        String action = request.getParameter("action");
        Employe serveur = (Employe) session.getAttribute("serveur");
        if ("add".equalsIgnoreCase(action)) {
            long ref = Long.valueOf(request.getParameter("ref"));
            try {
                LigneCommande lc = gestionLigneCommande.LigneCommandeById(ref);
                fw.add(lc);
            } catch (IllegalArgumentException ex) {

            }
        }

        if ("supp".equalsIgnoreCase(action)) {
            long ref = Long.valueOf(request.getParameter("ref"));
            System.out.println(ref);
            try {
                LigneCommande lc = gestionLigneCommande.LigneCommandeById(ref);
                fw.supp(lc);
            } catch (IllegalArgumentException ex) {

            }
        }
        String page = ""; // pas bien .....
        if ("gen".equalsIgnoreCase(action)) {
            fw.gen(serveur);
            return page = "/FrontControleur?section=affichage-facture";
        }
        
        if ("pers".equalsIgnoreCase(action)) {
            fw.generateFacture(serveur);
            fw.persist();
            return page = "/FrontControleur?section=affichage-facture";
        }
        
        if("addAll".equalsIgnoreCase(action)){
            fw.setFacture(gestionLigneCommande.LigneCommandeByTabla(Long.valueOf(request.getParameter("table"))));
            return page = "/FrontControleur?section=affichage-commande-table&table" + request.getParameter("table");
        }

        String origine = request.getParameter("origine");
        request.setAttribute("redirect", true);

        
        //String demande = request.getParameter("demande");

        if ("afficherCommande".equalsIgnoreCase(origine)) {
            Long table = (Long) session.getAttribute("table");
            if(table == null){
                return page = "FrontControleur?section=affichage-commande-table";
            } else {
                return page = "FrontControleur?section=affichage-commande-table&table="+table;
            }
        }

        if ("detail-panier".equalsIgnoreCase(origine)) {
            return page = "FrontControleur?section=affichage-panier";
        }

        return page;
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
