package group14.wheresmystuff;

/**
 * Created by will on 6/21/2017.
 */

public class User {
    private String name, loginID, password, email;
    private Boolean isLocked;

    public User(String name, String loginID, String password, String email) {
        this.name = name;
        this.loginID = loginID;
        this.password = password;
        this.email = email;
        this.isLocked = false;
    }

    public String getLoginID() {
        return loginID;
    }

    public String getPassword() { return password; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLoginID(String loginID) {
        this.loginID = loginID;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getLocked() {
        return isLocked;
    }

    public void setLocked(Boolean locked) {
        isLocked = locked;
    }
    //TODO: add getters and setters
}
