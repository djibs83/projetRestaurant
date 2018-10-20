package metiers;

import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class initBase {

    @PersistenceContext(unitName = "restaurantEE-ejbPU")
    private EntityManager em;
}
