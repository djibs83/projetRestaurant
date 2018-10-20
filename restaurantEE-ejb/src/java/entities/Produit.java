package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "produit")
public class Produit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(name = "prixHT", nullable = false, length = 10)
    private Float prixHT;

    @Column(name = "image", nullable = true)
    private String image;
    
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Statut statut;

    @Column(name = "description", nullable = true)
    private String description;

    @Column(name = "tempsPreparation", length = 10, nullable = false)
    private int tempsPreparation;

    @OneToMany(mappedBy = "produit", fetch = FetchType.LAZY)
    private Collection<LigneCommande> ligneCommandes;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private SousCategorie sousCategorie;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private TVA TVA;

    @ManyToMany(mappedBy = "produits", fetch = FetchType.LAZY)
    private Collection<Menu> Menus;

    @Column(name = "cuisine")
    private boolean cuisine;

    @Column(name = "cuisson")
    private boolean cuisson;

    public Produit() {
        ligneCommandes = new ArrayList<>();
        Menus = new ArrayList<>();
    }

    public Produit(String nom, Float prixHT, String image, String description, int tempsPreparation, boolean cuisine, boolean cuisson) {
        this();
        this.nom = nom;
        this.prixHT = prixHT;
        this.image = image;
        this.description = description;
        this.tempsPreparation = tempsPreparation;
        this.cuisine = cuisine;
        this.cuisson = cuisson;
    }

    public Collection<Menu> getMenus() {
        return Menus;
    }

    public void setMenus(Collection<Menu> Menus) {
        this.Menus = Menus;
    }

    public Collection<LigneCommande> getLigneCommandes() {
        return this.ligneCommandes;
    }

    public SousCategorie getSousCategorie() {
        return this.sousCategorie;
    }

    public TVA getTVA() {
        return this.TVA;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Float getPrixHT() {
        return this.prixHT;
    }

    public void setPrixHT(Float prixHT) {
        this.prixHT = prixHT;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Statut getStatut() {
        return this.statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTempsPreparation() {
        return this.tempsPreparation;
    }

    public void setTempsPreparation(int tempsPreparation) {
        this.tempsPreparation = tempsPreparation;
    }

    public void setLigneCommandes(Collection<LigneCommande> ligneCommandes) {
        this.ligneCommandes = ligneCommandes;
    }

    public void setSousCategorie(SousCategorie sousCategorie) {
        this.sousCategorie = sousCategorie;
    }

    public void setTVA(TVA TVA) {
        this.TVA = TVA;
    }

    public boolean isCuisine() {
        return this.cuisine;
    }

    public void setCuisine(boolean estCuisine) {
        this.cuisine = estCuisine;
    }

    public boolean isCuisson() {
        return this.cuisson;
    }

    public void setCuisson(boolean cuisson) {
        this.cuisson = cuisson;
    }

    @Override
    public String toString() {
        return "Produit{" + "nom=" + nom + ", prixHT=" + prixHT + '}';
    }

}
