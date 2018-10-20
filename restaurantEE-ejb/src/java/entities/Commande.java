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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name = "commande")
public class Commande implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;    

    @Column(name = "dateCommande")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateCommande;

    @JoinColumn(name = "statut")
    @OneToOne
    private Statut statut;
    
    @Column(name = "nomClient", nullable = true)
    private String nomClient;

    @ManyToOne
    private Client client;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Tabla tabla;

    @ManyToOne
    private Employe employe;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "commande", fetch = FetchType.LAZY)
    private Collection<LigneCommande> ligneCommandes;



    public Commande() {
        ligneCommandes = new ArrayList<>();
    }

    public Commande(Date dateCommande) {
        this();
        this.dateCommande = dateCommande;
//        this.statut = statut;
//        this.client = client;
//        this.tabla = tabla;
//        this.employe = employe;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
  
    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }
    
    public Date getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(Date dateCommande) {
        this.dateCommande = dateCommande;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Tabla getTabla() {
        return tabla;
    }

    public void setTabla(Tabla tabla) {
        this.tabla = tabla;
    }

    public Employe getEmployes() {
        return employe;
    }

    public void setEmployes(Employe employe) {
        this.employe = employe;
    }

    public Collection<LigneCommande> getLigneCommandes() {
        return ligneCommandes;
    }

    public void setLigneCommandes(Collection<LigneCommande> ligneCommandes) {
        this.ligneCommandes = ligneCommandes;
    }

    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", dateCommande=" + dateCommande + ", statut=" + statut + ", client=" + client + ", tabla=" + tabla + ", employes=" + employe + ", ligneCommandes=" + ligneCommandes +  '}';
    }

}
