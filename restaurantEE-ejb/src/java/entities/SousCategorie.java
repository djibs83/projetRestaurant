package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "sousCategorie")
public class SousCategorie implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "libelle", nullable = false)
    private String libelle;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private CategorieProduit categorieProduit;

    @OneToMany(mappedBy = "sousCategorie", fetch = FetchType.LAZY)
    private Collection<Produit> produits;

    public SousCategorie() {
        produits = new ArrayList<>();
    }

    public SousCategorie(String libelle) {
        this();
        this.libelle = libelle;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public CategorieProduit getCategorieProduit() {
        return categorieProduit;
    }

    public void setCategorieProduit(CategorieProduit categorieProduit) {
        this.categorieProduit = categorieProduit;
    }

    public Collection<Produit> getProduits() {
        return produits;
    }

    public void setProduits(Collection<Produit> produits) {
        this.produits = produits;
    }

    @Override
    public String toString() {
        return libelle;
    }

}
