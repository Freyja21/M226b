package ch.tbz.modul226b.company;

import lombok.Getter;
import lombok.Setter;



public class Company {

    @Getter
    @Setter
    private String id;

    @Getter
    @Setter
  private String name;
    @Getter
    @Setter
    String location;
    @Getter
    @Setter
    Boolean headquater;
    
    public Company(String id, String name, String location, boolean headquater){
        this.id = id;
        this.name = name;
        this.location = location;
        this.headquater = headquater;
    }

    boolean gotProfitThisYear(){
        return  true;
    }
}
