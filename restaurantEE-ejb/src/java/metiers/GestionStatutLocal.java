/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metiers;

import entities.Statut;
import javax.ejb.Local;

/**
 *
 * @author liliane
 */
@Local
public interface GestionStatutLocal {

    public Statut statutByNumero(int numero);
    
}
