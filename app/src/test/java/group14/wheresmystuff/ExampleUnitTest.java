package group14.wheresmystuff;



import org.junit.Test;

import static org.junit.Assert.*;

import group14.wheresmystuff.model.Item;
import group14.wheresmystuff.model.Model;
import group14.wheresmystuff.model.User;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void testJson() throws Exception {
        User testUser = new User("test", "test", "test", "test");
        Item testItem = new Item(Item.ItemType.LOST, "Mittens", "A cute kitty.", "North Ave NW, Atlanta, GA 30332", Item.Category.MISC, 1000000, testUser, null);
        String jsonString = Model.objToJson(testItem);
        Item convertedItem = (Item) Model.jsonToObj(jsonString, Item.class);
        assertEquals(testItem.toString(), convertedItem.toString());
    }
}