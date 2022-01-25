package ch.tbz.modul226b.user;

import lombok.Getter;
import lombok.Setter;


/**
 * User class is used for the usermanagement
 */
public class User {
    @Getter
    @Setter
    private String id;
    @Getter
    @Setter

    private String firstName;
    @Getter
    @Setter

    private String lastName;
    @Getter
    @Setter

    private String email;

    /**
     * @param id
     * @param firstName
     * @param lastName
     * @param email
     */
    public User(String id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

}
