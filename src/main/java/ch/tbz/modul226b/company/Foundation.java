package ch.tbz.modul226b.company;

public class Foundation extends Company{

    private String foundationRef;

    /**
     *
     * @param id
     * @param name
     * @param location
     * @param headquarter
     */

    Foundation(String id, String name, String location, boolean headquarter){
        super(id,name,location,headquarter);
    }

    @Override
    boolean gotProfitThisYear() {
        return false;
    }
}
