/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metiers;

import entities.Tabla;
import javax.ejb.Local;
import javax.jms.Session;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;

/**
 *
 * @author alex
 */
@Local
public interface GestionSessionLocal {

    public void sessionCreated(HttpSession session, Tabla table, GestionPanier panier);

    public void sessionDestroyed(HttpSession session);

    public HttpSession find(Tabla table);

    public GestionPanier findPanier(Tabla table);
    
}
