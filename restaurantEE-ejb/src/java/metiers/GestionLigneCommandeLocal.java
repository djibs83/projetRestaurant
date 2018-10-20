/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metiers;

import entities.LigneCommande;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author cdi205
 */
@Local
public interface GestionLigneCommandeLocal {

    public void deplacerLC(int Commande, int LigneCommande);

    public List<LigneCommande> LigneCommandeByTabla(long tabla);

    public LigneCommande LigneCommandeById(long id);
    
}
