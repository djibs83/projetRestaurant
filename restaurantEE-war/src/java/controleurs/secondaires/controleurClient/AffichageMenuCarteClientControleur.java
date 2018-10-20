package controleurs.secondaires.controleurClient;

import controleurs.secondaires.ControleurInterface;
import entities.Client;
import entities.Commande;
import entities.Tabla;
import java.io.Serializable;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import metiers.GestionClientLocal;
import utilitaire.PanierWar;

public class AffichageMenuCarteClientControleur implements Serializable, ControleurInterface {

    GestionClientLocal gestionClient = lookupGestionClientLocal();

    @Override
    public String executer(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();

        Tabla table = (Tabla) session.getAttribute("table");

        PanierWar gestionPanier = (PanierWar) session.getAttribute("panierWar");

        if (session.getAttribute("client") == null) {
            session.setAttribute("client", new Client());
        }
        Client client = (Client) session.getAttribute("client");

        if (session.getAttribute("commande") == null) {
            session.setAttribute("commande", new Commande());
        }
        Commande commande = (Commande) session.getAttribute("commande");
        gestionPanier.setCommande(commande);
        commande.setTabla(table);

        String valider = request.getParameter("valider");

        if (valider != null) {
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            String email = request.getParameter("email");
            String telephone = request.getParameter("telephone");
            Date d01 = new Date();

            if (!email.isEmpty()) {
                try {
                    client = gestionClient.clientByEmail(email);
                    if (!nom.isEmpty()) {
                        client.setNom(nom);
                    }
                    if (!prenom.isEmpty()) {
                        client.setPrenom(prenom);
                    }
                    if (!telephone.isEmpty()) {
                        client.setTelephone(telephone);
                    }
                    if (!email.isEmpty()) {
                        client.setTelephone(email);
                    }
                    gestionClient.mergeClient(client);

                } catch (Exception e) {
                    client.setNom(nom);
                    client.setPrenom(prenom);
                    client.setEmail(email);
                    client.setTelephone(telephone);
                    client.setDateEnregistrement(d01);
                    gestionClient.persistClient(client);
                }
                commande.setClient(client);
            }

            if (client.getEmail() != null) {
                if (client.getNom().isEmpty()) {
                    request.setAttribute("nom", client.getNom());
                } else {
                    request.setAttribute("nom", "invité");
                }
                commande.setNomClient(client.getNom());
            } else if (!nom.isEmpty()) {
                request.setAttribute("nom", nom);
                commande.setNomClient(nom);
            } else {
                request.setAttribute("nom", "invité");
                commande.setNomClient("invité");
            }
        }
        return "/WEB-INF/client/menuClient.jsp";
    }

    private GestionClientLocal lookupGestionClientLocal() {
        try {
            Context c = new InitialContext();
            return (GestionClientLocal) c.lookup("java:global/restaurantEE/restaurantEE-ejb/GestionClient!metiers.GestionClientLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
