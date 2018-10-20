package controleurs;

import controleurs.secondaires.ControleurInterface;
import entities.Client;
import entities.InterfaceAccueil;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import metiers.GestionAttributionTableServeur;
import metiers.GestionAttributionTableServeurLocal;
import metiers.GestionInterfaceLocal;
import metiers.GestionProduitLocal;
import services.jeuDeDonneesLocal;
import utilitaire.PanierMenu;
import utilitaire.PanierWar;

public class FrontControleur extends HttpServlet {

    private HashMap<String, ControleurInterface> mp;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config); //To change body of generated methods, choose Tools | Templates.
        mp = new HashMap<>();
//        mp.put("menu-main",new MenuControleur());
        Enumeration<String> clefs = config.getInitParameterNames();
        while (clefs.hasMoreElements()) {
            String cle = clefs.nextElement();
            String value = config.getInitParameter(cle);

            try {
                ControleurInterface ci = (ControleurInterface) Class.forName(value).newInstance();
                mp.put(cle, ci);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FrontControleur.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(FrontControleur.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(FrontControleur.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @EJB
    private jeuDeDonneesLocal jeuDeDonnees;

    @EJB
    private GestionProduitLocal gestionProduit;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();

        String section = request.getParameter("section");
        String page = "/FrontControleur?section=interface-gestion";

        if (session.getAttribute("panierWar") == null) {
            session.setAttribute("panierWar", new PanierWar());
        }

        PanierWar gestionPanier = (PanierWar) session.getAttribute("panierWar");

        if (session.getAttribute("panierMenu") == null) {
            session.setAttribute("panierMenu", new PanierMenu());
        }
        PanierMenu gestionPanierMenu = (PanierMenu) session.getAttribute("PanierMenu");

        if (mp.containsKey(section)) {
            ControleurInterface ci = mp.get(section);
            page = ci.executer(request, response);
        }
        if ("jeuDeTeste".equalsIgnoreCase(request.getParameter("section"))) {
            jeuDeDonnees.insererJeuEssai();
        }

        page = response.encodeURL(page);

        Boolean b = false;
        b = (Boolean) request.getAttribute("redirect");

        if (b == null || !b) {
            getServletContext().getRequestDispatcher(page).include(request, response);
        } else {
            response.sendRedirect(page);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
