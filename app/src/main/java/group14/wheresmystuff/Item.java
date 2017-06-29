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
        return name + " " + location ;
    }
    //TODO: add getters and setters
}
