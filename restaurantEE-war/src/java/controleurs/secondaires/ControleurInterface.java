package controleurs.secondaires;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ControleurInterface {
    public String executer(HttpServletRequest request, HttpServletResponse response);
    
    
}
