package ch.tbz.modul226b.company;

public class Foundation extends Company{

    private String foundationRef;

    Foundation(String id, String name, String location, boolean headquarter){
        super(id,name,location,headquarter);
    }

    @Override
    boolean gotProfitThisYear() {
        return false;
    }
}
