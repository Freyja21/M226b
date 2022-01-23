package ch.tbz.modul226b.departement;

import lombok.Getter;
import lombok.Setter;

public class Hr extends Departement{

    public Hr(String id, String name){
        super(id,name);
    }

    public String showImportance(){return "useless";}
}
