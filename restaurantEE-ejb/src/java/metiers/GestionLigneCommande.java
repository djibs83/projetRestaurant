/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metiers;

import entities.LigneCommande;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author cdi205
 */
@Stateless
public class GestionLigneCommande implements GestionLigneCommandeLocal {
    @PersistenceContext(unitName = "restaurantEE-ejbPU")
    private EntityManager em;

    @Override
    public void deplacerLC(int Commande, int LigneCommande) {
		throw new UnsupportedOperationException();
	}
    
    
    @Override
    public List<LigneCommande> LigneCommandeByTabla(long tabla){
        String query = "SELECT lc FROM LigneCommande lc WHERE lc.commande.tabla.numero = :id";
        Query qr = em.createQuery(query);
        qr.setParameter("id", tabla);
        //qr.setParameter("statut", 0);
        System.out.println();
        return qr.getResultList();
    }
    
    @Override
    public LigneCommande LigneCommandeById(long id){
        String query = "SELECT lc FROM LigneCommande lc WHERE lc.id = :id";
        Query qr = em.createQuery(query);
        qr.setParameter("id", id);
        return (LigneCommande) qr.getResultList().get(0);
    }
    
    
}
