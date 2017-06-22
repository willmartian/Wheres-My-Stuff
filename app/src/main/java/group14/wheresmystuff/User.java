package group14.wheresmystuff;

/**
 * Created by will on 6/21/2017.
 */

public class User {
    private String name, loginID, password, email;
    private Boolean isLocked;

    public User(String name, String loginID, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.isLocked = false;
    }
}
