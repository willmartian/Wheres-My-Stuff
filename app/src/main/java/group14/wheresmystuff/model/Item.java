package group14.wheresmystuff.model;
import android.graphics.Bitmap;
import android.location.Location;

import java.util.Date;
import com.google.android.gms.maps.model.Marker;

/**
 * Created by will on 6/27/2017.
 */



public class Item {
    private String name, description, location;
    private User creator;
    private Date date;
    private boolean open;
    private Category category;
    private ItemType itemType;
    private double reward;
    private Bitmap icon;

    public enum Category {
        KEEPSAKE, HEIRLOOM, MISC;

        /**
         * Method to return an array representation of the enum value names
         * @return Sting[] of value names
         */
        public static String[] stringEnumArray() {
            String[] arr = new String[values().length];
            for(int i = 0; i < values().length; i++) {
                arr[i] = values()[i].toString();
            }
            return arr;
        }
    }

    public enum ItemType {
        LOST, FOUND, NEED;
    }

    public Item(ItemType itemType, String name, String description,
                String location, Category category, double reward, User creator, Bitmap icon) {
        this.itemType = itemType;
        this.name = name;
        this.description = description;
        this.location = location;
        this.category = category;
        this.reward = reward;
        this.creator = creator;
        this.open = true;
        this.date = new Date();
        this.icon = icon;
    }

    @Override
    public boolean equals(Object object) {

        if (object.getClass() != this.getClass()) {
            return false;
        }
        return this.toString() == object.toString();
//        Item item = (Item) object;
//        if (itemType != item.getItemType()
//                || name != item.getName()
//                || description != item.getDescription()
//                || !location.equals(item.getLocation())
//                || category != item.getCategory()
//                || creator != item.getCreator()
//                || open != item.open
//                || !date.equals(item.getDate())
//                ) {
//            return false;
//        }
//        return true;
    }

    @Override
    public String toString() {
        return itemType.toString() + ": \"" + name + "\" in " + location;
    }

    public Bitmap getIcon() {
        return icon;
    }

    public void setIcon(Bitmap icon) {
        this.icon = icon;
    }
    /**
     * getter for name
     * @return String gets and returns the name of the user
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
     * getter for description
     * @return String gets and returns the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * setter for description
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * getter for location
     * @return String gets and returns the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * setter for location
     * @param location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * getter for creator
     * @return String gets and returns creator
     */
    public User getCreator() {
        return creator;
    }

    /**
     * setter for creator
     * @param creator
     */
    public void setCreator(User creator) {
        this.creator = creator;
    }

    /**
     * getter for date
     * @return String gets and returns the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * setter for date
     * @param date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * getter for open
     * @return boolean if true, gets and returns open
     */
    public boolean isOpen() {
        return open;
    }

    /**
     * setter for open
     * @param open
     */
    public void setOpen(boolean open) {
        this.open = open;
    }

    /**
     * getter for category
     * @return Category gets and returns the category
     */
    public Category getCategory() {
        return category;
    }

    /**
     * setter for category
     * @param category
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * getter for item type
     * @return ItemType gets and returns the item type
     */
    public ItemType getItemType() {
        return itemType;
    }

    /**
     * setter for item type
     * @param itemType
     */
    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    /**
     * getter for reward
     * @return double gets and returns reward
     */
    public double getReward() {
        return reward;
    }

    /**
     * setter for reward
     * @param reward
     */
    public void setReward(double reward) {
        this.reward = reward;
    }
}
