package metiers;

import entities.Employe;
import entities.Facture;
import entities.LigneCommande;
import entities.Reglement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author cdi205
 */
@Stateful
public class GestionFacture implements GestionFactureLocal {

    private List<LigneCommande> facture;
    private Facture facturePersiste;
    @PersistenceContext(unitName = "restaurantEE-ejbPU")
    private EntityManager em;

    @PostConstruct
    public void init() {
        facturePersiste = new Facture();
        facture = new ArrayList<>();
    }

    @Override
    public int getQuantite() {
        return facture.size();
    }

    @Override
    public float getPrixTotalHT() {
        float p = 0;
        Collection<LigneCommande> cl = getFacture();
        for (LigneCommande l : cl) {
            p += l.getPrixHT();
        }
        
        for(Reglement reg : facturePersiste.getReglements()){
            p -= reg.getMontant();
        }
        return p;
    }

    @Override
    public void add(LigneCommande lc) throws NullPointerException {
        if (!facture.contains(lc)) {
            facture.add(lc);
        }
    }

    @Override
    public boolean supp(LigneCommande lc) throws NullPointerException {
        return facture.remove(lc);

    }

    @Override
    public List<LigneCommande> getFacture() {
        return facture;
    }

    @Override
    public void setFacture(List<LigneCommande> facture) {
        this.facture = facture;
    }

    @Override
    public void generateFacture(Employe serveur) {
        facturePersiste.setEmploye(serveur);
        facturePersiste.setDateFacture(new Date());
        facturePersiste.setLigneCommandes(facture);

    }

    @Override
    public void persistFacture() {
        em.persist(facturePersiste);
        for (LigneCommande lc : facture) {
            lc.setFacture(facturePersiste);
            em.merge(lc);
        }
    }
    
    @Override
    public void mergeReglement(){
        for(Reglement reg : facturePersiste.getReglements()){
            em.merge(reg);
        }
    }

    @Override
    public Facture getFacturePersiste() {
        return facturePersiste;
    }

    @Override
    public void setFacturePersiste(Facture facturePersiste) {
        this.facturePersiste = facturePersiste;
    }
    
    @Override
    public void reinitFacture(){
        this.facture.clear();
    }

    public void ajouterReglement(Reglement reg){
        facturePersiste.getReglements().add(reg);
    }
}
