package controleurs.secondaires;

import entities.InterfaceAccueil;
import java.io.Serializable;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import metiers.GestionInterfaceLocal;

public class InterfaceGestionControleur implements Serializable, ControleurInterface {

    GestionInterfaceLocal gestionInterface = lookupGestionInterfaceLocal();

    private final HashMap<String, String> inclu;

    public InterfaceGestionControleur() {

        inclu = new HashMap<>();
        inclu.put("cuisine", "/FrontControleur?section=accueil-cuisine");
        inclu.put("serveur", "/FrontControleur?section=accueil-serveur");
        inclu.put("client", "/FrontControleur?section=accueil-client");
        inclu.put("caisse", "/FrontControleur?section=affichage-commande-table");
    }

    @Override
    public String executer(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();

        if (session.getAttribute("interface") == null) {
            session.setAttribute("interface", new InterfaceAccueil());
        }
        InterfaceAccueil ia = (InterfaceAccueil) session.getAttribute("interface");
        System.out.println(ia);

        String code = request.getParameter("code");

        if (code != null && !code.isEmpty()) {
            ia = gestionInterface.InterfaceAccueilById(code);
        }

        try {
            if (ia.getType() != null) {
                session.setAttribute("interface", ia);
            }
        } catch (Exception e) {
            return "/WEB-INF/home.jsp";
        }

        if ("exit".equalsIgnoreCase(request.getParameter("exit"))) {
            session.invalidate();
            ia.setCode(null);
            ia.setType(null);
        }

        if (inclu.containsKey(ia.getType())) {
            return inclu.get(ia.getType());
        }

        return "/WEB-INF/home.jsp";
    }

    private GestionInterfaceLocal lookupGestionInterfaceLocal() {
        try {
            Context c = new InitialContext();
            return (GestionInterfaceLocal) c.lookup("java:global/restaurantEE/restaurantEE-ejb/GestionInterface!metiers.GestionInterfaceLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
