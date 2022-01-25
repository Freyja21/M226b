package ch.tbz.modul226b;

import ch.tbz.modul226b.db.SQLDatabase;
import ch.tbz.modul226b.user.Account;
import ch.tbz.modul226b.user.User;

import java.util.Scanner;
import java.util.UUID;

public class Menu {
    private boolean exit = false;

    private SQLDatabase sqlDatabase = null;
    private Scanner s = new Scanner(System.in);

    //Menu point to use the Programm
    /**
     * @param selected
     */
    private void useSelected(int selected) {
        try {
            sqlDatabase = new SQLDatabase();
            switch (selected) {
                case 1 -> {
                    // user
                    String firstName;
                    String lastName;
                    String email;
                    System.out.println("what is your first name?");
                    firstName = s.nextLine();
                    System.out.println("what is your last name");
                    lastName = s.nextLine();
                    System.out.println("what is your email?");
                    email = s.nextLine();


                    // company
                    System.out.println("where do you work? \n1. Blue GmbH\n2. Red GmbH");
                    String company = s.nextLine();

                    // Departement
                    System.out.println("Which departement do you work? \n1. HR\n2. IT\n3. Production");
                    String departement = s.nextLine();


                    // Userinfo saved in table Account
                    Account account = new Account(UUID.randomUUID().toString(), new User(UUID.randomUUID().toString(), firstName, lastName, email), sqlDatabase.loadCompany(company), sqlDatabase.loadDepartement(departement));
                    sqlDatabase.saveAccount("account", account);
                    System.out.println(account.getUserInfo().getFirstName() + " is saved");
                }
                //Gets userinfos for Red GmbH
                case 2 -> {
                    for (Account account : sqlDatabase.loadAccounts()
                    ) {
                        if (account.getWorkingInCompany().getName().equals("Red GmbH")) {
                            System.out.println("FirstName: " + account.getUserInfo().getFirstName());
                        }
                    }
                }
                //Gets Userinfos Blue GmbH
                case 3 -> {
                    for (Account account : sqlDatabase.loadAccounts()
                    ) {
                        if (account.getWorkingInCompany().getName().equals("Blue GmbH")) {
                            System.out.println("FirstName: " + account.getUserInfo().getFirstName());
                        }
                    }
                }
                // exit case
                case 4 -> {
                    exit = true;
                }
                default -> System.out.println("wrong input");
            }
            // Exceptionhandeling
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Menu points
    public void menu() {
        Scanner input = new Scanner(System.in);
        try {
            System.out.println("Select your choice");

            while (!exit) {
                System.out.println("***************************\n1. Create new User\n2. Show Users Red GmbH\n3. Show Users Blue GmbH\n4. Quit");
                useSelected(input.nextInt());
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            input.close();

        }

    }
}