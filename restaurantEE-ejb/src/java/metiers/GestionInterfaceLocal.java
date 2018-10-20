package metiers;

import entities.InterfaceAccueil;
import java.util.List;
import javax.ejb.Local;

@Local
public interface GestionInterfaceLocal {

    public List<InterfaceAccueil> listInterfaceAccueil();

    public InterfaceAccueil InterfaceAccueilById(String id);
    
}
