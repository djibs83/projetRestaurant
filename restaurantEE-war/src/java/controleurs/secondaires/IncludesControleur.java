package controleurs.secondaires;

import java.io.Serializable;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utilitaire.PanierWar;

public class IncludesControleur implements Serializable, ControleurInterface {

    private HashMap<String, String> inclu;

    public IncludesControleur() {

        inclu = new HashMap<>();
        inclu.put("header", "/WEB-INF/includes/head.jsp");
        inclu.put("footer", "/WEB-INF/includes/footer.jsp");
        inclu.put("nav-main", "/WEB-INF/includes/nav-main.jsp");
    }
    
    @Override
    public String executer(HttpServletRequest request, HttpServletResponse response) {

        String page = "/WEB-INF/home.jsp";
        String inc = request.getParameter("include");

        HttpSession session = request.getSession();
        PanierWar gestionPanier = (PanierWar) session.getAttribute("panierWar");
        request.setAttribute("infoPanier", gestionPanier.panierSize() + " articles");


        if (inclu.containsKey(inc)) {
            page = inclu.get(inc);
        }

        return page;
    }
}
