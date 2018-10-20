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
@Table(name = "typeReglement")
public class TypeReglement implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "typeReglement", nullable = false)
    private String typeReglement;

    @OneToMany(mappedBy = "typeReglement", fetch = FetchType.LAZY)
    private Collection<Reglement> reglements;

    public TypeReglement() {
        reglements = new ArrayList<>();
    }

    public TypeReglement(String TypeReglement) {
        this();
        this.typeReglement = TypeReglement;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Collection<Reglement> getReglements() {
        return reglements;
    }

    public void setReglements(Collection<Reglement> reglements) {
        this.reglements = reglements;
    }

    public String getTypeReglement() {
        return typeReglement;
    }

    public void setTypeReglement(String TypeReglement) {
        this.typeReglement = TypeReglement;
    }

    @Override
    public String toString() {
        return typeReglement;
    }

}
