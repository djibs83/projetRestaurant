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
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name = "employe")
public class Employe implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(name = "civilite", nullable = false)
    private String civilite;

    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "prenom", nullable = false)
    private String prenom;

    @Column(name = "dateNaissance", nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateNaissance;

    @Column(name = "dateEmbauche", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateEmbauche;

    @OneToMany(mappedBy = "employe")
    private Collection<Menu> menus;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Poste poste;

    @OneToMany(mappedBy = "employe", fetch = FetchType.LAZY)
    private Collection<Commande> commandes;
   

    public Employe() {
        menus = new ArrayList<>();
        commandes = new ArrayList<>();
    }

    public Employe(String civilite, String nom, String prenom, Date DateNaissance, Date DateEmbauche) {
        this();
        this.civilite = civilite;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = DateNaissance;
        this.dateEmbauche = DateEmbauche;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCivilite() {
        return this.civilite;
    }

    public void setCivilite(String civilite) {
        this.civilite = civilite;
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

    public Date getDateNaissance() {
        return this.dateNaissance;
    }

    public void setDateNaissance(Date DateNaissance) {
        this.dateNaissance = DateNaissance;
    }

    public Date getDateEmbauche() {
        return this.dateEmbauche;
    }

    public void setDateEmbauche(Date DateEmbauche) {
        this.dateEmbauche = DateEmbauche;
    }

    public Poste getPoste() {
        return this.poste;
    }

    public void setPoste(Poste poste) {
        this.poste = poste;
    }
    
    public Collection<Menu> getMenus() {
        return menus;
    }

    public void setMenus(Collection<Menu> menus) {
        this.menus = menus;
    }

    public Collection<Commande> getCommandes() {
        return commandes;
    }

    public void setCommandes(Collection<Commande> commandes) {
        this.commandes = commandes;
    }

    @Override
    public String toString() {
        return "Employe{" + id + " " + nom + " " + prenom + '}';
    }

}
