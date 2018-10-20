package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tva")
public class TVA implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "taux", length = 10)
    private Float taux;

    @Column(name = "nom")
    private String nom;

    @OneToMany(mappedBy = "TVA", fetch = FetchType.LAZY)
    private Collection<Produit> produits;

    public TVA() {
        produits = new ArrayList<>();
    }

    public TVA(Float taux, String nom) {
        this();
        this.taux = taux;
        this.nom = nom;
    }

    public Long getId() {
        return this.id;
    }

    public void setID(Long id) {
        this.id = id;
    }

    public Float getTaux() {
        return this.taux;
    }

    public void setTaux(Float taux) {
        this.taux = taux;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Collection<Produit> getProduits() {
        return this.produits;
    }

    public void setProduits(Collection<Produit> produits) {
        this.produits = produits;
    }

    @Override
    public String toString() {
        return "TVA{" + "taux=" + taux + ", nom=" + nom + '}';
    }

}
