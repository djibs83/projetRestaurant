package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "typeCuisson")
public class TypeCuisson implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "libelle", nullable = false)
    private String libelle;

    @OneToMany(mappedBy = "typeCuisson", fetch = FetchType.LAZY)
    private Collection<LigneCommande> ligneCommandes;

    public TypeCuisson() {
        ligneCommandes = new ArrayList<>();
    }

    public TypeCuisson(String libelle) {
        this();
        this.libelle = libelle;
    }

    public Collection<LigneCommande> getLigneCommandes() {
        return this.ligneCommandes;
    }

    public void setLigneCommandes(Collection<LigneCommande> ligneCommandes) {
        this.ligneCommandes = ligneCommandes;
    }

    public String getLibelle() {
        return this.libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public String toString() {
        return "TypeCuisson{" + "libelle=" + libelle + '}';
    }

}
