package controleurs.secondaires.controleurClient;

import controleurs.secondaires.ControleurInterface;
import entities.InterfaceAccueil;
import entities.Tabla;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import metiers.GestionTableLocal;

public class AffichageAccueilClientControleur implements Serializable, ControleurInterface {

    GestionTableLocal gestionTable = lookupGestionTableLocal();

    @Override
    public String executer(HttpServletRequest request, HttpServletResponse response) {

        // Ne pas toucher cette partie, sert à verifier l'acces a cette interface.
        HttpSession session = request.getSession();
        InterfaceAccueil ia = (InterfaceAccueil) session.getAttribute("interface");

        if (!"client".equalsIgnoreCase(ia.getType())) {
            return "/FrontControleur?section=interface-gestion";
        }
        //////////////////////////////Coder en dessous//////////////////////////////////////////

        if (session.getAttribute("table") == null) {
            session.setAttribute("table", new Tabla());
        }
        Tabla table = (Tabla) session.getAttribute("table");

        if (table.getId() == null) {
            if (request.getParameter("tableId") != null) {
                Long id = Long.valueOf(request.getParameter("tableId"));
                table = gestionTable.tableById(id);
                session.setAttribute("table", table);

            } else {
                request.setAttribute("tables", gestionTable.listTable());
                return "/WEB-INF/client/tableSelection.jsp";
            }
        }

        return "/WEB-INF/client/menuClient.jsp";
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

}
