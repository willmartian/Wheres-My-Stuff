package group14.wheresmystuff;

/**
 * Created by will on 6/21/2017.
 */
import android.app.Application;

import java.util.ArrayList;

public class Model extends Application {

    private static ArrayList<User> userList;
    private static ArrayList<Item> itemList;

    @Override
    public void onCreate() {
        super.onCreate();
        userList = new ArrayList<User>();
        //example user and admin
        userList.add(new User("Default User", "user", "pass", "user@example.com"));
        userList.add(new User("Default Admin", "admin", "pass2", "admin@example.com"));

        itemList = new ArrayList<Item>();
        //example item
        itemList.add(new Item(Item.ItemType.LOST, "Mittens", "A cute kitty.", "Atlanta", Item.Category.MISC, 1000000, userList.get(0)));
    }

    public static ArrayList<User> getUserList() {
        return userList;
    }

    public static ArrayList<Item> getItemList() {
        return itemList;
    }
}
