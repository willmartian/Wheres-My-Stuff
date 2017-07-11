package group14.wheresmystuff.controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import group14.wheresmystuff.model.Item;

import group14.wheresmystuff.R;
import group14.wheresmystuff.model.Model;

/**
 * Created by willi on 7/10/2017.
 */

public class ItemActivity extends AppCompatActivity {
    private TextView itemName;
    private TextView itemDescription;
    private TextView itemLocation;
    private TextView itemReward;
    private TextView itemCreator;
    private TextView itemType;
    private TextView itemCategory;
    private TextView itemDate;

    private Bundle itemBundle;
    private Item item;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        itemName = (TextView) findViewById(R.id.itemNameView);
        itemDescription = (TextView) findViewById(R.id.itemDescriptionView);
        itemLocation = (TextView) findViewById(R.id.itemLocationView);
        itemReward = (TextView) findViewById(R.id.itemRewardView);
        itemCreator = (TextView) findViewById(R.id.itemCreatorView);
        itemType = (TextView) findViewById(R.id.itemTypeView);
        itemCategory = (TextView) findViewById(R.id.itemCategoryView);
        itemDate = (TextView) findViewById(R.id.itemDateView);


        Bundle itemBundle = getIntent().getExtras();
        item = Model.getItemList().get(itemBundle.getInt("itemIndex"));
        itemName.setText(item.getName());
        itemDescription.setText(item.getDescription());
        itemLocation.setText(item.getLocation());
        itemReward.setText((new Double(item.getReward())).toString());
        itemCreator.setText(item.getCreator().getName());
        itemType.setText(item.getItemType().toString());
        itemCategory.setText(item.getCategory().toString());
        itemDate.setText(item.getDate().toString());
//        getItemAtPosition(position)
    }
}
