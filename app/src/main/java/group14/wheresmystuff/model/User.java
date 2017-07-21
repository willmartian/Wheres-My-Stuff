package group14.wheresmystuff.model;

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

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != User.class) {
            return false;
        }
        return ((User) obj).getLoginID() == this.loginID;
    }

    /**
     * getter for login ID
     * @return String gets and returns the user's login ID
     */
    public String getLoginID() {
        return loginID;
    }

    /**
     * getter for password
     * @return String gets and returns the password
     */
    public String getPassword() { return password; }

    /**
     * getter for name
     * @return String gets and returns the name
     */
    public String getName() {
        return name;
    }

    /**
     * setter for name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * setter for login ID
     * @param loginID
     */
    public void setLoginID(String loginID) {
        this.loginID = loginID;
    }

    /**
     * setter for password
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * getter for email
     * @return String gets and returns the user's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * setter for email
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * getter for locked
     * @return Boolean gets and returns isLocked
     */
    public Boolean getLocked() {
        return isLocked;
    }

    public void setLocked(Boolean locked) {
        isLocked = locked;
    }
    //TODO: add getters and setters
}
