package group14.wheresmystuff;

/**
 * Created by will on 6/21/2017.
 */
import android.app.Application;

import java.util.ArrayList;

public class Model extends Application {

//    private static ArrayList<String> userList;
    private static ArrayList<User> userList;

    @Override
    public void onCreate() {
        super.onCreate();
        userList = new ArrayList<User>();
        userList.add(new User("Default User", "user", "pass", "user@example.com"));
        userList.add(new User("Default Admin", "admin", "pass2", "admin@example.com"));

//        userList = new ArrayList<String>();
//        userList.add("user:pass");
//        userList.add("admin:pass2");
    }

    public static ArrayList<User> getUserList() {
        return userList;
    }

//    public static ArrayList<String> getUserList() {
//        return userList;
//    }

}
