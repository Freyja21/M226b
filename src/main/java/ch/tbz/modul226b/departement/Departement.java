package ch.tbz.modul226b.departement;

import lombok.Getter;
import lombok.Setter;



abstract public class Departement {

    @Getter
    @Setter
    private String id;
    @Getter
    @Setter
    private String name = "";
    Departement(String id, String name){
        this.id = id;
        this.name =name;

    }

    abstract public String showImportance();


}

