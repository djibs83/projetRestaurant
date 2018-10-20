package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "statut")
public class Statut implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "numero", nullable = false)
    private int numero;

    @Column(name = "libelle", nullable = false)
    private String libelle;

    @OneToMany(mappedBy = "statut", fetch = FetchType.LAZY)
    private Collection<Produit> produits;

    public Statut() {
    }

    public Statut(int numero, String libelle) {
        this.numero = numero;
        this.libelle = libelle;
    }

    public Collection<Produit> getProduits() {
        return produits;
    }

    public void setProduits(Collection<Produit> produits) {
        this.produits = produits;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public String toString() {
        return numero + " " + libelle;
    }

}
