package controleurs.secondaires.controleurServeur;

import controleurs.secondaires.ControleurInterface;
import entities.Employe;
import entities.InterfaceAccueil;
import entities.Tabla;
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
import metiers.GestionAttributionTableServeur;
import metiers.GestionAttributionTableServeurLocal;
import metiers.GestionEmployeLocal;
import metiers.GestionTableLocal;

public class AffichageAccueilServeurControleur implements Serializable, ControleurInterface {

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
        if (session.getAttribute("serveur") == null) {
            session.setAttribute("serveur", new Employe());
        }
        Employe serveur = (Employe) session.getAttribute("serveur");

        if (request.getParameter("serveurId") != null) {
            Long id = Long.valueOf(request.getParameter("serveurId"));
            serveur = gestionEmploye.employeById(id);

            System.out.println(serveur);
        }
        if (serveur.getNom() != null) {
            Boolean test = false;
            if (!gestionAttributionTableServeur.getMaListeTables().containsKey(serveur)) {
                gestionAttributionTableServeur.getMaListeTables().put(serveur, new ArrayList<>());
            }
            request.setAttribute("employe", serveur);
            List<Tabla> tables = gestionTable.listTable();
            request.setAttribute("tables", tables);
            session.setAttribute("serveur", serveur);

            request.setAttribute("nbTable", gestionAttributionTableServeur.getMaListeTablesSize(serveur));
            System.out.println(gestionAttributionTableServeur);

            if (request.getParameter("tableId") != null) {
                System.out.println("BEFORE LE BIG IF");
                if ("add".equalsIgnoreCase(request.getParameter("action"))) {

                    gestionAttributionTableServeur.addTable(serveur, gestionTable.tableById(Long.valueOf(request.getParameter("tableId"))));
                    request.setAttribute("nbTable", gestionAttributionTableServeur.getMaListeTablesSize(serveur));
                    System.out.println(" RESULATO " + gestionAttributionTableServeur.getMaListeTablesSize(serveur));
                }

                Long tableid = Long.valueOf(request.getParameter("tableId"));
                Tabla tableadd = gestionTable.tableById(tableid);
                request.setAttribute("tableId", tableadd);

            }

            return "/WEB-INF/serveur/pageServeur.jsp";
        } else {
            System.out.println(" serveur null dans le elses");
            List<Employe> employes = gestionEmploye.listEmploye();
            request.setAttribute("employes", employes);
            System.out.println("POPO");

            return "/WEB-INF/serveur/accueilServeur.jsp";

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

    private GestionEmployeLocal lookupGestionEmployeLocal() {
        try {
            Context c = new InitialContext();
            return (GestionEmployeLocal) c.lookup("java:global/restaurantEE/restaurantEE-ejb/GestionEmploye!metiers.GestionEmployeLocal");
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
