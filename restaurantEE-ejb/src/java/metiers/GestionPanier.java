package metiers;

import entities.Commande;
import entities.LigneCommande;
import entities.Menu;
import entities.Produit;
import entities.Tabla;
import entities.TypeCuisson;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateful
public class GestionPanier implements GestionPanierLocal {

    private Commande commande;
    private ArrayList<LigneCommande> ligneCommandes;

    @PersistenceContext(unitName = "restaurantEE-ejbPU")
    private EntityManager em;

    @PostConstruct
    public void init() { //ne pas exposer la methode dans l'inteface
        commande = new Commande();
        ligneCommandes = new ArrayList<>();
    }

    @Override
    public Commande getCommande() {
        return commande;
    }

    @Override
    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    @Override
    public ArrayList<LigneCommande> getLigneCommandesProduits() {
        return ligneCommandes;
    }

    public void setLigneCommandes(ArrayList<LigneCommande> ligneCommandes) {
        this.ligneCommandes = ligneCommandes;
    }

    @Override
    public float getPrixTotalHT() {
        float p = 0;
        for (LigneCommande lc : ligneCommandes) {
            p += lc.getPrixHT();
        }
        return p;
    }

    @Override
    public void add(Produit p, String com, TypeCuisson typeCuisson) {
        if (p == null) {
            throw new NullPointerException("produit null!");
        }
        LigneCommande lc = new LigneCommande(false, p, null, com);
        lc.setTVA(p.getTVA().getTaux());
        lc.setPrixHT(p.getPrixHT());
        lc.setTypeCuisson(typeCuisson);
        ligneCommandes.add(lc);

    }

    @Override
    public void validerMenu(Menu menu, Collection<Produit> choix, String commentaire) {
        LigneCommande lc = new LigneCommande();
        lc.setIsMenu(true);
        lc.setPrixHT(menu.getPrixHT());
        lc.setTVA(menu.getTVA().getTaux());
        lc.setMenu(menu);
        for (Produit p : choix) {
            LigneCommande lcp = new LigneCommande();
            lcp.setProduit(p);
            lcp.setLigneCommande(lc);
            lcp.setCommentaire(commentaire);
            lc.getLigneCommandes().add(lcp);
        }
        ligneCommandes.add(lc);

    }

    @Override
    public void subProduit(int index) {
        ligneCommandes.remove(index);

    }

    @Override
    public void vider() {
        init();
    }

    @Override
    public int panierSize() {
        return ligneCommandes.size();
    }

    @Override
    public void persistePanier(Tabla table) {
        for (LigneCommande lc : ligneCommandes) {
            lc.setCommande(commande);
            commande.getLigneCommandes().add(lc);
        }
        commande.setTabla(table);
        commande.setDateCommande(new Date());
        em.merge(this.commande);
    }

}
