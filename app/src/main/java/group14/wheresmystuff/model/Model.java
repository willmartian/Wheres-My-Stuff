package group14.wheresmystuff.model;

/**
 * Created by will on 6/21/2017.
 */

import android.app.Application;
import android.content.Context;
import android.preference.PreferenceManager;
import android.content.SharedPreferences;
import com.google.gson.*;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Date;
import android.util.Log;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;


public class Model extends Application {

    private static ArrayList<User> userList;
    private static ArrayList<Item> itemList;
    private static User activeUser;
    private static Context context;

    @Override
    public void onCreate() {
        context = getApplicationContext();
//        clearSaveData(); // CALL TO CLEAR ALL PREVIOUS DATA
        super.onCreate();
        loadUserList();
        loadItemList();
        addUser(new User("Default User", "user", "pass", "user@example.com"));
        addItem(new Item(Item.ItemType.LOST, "Mittens", "A cute kitty.", "North Ave NW, Atlanta, GA 30332", Item.Category.MISC, 1000000, userList.get(0)));

    }

    private void clearSaveData() {
        userList = new ArrayList<User>();
        itemList = new ArrayList<Item>();
        objToJson("users", userList);
        objToJson("items", itemList);
    }

    public static void setActiveUser(User user) {
        activeUser = user;
    }

    public static User getActiveUser() {
        return activeUser;
    }

    public static void addUser(User user) {
        if (!userList.contains(user)) {
            userList.add(user);
            objToJson("users", userList);
        }
    }

    public static void addItem(Item item) {
        for (Item itemB : itemList) {
            if (item.toString() == itemB.toString()) {
                return;
            }
        }
        itemList.add(item);
        objToJson("items", itemList);
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

    private static void loadUserList() {
        try {
            Type listType = new TypeToken<ArrayList<User>>() {}.getType();
            userList = (ArrayList<User>) jsonToObj("users", listType);
            if (userList == null) {
                userList = new ArrayList<User>();
            }

        } catch (Exception | Error e) {
            userList = new ArrayList<User>();
        }
    }

    private static void loadItemList() {
        try {
            Type listType = new TypeToken<ArrayList<Item>>() {}.getType();
            itemList = (ArrayList<Item>) jsonToObj("items", listType);
            if (itemList == null) {
                itemList = new ArrayList<Item>();
            }
        } catch (Exception | Error e) {
            itemList = new ArrayList<Item>();
        }
    }

    private static Object jsonToObj(String title, Type type) {
        String jsonString = PreferenceManager.getDefaultSharedPreferences(context).getString(title, "");
        Gson gson = new Gson();
        return gson.fromJson(jsonString, type);
    }

    private static String objToJson(String title, Object obj) {
        Gson gson = new Gson();
        String json = gson.toJson(obj);
        PreferenceManager.getDefaultSharedPreferences(context).edit()
                .putString(title, json).apply();
        return json;
    }

//    private JSONObject convertUserToJson() {
//        JSONObject jsonObject = new JSONObject();
//        for (User user : userList) {
//            String loginID = user.getLoginID();
//            String password = user.getPassword();
//            String name = user.getName();
//            String email = user.getEmail();
//            boolean isLocked = user.getLocked();
//            try {
//                jsonObject.put("loginID", loginID);
//                jsonObject.put("password", password);
//                jsonObject.put("name", name);
//                jsonObject.put("email", email);
//                jsonObject.put("locked", isLocked);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//        return jsonObject;
//    }

//    private JSONObject convertItemToJson() {
//        JSONObject jsonObject = new JSONObject();
//        for (Item item : itemList) {
//            String name = item.getName();
//            String description = item.getDescription();
//            String location = item.getLocation();
//            User creator = item.getCreator();
//            Date date = item.getDate();
//            boolean open = item.isOpen();
//            Item.Category category = item.getCategory();
//            Item.ItemType itemType = item.getItemType();
//            double reward = item.getReward();
//            try {
//                jsonObject.put("name", name);
//                jsonObject.put("description", description);
//                jsonObject.put("location", location);
//                jsonObject.put("creator", creator);
//                jsonObject.put("date", date);
//                jsonObject.put("isOpen", open);
//                jsonObject.put("category", category);
//                jsonObject.put("itemType", itemType);
//                jsonObject.put("reward", reward);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//        }
//        return jsonObject;
//    }
}
