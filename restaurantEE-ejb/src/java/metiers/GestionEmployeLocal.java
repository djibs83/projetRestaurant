/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metiers;

import entities.Employe;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author liliane
 */
@Local
public interface GestionEmployeLocal {

    public List<Employe> listEmploye();

    public Employe employeById(Long id);
    
}
