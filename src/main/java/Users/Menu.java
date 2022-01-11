package Users;

import java.util.Scanner;

public class Menu {
    private boolean exit = false;
    private String name = "";
    private final BlueGmbh blueGmbh = new BlueGmbh();
    private final RedGmbh redGmbh = new RedGmbh();
    private final Hr hr = new Hr();
    private final IT it = new IT();
    private final Production production = new Production();
    private final Users users = new Users();
    private Accounts accounts = new Accounts();


    private void useSelected(int selected) {
        switch (selected) {
            case 1 -> {}
            case 2 -> {}
            case 3 -> {}
            case 4 -> {
                exit = true;
            }
            default -> System.out.println("wrong input");
        }
    }

    public void menu() {
        Scanner input = new Scanner(System.in);
        try {
            System.out.println("Select your choice");
            name = input.nextLine();
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