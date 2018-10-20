package controleurs.secondaires;

import entities.LigneCommande;
import java.io.Serializable;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utilitaire.PanierWar;

public class AffichagePanierControleur implements Serializable, ControleurInterface{

    @Override
    public String executer(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        PanierWar gestionPanier = (PanierWar) session.getAttribute("panierWar");
        ArrayList<LigneCommande> produits = gestionPanier.getLigneCommandesProduits();
//        ArrayList<LigneMenu> menus = gestionPanier.getPanierMenu();
        
        request.setAttribute("produits", produits);
//        request.setAttribute("menus", menus);
        request.setAttribute("totalHT", gestionPanier.getPrixTotalHT());
        
        if ("confirmer".equalsIgnoreCase(request.getParameter("confirmer"))){
//            Commande commande = (Commande) session.getAttribute("commande");
//            gestionPanier.persistePanier();
            
            
        }
        
        return "/WEB-INF/detailPanier.jsp";
        
        
    }
    
}
