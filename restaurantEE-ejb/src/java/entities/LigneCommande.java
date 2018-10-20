package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name = "ligneCommande")
public class LigneCommande implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(name = "TVA", nullable = false, length = 10)
    private float TVA;

    @Column(name = "prixHT", length = 10)
    private Float prixHT;

    @Column(name = "isMenu", nullable = false)
    private boolean isMenu;
       
    @Column(name = "commentaire", nullable = true)
    private String commentaire;

    @Column(name = "debutPreparation")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date debutPreparation;

    @Column(name = "finPreparation")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date finPreparation;

    @ManyToOne
    private Commande commande;

    @ManyToOne
    @JoinColumn(nullable = true)
    private Produit produit;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private Facture facture;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private Menu menu;
    
    @OneToMany(mappedBy = "ligneCommande")
    private Collection<LigneCommande> ligneCommandes;

    @ManyToOne
    private LigneCommande ligneCommande;
    
    @ManyToOne
    private TypeCuisson typeCuisson;

    public LigneCommande() {
        ligneCommandes = new ArrayList<>();

    }

    public LigneCommande(float TVA, Float prixHT, boolean isMenu, Date debutPreparation, Date finPreparation, Commande commande, Produit produit, TypeCuisson typeCuisson) {
        this();
        this.TVA = TVA;
        this.prixHT = prixHT;
        this.isMenu = isMenu;
        this.debutPreparation = debutPreparation;
        this.finPreparation = finPreparation;
        this.produit = produit;
        this.typeCuisson = typeCuisson;
    }

    public LigneCommande(boolean isMenu, Produit produit, TypeCuisson typeCuisson, String commentaire) {
        this();
        this.prixHT = produit.getPrixHT();
        this.isMenu = isMenu;
        this.produit = produit;
        this.commentaire = commentaire;
        this.typeCuisson = typeCuisson;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Collection<LigneCommande> getLigneCommandes() {
        return ligneCommandes;
    }

    public void setLigneCommandes(Collection<LigneCommande> ligneCommandes) {
        this.ligneCommandes = ligneCommandes;
    }

    public LigneCommande getLigneCommande() {
        return ligneCommande;
    }

    public void setLigneCommande(LigneCommande ligneCommande) {
        this.ligneCommande = ligneCommande;
    }

    public float getTVA() {
        return this.TVA;
    }

    public void setTVA(float TVA) {
        this.TVA = TVA;
    }

    public Float getPrixHT() {
        return this.prixHT;
    }

    public void setPrixHT(Float prixHT) {
        this.prixHT = prixHT;
    }

    public boolean isIsMenu() {
        return this.isMenu;
    }

    public void setIsMenu(boolean isMenu) {
        this.isMenu = isMenu;
    }

    public String getCommentaire() {
        return this.commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Date getDebutPreparation() {
        return this.debutPreparation;
    }

    public void setDebutPreparation(Date debutPreparation) {
        this.debutPreparation = debutPreparation;
    }

    public Date getFinPreparation() {
        return this.finPreparation;
    }

    public void setFinPreparation(Date finPreparation) {
        this.finPreparation = finPreparation;
    }

    public Commande getCommande() {
       return this.commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public Produit getProduit() {
        return this.produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public Facture getFacture() {
        return this.facture;
    }

    public void setFacture(Facture facture) {
        this.facture = facture;
    }

    public Menu getMenu() {
        return this.menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public TypeCuisson getTypeCuisson() {
        return this.typeCuisson;
    }

    public void setTypeCuisson(TypeCuisson typeCuisson) {
        this.typeCuisson = typeCuisson;
    }

    @Override
    public String toString() {
        return "LigneCommande{" + "TVA=" + TVA + ", prixHT=" + prixHT + ", isMenu=" + isMenu + ", debutPreparation=" + debutPreparation + ", finPreparation=" + finPreparation + ", commande=" + commande + ", produit=" + produit + ", typeCuisson=" + typeCuisson + '}';
    }

//ne pas toucher svp
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final LigneCommande other = (LigneCommande) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    
}
