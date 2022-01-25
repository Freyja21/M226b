package ch.tbz.modul226b.departement;

public class IT extends Departement {


    int test = 3;

    /**
     *
     * @param id
     * @param name
     */
    public IT(String id, String name) {
        super(id, name);
    }

    public String showImportance() {
        return "Best one";
    }

}
