package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "menu")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "libelle")
    private String libelle;
    
    @Column(name = "image")
    private String image;

    @Column(name = "prixHT", length = 10)
    private Float prixHT;

    @ManyToOne
    private Statut statut;

    @ManyToOne
    private Employe employe;

    @ManyToMany
    private Collection<Produit> produits;

    @OneToMany(mappedBy = "menu")
    private Collection<LigneCommande> ligneCommandes;

    @ManyToOne
    private TVA TVA;
    
    public Menu() {
        produits = new ArrayList<>();
        ligneCommandes = new ArrayList<>();
    }

    public Menu(String libelle, Float prixHT) {
        this();
        this.libelle = libelle;
        this.prixHT = prixHT;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TVA getTVA() {
        return TVA;
    }

    public void setTVA(TVA TVA) {
        this.TVA = TVA;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Float getPrixHT() {
        return prixHT;
    }

    public void setPrixHT(Float prixHT) {
        this.prixHT = prixHT;
    }

    public Statut getStatut() {
        return this.statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public Employe getEmploye() {
        return this.employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public Collection<Produit> getProduits() {
        return this.produits;
    }

    public void setProduits(Collection<Produit> produits) {
        this.produits = produits;
    }

    public Collection<LigneCommande> getLigneCommandes() {
        return this.ligneCommandes;
    }

    public void setLigneCommandes(Collection<LigneCommande> ligneCommandes) {
        this.ligneCommandes = ligneCommandes;
    }

    @Override
    public String toString() {
        return "Menu{" + "libelle=" + libelle + ", prixHT=" + prixHT + ", statut=" + statut + ", employe=" + employe + '}';
    }

}
