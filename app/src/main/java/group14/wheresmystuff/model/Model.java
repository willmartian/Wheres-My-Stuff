package group14.wheresmystuff.model;

import android.app.Application;
import android.content.Context;
import android.preference.PreferenceManager;
import com.google.gson.*;
import java.util.ArrayList;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;



public class Model extends Application {

    private static ArrayList<User> userList;
    private static ArrayList<Item> itemList;
    private static User activeUser;
    private static Context context;
    private static boolean firstLaunch = true;

    @Override
    public void onCreate() {
        context = getApplicationContext();
//        clearSaveData(); // CALL TO CLEAR ALL PREVIOUS DATA
        super.onCreate();
        loadUserList();
        loadItemList();
        if (firstLaunch) {
            addUser(new User("Default User", "user", "pass", "user@example.com"));
//            addItem(new Item(Item.ItemType.LOST, "Mittens", "A cute kitty.", "North Ave NW, Atlanta, GA 30332", Item.Category.MISC, 1000000, userList.get(0), null));
        }
        firstLaunch = false;
    }

    public static Context getContext() {
        return context;
    }

    /**
     * Clears the saved user and item data in SharedPreferences
     */
    private void clearSaveData() {
        userList = new ArrayList<>();
        itemList = new ArrayList<>();
        objToJson("users", userList);
        objToJson("items", itemList);
    }

    public static void setActiveUser(User user) {
        activeUser = user;
    }

    public static User getActiveUser() {
        return activeUser;
    }

    /**
     * adds the given user if they are not already present
     * @param user the user to add
     */
    public static void addUser(User user) {
        if (!userList.contains(user)) {
            userList.add(user);
            objToJson("users", userList);
        }
    }

    /**
     * adds the given item if it is not already present
     * @param item the item to add
     */
    public static void addItem(Item item) {
        for (Item itemB : itemList) {
            if (item.toString().equals(itemB.toString())) {
                return;
            }
        }
        itemList.add(item);
        objToJson("items", itemList);
    }

    public static boolean removeItem(Item item) {
        if (itemList.remove(item)) {
            objToJson("items", itemList);
            return true;
        }
        return false;
    }

    /**
     * getter for user list
     * @return ArrayList gets and returns the user list
     */
    public static ArrayList<User> getUserList() {
        loadUserList();
        return userList;
    }

    /**
     * getter for item list
     * @return ArrayList gets and returns the item list
     */
    public static ArrayList<Item> getItemList() {
        loadItemList();
        return itemList;
    }

    /**
     * Filter method for itemList
     * @param filters enum filters to filter the itemList with
     * @return the filtered itemList
     */
    public static ArrayList<Item> getItemList(Enum... filters) {
        ArrayList<Item> filteredList = new ArrayList<>();
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
        ArrayList<Item> filteredList = new ArrayList<>();
        for (Item item: itemList) {
            if (item.getName().toLowerCase().contains(query.toLowerCase())
                    || item.getDescription().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(item);
            }
        }
        return filteredList;
    }

    /**
     * loads the user list from save data
     */
    private static void loadUserList() {
        try {
            Type listType = new TypeToken<ArrayList<User>>() {}.getType();
            if (listType.getClass().equals(User.class)) {
                userList = (ArrayList<User>) jsonToObj("users", listType);
            }
            if (userList == null) {
                userList = new ArrayList<>();
            }

        } catch (Exception | Error e) {
            userList = new ArrayList<>();
        }
    }

    /**
     * loads the item list from save data
     */
    private static void loadItemList() {
        try {
            Type listType = new TypeToken<ArrayList<Item>>() {}.getType();
            if (listType.getClass().equals(Item.class)) {
                itemList = (ArrayList<Item>) jsonToObj("items", listType);
            }
            if (itemList == null) {
                itemList = new ArrayList<>();
            }
        } catch (Exception | Error e) {
            itemList = new ArrayList<>();
        }
    }

    /**
     * converts json to object
     * @param title the title of the json in the save data
     * @param type the type to convert to
     * @return the converted object
     */
    private static Object jsonToObj(String title, Type type) {
        String jsonString = PreferenceManager.getDefaultSharedPreferences(context).getString(title, "");
        Gson gson = new Gson();
        return gson.fromJson(jsonString, type);
    }

    /**
     * converts object to json
     * @param title the title of the json in the save data
     * @param obj the object to convert
     * @return string representation of the json
     */
    private static String objToJson(String title, Object obj) {
        Gson gson = new Gson();
        String json = gson.toJson(obj);
        PreferenceManager.getDefaultSharedPreferences(context).edit()
                .putString(title, json).apply();
        return json;
    }

//      FOR TESTING--------------------------------
//    public static String objToJson(Object obj) {
//        Gson gson = new Gson();
//        return gson.toJson(obj);
//    }
//
//
//    public static Object jsonToObj(String json, Class type) {
//        Gson gson = new Gson();
//        return gson.fromJson(json, type);
//    }
}
