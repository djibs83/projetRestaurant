package metiers;

import entities.Client;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class GestionClient implements GestionClientLocal {

    @PersistenceContext(unitName = "restaurantEE-ejbPU")
    private EntityManager em;

    @Override
    public Client clientByEmail(String email) {
        System.out.println("------------->>>> email = "+email);
        String query = "SELECT c FROM Client c WHERE c.email LIKE :email";
        Query qr = em.createQuery(query);
        qr.setParameter("email", email);
        Client c = (Client) qr.getSingleResult();
        System.out.println(c);
        return c;
    }

    @Override
    public void persistClient(Client c01) {
        em.persist(c01);
    }

    @Override
    public void mergeClient(Client c01) {
        em.merge(c01);
    }   

}
