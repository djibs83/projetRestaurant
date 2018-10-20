/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metiers;

import entities.Facture;
import entities.Reglement;
import entities.TypeReglement;
import java.util.HashMap;
import javax.ejb.Local;

/**
 *
 * @author cdi205
 */
@Local
public interface GestionReglementLocal {

    public void create(float montant, String typeRegKey, Facture facture);

    public Reglement getReglement();

    public void initHashMap();

    public HashMap<String, TypeReglement> getTypeReg();
    
}
