package entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "interfaceAccueil")
public class InterfaceAccueil implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "code", unique = true, nullable = false)
    private String code;
    
    @Column(name = "type", nullable = false)
    private String type;

    public InterfaceAccueil() {
    }

    public InterfaceAccueil(String type, String code) {
        this.type = type;
        this.code = code;
    }    

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "InterfaceAccueil{" + "type=" + type + ", code=" + code + '}';
    }

}
