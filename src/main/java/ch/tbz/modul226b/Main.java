package ch.tbz.modul226b;

import ch.tbz.modul226b.company.Company;
import ch.tbz.modul226b.db.SQLDatabase;
import ch.tbz.modul226b.departement.Departement;
import ch.tbz.modul226b.departement.Hr;
import ch.tbz.modul226b.departement.IT;
import ch.tbz.modul226b.user.User;

public class Main {
    public static void main(String[] args) {

        Menu menu = new Menu();
        menu.menu();
    }
}
