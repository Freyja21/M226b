package ch.tbz.modul226b.user;

import ch.tbz.modul226b.company.Company;
import ch.tbz.modul226b.departement.Departement;
import lombok.Getter;
import lombok.Setter;

public class Account {

    @Getter
    @Setter
    private String id;
    // User
    @Getter
    @Setter
    private User userInfo;


    // IT, Prod or HR
    @Getter
    @Setter
    private Departement workingInDepartement;


    // Blue or Red
    @Getter
    @Setter
    private Company workingInCompany;


    public Account(String id, User userInfo, Company workingInCompany, Departement workingInDepartement) {
        this.id = id;
        this.userInfo = userInfo;
        this.workingInDepartement = workingInDepartement;
        this.workingInCompany = workingInCompany;
    }

}
