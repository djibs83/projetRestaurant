/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metiers;

import entities.Employe;
import entities.Tabla;
import java.util.Collection;
import java.util.HashMap;
import javax.ejb.Local;

/**
 *
 * @author liliane
 */
@Local
public interface GestionAttributionTableServeurLocal {

    public void resetPanierTable(Employe emp);

    public void addTable(Employe emp, Tabla tabla);

    public void deleteTable(Employe emp, Tabla tabla);

    public Collection<Tabla> getMaListeTables(Employe emp);

    public int getMaListeTablesSize(Employe emp);

    public HashMap<Employe, Collection<Tabla>> getMaListeTables();

   
    
    
}
