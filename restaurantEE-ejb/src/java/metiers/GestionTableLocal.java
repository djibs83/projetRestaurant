package metiers;

import entities.Tabla;
import java.util.List;
import javax.ejb.Local;

@Local
public interface GestionTableLocal {

    public List<Tabla> listTable();

    public Tabla tableById(Long id);

}
