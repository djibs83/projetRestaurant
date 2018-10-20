package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name = "reglement")
public class Reglement implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @JoinColumn(name = "statut")
    @OneToOne
    private Statut statut;

    @Column(name = "dateReglement")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateReglement;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private TypeReglement typeReglement;

    @ManyToOne(fetch = FetchType.LAZY)
    private Facture facture;

    @Column(name = "Montant", nullable = false, length = 10)
    private float montant;

    public Reglement() {
        
    }

    public Reglement(Statut statut, Date dateReglement, float montant, TypeReglement typeReglement) {
        this();
        this.statut = statut;
        this.dateReglement = dateReglement;
        this.montant = montant;
        this.typeReglement = typeReglement;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Statut getStatut() {
        return this.statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public Date getDateReglement() {
        return this.dateReglement;
    }

    public void setDateReglement(Date dateReglement) {
        this.dateReglement = dateReglement;
    }

    public TypeReglement getTypeReglement() {
        return this.typeReglement;
    }

    public void setTypeReglement(TypeReglement typeReglement) {
        this.typeReglement = typeReglement;
    }

    public Facture getFacture() {
        return facture;
    }

    public void setFacture(Facture facture) {
        this.facture = facture;
    }


    public float getMontant() {
        return this.montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    @Override
    public String toString() {
        return "Reglement{" + "statut=" + statut + ", dateReglement=" + dateReglement + ", montant=" + montant + '}';
    }

}
