/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metiers;

import entities.Facture;
import entities.Reglement;
import entities.TypeReglement;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author cdi205
 */
@Stateful
public class GestionReglement implements GestionReglementLocal {
    @PersistenceContext(unitName = "restaurantEE-ejbPU")
    private EntityManager em;
    
    private Reglement reglement;
    private HashMap<String, TypeReglement> typeReg;
    
   @PostConstruct
    public void init() {
        this.typeReg = new HashMap<>();
        
    }
    
    @Override
    public void initHashMap(){
        String query = "select t from TypeReglement t";
        Query qr = em.createQuery(query);
        List<TypeReglement> result = qr.getResultList();
        System.out.println(">>>>>"+result);
        for(TypeReglement tr : result){
            System.out.println();
            if(tr.getTypeReglement().equalsIgnoreCase("CB")){
                typeReg.put("CB",tr);
            } else if(tr.getTypeReglement().equalsIgnoreCase("Espèce")){
                typeReg.put("espece",tr);
            } else if(tr.getTypeReglement().equalsIgnoreCase("Chèque")){
                typeReg.put("cheque", tr);
            }
        }
    }
    @Override
    public void create(float montant, String typeRegKey, Facture facture){
        initHashMap();
        TypeReglement type = typeReg.get(typeRegKey);
        System.out.println(typeReg);
//        System.out.println(type);
        reglement = new Reglement(null, new Date(), montant, type);
        reglement.setFacture(facture);
    }

    @Override
    public Reglement getReglement() {
        return reglement;
    }

    @Override
    public HashMap<String, TypeReglement> getTypeReg() {
        return typeReg;
    }
    
    
    
    
}
