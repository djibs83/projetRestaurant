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
@Table(name = "tabla")
public class Tabla implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "numero", nullable = false)
    private int numero;
    
    @Column(name = "statut", nullable = false)
    private int statut;

    @Column(name = "nbPlace", nullable = false)
    private int nbPlace;
    
    @Column(name = "besoinAide", nullable = false)
    private boolean besoinAide;    

    @OneToMany(mappedBy = "tabla", fetch = FetchType.LAZY)
    private Collection<Commande> commandes;

    public Tabla() {
        besoinAide = false;
        commandes = new ArrayList<>();
    }

    public Tabla(int numero, int statut, int nbPlace) {
        this();
        this.numero = numero;
        this.statut = statut;
        this.nbPlace = nbPlace;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public boolean isBesoinAide() {
        return besoinAide;
    }

    public void setBesoinAide(boolean besoinAide) {
        this.besoinAide = besoinAide;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getStatut() {
        return statut;
    }

    public void setStatut(int statut) {
        this.statut = statut;
    }

    public int getNbPlace() {
        return nbPlace;
    }

    public void setNbPlace(int nbPlace) {
        this.nbPlace = nbPlace;
    }

    public Collection<Commande> getCommandes() {
        return commandes;
    }

    public void setCommandes(Collection<Commande> commandes) {
        this.commandes = commandes;
    }

    @Override
    public String toString() {
        return statut + " " + nbPlace;
    }
}
