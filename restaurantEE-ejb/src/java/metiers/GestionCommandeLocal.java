/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metiers;

import entities.Commande;
import entities.Statut;
import entities.Tabla;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author cdi205
 */
@Local
public interface GestionCommandeLocal {

    public Commande commandeById(int id);

    public Commande commandeByClient(int Client);

    public List<Commande> commandeByTabla(long tabla);

    public void validerCommande();

    public void confirmerCommande();

    public Commande modifierCommande();

    public List<Commande> commandeByTablaStatus(long tabla, Statut status);

   
    
}
