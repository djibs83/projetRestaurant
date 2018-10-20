/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metiers;

import entities.Employe;
import entities.Facture;
import entities.LigneCommande;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author cdi205
 */
@Local
public interface GestionFactureLocal {

    public int getQuantite();

    public float getPrixTotalHT();

    public List<LigneCommande> getFacture();

    public void setFacture(List<LigneCommande> facture);

    public void add(LigneCommande lc) throws NullPointerException;

    public boolean supp(LigneCommande lc) throws NullPointerException;

    public void generateFacture(Employe serveur);

    public void persistFacture();

    public Facture getFacturePersiste();

    public void setFacturePersiste(Facture facturePersiste);

    public void mergeReglement();

    public void reinitFacture();
    
}
