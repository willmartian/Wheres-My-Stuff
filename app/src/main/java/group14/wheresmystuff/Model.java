package group14.wheresmystuff;

/**
 * Created by will on 6/21/2017.
 */
import android.app.Application;

import java.util.ArrayList;

public class Model extends Application {

    private static ArrayList<String> userList;

    @Override
    public void onCreate() {
        super.onCreate();
        userList = new ArrayList<String>();
        userList.add("user:pass");
        userList.add("admin:pass2");
    }

    public static ArrayList<String> getUserList() {
        return userList;
    }

}
