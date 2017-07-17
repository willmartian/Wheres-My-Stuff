package group14.wheresmystuff.model;

/**
 * Created by will on 6/21/2017.
 */
import android.app.Activity;
import android.app.Application;

import com.google.android.gms.maps.model.Marker;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

public class Model extends Application {

    private static ArrayList<User> userList;
    private static ArrayList<Item> itemList;
    private static User activeUser;

    @Override
    public void onCreate() {
        super.onCreate();
        userList = new ArrayList<User>();
        //example user and admin
        userList.add(new User("Default User", "user", "pass", "user@example.com"));
        userList.add(new User("Default Admin", "admin", "pass2", "admin@example.com"));

        itemList = new ArrayList<Item>();
        //example item
        itemList.add(new Item(Item.ItemType.LOST, "Mittens", "A cute kitty.", "North Ave NW, Atlanta, GA 30332", Item.Category.MISC, 1000000, userList.get(0)));
    }

    public static void setActiveUser(User user) {
        activeUser = user;
    }

    public static User getActiveUser() {
        return activeUser;
    }

    public static void addUser(User user) {
        userList.add(user);
    }

    public static void addItem(Item item) {
        itemList.add(item);
    }

    /**
     * getter for user list
     * @return ArrayList gets and returns the user list
     */
    public static ArrayList<User> getUserList() {
        return userList;
    }

    /**
     * getter for item list
     * @return ArrayList gets and returns the item list
     */
    public static ArrayList<Item> getItemList() {
        return itemList;
    }

    /**
     * Filter method for itemList
     * @param filters enum filters to filter the itemList with
     * @return the filtered itemList
     */
    public static ArrayList<Item> getItemList(Enum... filters) {
        ArrayList<Item> filteredList = new ArrayList<Item>();
        for (Item item : itemList) {
            boolean check = false;
            for (Enum filter : filters) {
                Enum checkAgainst;
                if (filter.getClass() == Item.ItemType.class) {
                    checkAgainst = item.getItemType();
                } else if (filter.getClass() == Item.Category.class) {
                    checkAgainst = item.getCategory();
                } else {
                    throw new IllegalArgumentException("Illegal filter given.");
                }
                if (checkAgainst == filter) {
                    check = true;
                }
            }
            if (check) {
                filteredList.add(item);
            }
        }
        return filteredList;
    }

    public static ArrayList<Item> getItemList(String query) {
        ArrayList<Item> filteredList = new ArrayList<Item>();
        for (Item item: itemList) {
            if (item.getName().toLowerCase().contains(query.toLowerCase())
                    || item.getDescription().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(item);
            }
        }
        return filteredList;
    }
    private JSONObject convertUserToJson() {
        JSONObject jsonObject = new JSONObject();
        for (User user : userList) {
            String loginID = user.getLoginID();
            String password = user.getPassword();
            String name = user.getName();
            String email = user.getEmail();
            boolean isLocked = user.getLocked();
            try {
                jsonObject.put("loginID", loginID);
                jsonObject.put("password", password);
                jsonObject.put("name", name);
                jsonObject.put("email", email);
                jsonObject.put("locked", isLocked);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jsonObject;
    }

    private JSONObject convertItemToJson() {
        JSONObject jsonObject = new JSONObject();
        for (Item item : itemList) {
            String name = item.getName();
            String description = item.getDescription();
            String location = item.getLocation();
            User creator = item.getCreator();
            Date date = item.getDate();
            boolean open = item.isOpen();
            Item.Category category = item.getCategory();
            Item.ItemType itemType = item.getItemType();
            double reward = item.getReward();
            try {
                jsonObject.put("name", name);
                jsonObject.put("description", description);
                jsonObject.put("location", location);
                jsonObject.put("creator", creator);
                jsonObject.put("date", date);
                jsonObject.put("isOpen", open);
                jsonObject.put("category", category);
                jsonObject.put("itemType", itemType);
                jsonObject.put("reward", reward);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return jsonObject;
    }
}
