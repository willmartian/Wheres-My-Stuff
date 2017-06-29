package group14.wheresmystuff;
import java.util.Date;

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

    public enum Category {
        KEEPSAKE, HEIRLOOM, MISC;

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
                String location, Category category, double reward, User creator) {
        this.itemType = itemType;
        this.name = name;
        this.description = description;
        this.location = location;
        this.category = category;
        this.reward = reward;
        this.creator = creator;
        this.open = true;
        this.date = new Date();
    }
    @Override
    public String toString() {
        return itemType.toString() + ": \"" + name + "\" in " + location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public double getReward() {
        return reward;
    }

    public void setReward(double reward) {
        this.reward = reward;
    }
}
