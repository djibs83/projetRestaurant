/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilitaire;

import entities.Employe;
import entities.Facture;
import entities.LigneCommande;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import metiers.GestionFactureLocal;

/**
 *
 * @author cdi205
 */
public class FactureWar implements Serializable {

    GestionFactureLocal gestionFacture = lookupGestionFactureLocal();

    public FactureWar() {
        gestionFacture = lookupGestionFactureLocal();
    }

    public List<LigneCommande> getFacture() {
        return gestionFacture.getFacture();
    }

    public int getQuantite() {
        return gestionFacture.getQuantite();
    }

    public float getPrixTotalHT() {
        return gestionFacture.getPrixTotalHT();
    }

    public void add(LigneCommande lc) throws NullPointerException {
        gestionFacture.add(lc);
    }

    public void supp(LigneCommande lc) throws NullPointerException {
        System.out.println(gestionFacture.supp(lc));
    }

    public void gen(Employe serveur) throws NullPointerException {
        gestionFacture.generateFacture(serveur);
    }

    public void persist() throws NullPointerException {
        gestionFacture.persistFacture();
    }

    public void generateFacture(Employe serveur) {
        gestionFacture.generateFacture(serveur);
    }
    
    public Facture getFacturePersiste() {
        return gestionFacture.getFacturePersiste();
    }
    
    public void reinitFacture(){
        gestionFacture.reinitFacture();
    }
    
    public void mergeReglement() {
        gestionFacture.mergeReglement();
    }
    
    public void setFacture(List<LigneCommande> lc){
        gestionFacture.setFacture(lc);
    }
    private GestionFactureLocal lookupGestionFactureLocal() {
        try {
            Context c = new InitialContext();
            return (GestionFactureLocal) c.lookup("java:global/restaurantEE/restaurantEE-ejb/GestionFacture!metiers.GestionFactureLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
