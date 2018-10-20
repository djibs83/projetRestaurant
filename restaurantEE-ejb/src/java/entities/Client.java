package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name = "client")
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

   
    @Column(name = "nom", nullable = true)
    private String nom;

    @Column(name = "prenom", nullable = true)
    private String prenom;

    @Column(name = "email", nullable = true, unique = true)
    private String email;

    @Column(name = "telephone", nullable = true)
    private String telephone;

    @Column(name = "dateEnregistrement", nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateEnregistrement;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    Collection<Commande> commandes;

    public Client() {
        commandes = new ArrayList<>();
    }

    public Client(String nom, String prenom, String email, String telephone, Date dateEnregistrement) {
        this();
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.dateEnregistrement = dateEnregistrement;
    }

    public Collection<Commande> getCommandes() {
        return this.commandes;
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

    public String getPrenom() {
        return this.prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Date getDateEnregistrement() {
        return this.dateEnregistrement;
    }

    public void setDateEnregistrement(Date dateEnregistrement) {
        this.dateEnregistrement = dateEnregistrement;
    }

    public void setCommandes(Collection<Commande> commandes) {
        this.commandes = commandes;
    }

    @Override
    public String toString() {
        return nom + " " + prenom;
    }

}
