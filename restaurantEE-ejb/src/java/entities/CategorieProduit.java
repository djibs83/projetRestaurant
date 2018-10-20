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
@Table(name = "categorieProduit")
public class CategorieProduit implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "libelle", nullable = false)
    private String libelle;
    
    @Column(name = "position", nullable = false)
    private int position;

    @OneToMany(mappedBy = "categorieProduit", fetch = FetchType.LAZY)
    private Collection<SousCategorie> sousCategories;

    public CategorieProduit() {
        sousCategories = new ArrayList<>();
    }

    public CategorieProduit(int position, String libelle) {
        this();
        this.position = position;
        this.libelle = libelle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getLibelle() {
        return this.libelle;
    }

    public void setLibelle(String Libelle) {
        this.libelle = Libelle;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Collection<SousCategorie> getSousCategories() {
        return this.sousCategories;
    }

    public void setSousCategories(Collection<SousCategorie> sousCategories) {
        this.sousCategories = sousCategories;
    }

    @Override
    public String toString() {
        return "CategorieProduit{" + "libelle=" + libelle + '}';
    }

}
