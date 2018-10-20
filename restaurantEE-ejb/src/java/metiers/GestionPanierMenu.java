package metiers;

import entities.LigneCommande;
import entities.Produit;
import java.util.HashMap;
import javax.annotation.PostConstruct;
import javax.ejb.Stateful;

@Stateful
public class GestionPanierMenu implements GestionPanierMenuLocal {

    private HashMap<String, Produit> choix;

    @PostConstruct
    public void init() {
        choix = new HashMap<>();
    }

    @Override
    public HashMap<String, Produit> getChoix() {
        return choix;
    }

    @Override
    public void add(Produit p, String com) {
        if (p == null) {
            throw new NullPointerException("produit null!");
        }
        LigneCommande lc = new LigneCommande(false, p, null, com);
        choix.put(p.getSousCategorie().getCategorieProduit().getLibelle(), p);
    }

    @Override
    public void vider() {
        choix.clear();
    }

    @Override
    public int choixSize() {
        return choix.size();
    }
}
