/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleurs.secondaires.controleurCaisse;

import controleurs.secondaires.ControleurInterface;
import utilitaire.FactureWar;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import metiers.GestionReglementLocal;

/**
 *
 * @author cdi205
 */
public class AffichageReglement implements Serializable, ControleurInterface{
    GestionReglementLocal gestionReglement = lookupGestionReglementLocal();

    
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
        HttpSession session = request.getSession();
        if(session.getAttribute("factureWar") == null){
            session.setAttribute("factureWar", new FactureWar());
        }
        
        FactureWar fw = (FactureWar) session.getAttribute("factureWar");
        request.setAttribute("facture", fw.getFacturePersiste());
        String choix = request.getParameter("choice");
        if("cb".equalsIgnoreCase(choix)){
            return "/WEB-INF/caisse/paiementCB.jsp";
        } else if("espèce".equalsIgnoreCase(choix)) {
            return "/WEB-INF/caisse/paiementEspece.jsp";
        } else if("Chèque".equalsIgnoreCase(choix)) {
            return "/WEB-INF/caisse/paiementCheque.jsp";
        }
        fw.persist();
        gestionReglement.initHashMap();
        request.setAttribute("typereg", gestionReglement.getTypeReg());
//        System.out.println(gestionReglement.getReglement());
        request.setAttribute("prix", fw.getPrixTotalHT());
        return "/WEB-INF/caisse/reglement.jsp";
    }

    private GestionReglementLocal lookupGestionReglementLocal() {
        try {
            Context c = new InitialContext();
            return (GestionReglementLocal) c.lookup("java:global/restaurantEE/restaurantEE-ejb/GestionReglement!metiers.GestionReglementLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }



    
}
    
    


