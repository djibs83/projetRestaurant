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
import metiers.GestionCommandeLocal;
import metiers.GestionLigneCommandeLocal;

/**
 *
 * @author cdi205
 */
public class AffichageFacture implements Serializable, ControleurInterface{

    
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
        return "/WEB-INF/caisse/afficherFacture.jsp";
    }



    
}
    
    


