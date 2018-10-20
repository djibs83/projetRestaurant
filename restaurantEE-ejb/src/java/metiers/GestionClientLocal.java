package metiers;

import entities.Client;
import javax.ejb.Local;

@Local
public interface GestionClientLocal {

    public void persistClient(Client c01);

    public void mergeClient(Client c01);

    public Client clientByEmail(String email);

}
