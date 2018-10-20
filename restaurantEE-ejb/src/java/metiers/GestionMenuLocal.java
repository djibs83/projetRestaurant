package metiers;

import entities.Menu;
import java.util.List;
import javax.ejb.Local;

@Local
public interface GestionMenuLocal {

    public List<Menu> listMenus();

    public Menu MenuById(Long id);
    
}
