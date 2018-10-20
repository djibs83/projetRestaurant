package metiers;

import entities.Tabla;
import java.util.HashMap;
import javax.ejb.Stateless;
import javax.servlet.http.HttpSession;

@Stateless
public class GestionSession implements GestionSessionLocal {

    private HashMap<Long, HttpSession> sessions = new HashMap<>();
    private HashMap<Long, GestionPanier> sessionsPanier = new HashMap<>();

    @Override
    public void sessionCreated(HttpSession session, Tabla table, GestionPanier panier) {
        sessionsPanier.put(table.getId(), panier);
        sessions.put(table.getId(), session);
    }


    @Override
    public void sessionDestroyed(HttpSession session) {
        Tabla table = (Tabla) session.getAttribute("table");
        sessions.remove(table.getId());
    }

    @Override
    public HttpSession find(Tabla table) {
        return sessions.get(table.getId());
    }

    @Override
    public GestionPanier findPanier(Tabla table) {
        return sessionsPanier.get(table.getId());
    }
}
