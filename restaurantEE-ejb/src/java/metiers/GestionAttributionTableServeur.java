package metiers;

import entities.Employe;
import entities.Tabla;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;

@Singleton
public class GestionAttributionTableServeur implements GestionAttributionTableServeurLocal {

    public HashMap<Employe, Collection<Tabla>> maListeTables;

    @PostConstruct
    public void init() {
        maListeTables = new HashMap<>();
    }

    @Override
    public HashMap<Employe, Collection<Tabla>> getMaListeTables() {
        return maListeTables;
    }

    public void setMaListeTables(HashMap<Employe, Collection<Tabla>> maListeTables) {
        this.maListeTables = maListeTables;
    }

    @Override
    public Collection<Tabla> getMaListeTables(Employe emp) {
        return maListeTables.get(emp);

    }

    @Override
    public void addTable(Employe emp, Tabla tabla) {
        getMaListeTables(emp).add(tabla);

    }

    @Override
    public void deleteTable(Employe emp, Tabla tabla) {
        getMaListeTables(emp).remove(tabla);
    }

    @Override
    public void resetPanierTable(Employe emp) {
        getMaListeTables(emp).clear();
    }

    @Override
    public int getMaListeTablesSize(Employe emp) {
        if (emp != null) {
            return maListeTables.get(emp).size();
        } else {
            return 0;
        }
    }
}
