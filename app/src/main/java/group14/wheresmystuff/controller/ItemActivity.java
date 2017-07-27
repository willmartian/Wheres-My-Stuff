package group14.wheresmystuff.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import group14.wheresmystuff.model.Item;
import group14.wheresmystuff.R;
import group14.wheresmystuff.model.Model;
import group14.wheresmystuff.model.Admin;



public class ItemActivity extends AppCompatActivity {

    private Bundle itemBundle;
    private Item item;


    protected void onCreate(Bundle savedInstanceState) {
        TextView itemName;
        TextView itemDescription;
        TextView itemLocation;
        TextView itemReward;
        TextView itemCreator;
        TextView itemType;
        TextView itemCategory;
        TextView itemDate;
        Button editButton;
        Button removeButton;
        Button viewButton;
        ImageView itemIcon;


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        getSupportActionBar().setTitle("Where's My Stuff? - " + Model.getActiveUser().getName());
        itemName = (TextView) findViewById(R.id.itemNameView);
        itemDescription = (TextView) findViewById(R.id.itemDescriptionView);
        itemLocation = (TextView) findViewById(R.id.itemLocationView);
        itemReward = (TextView) findViewById(R.id.itemRewardView);
        itemCreator = (TextView) findViewById(R.id.itemCreatorView);
        itemType = (TextView) findViewById(R.id.itemTypeView);
        itemCategory = (TextView) findViewById(R.id.itemCategoryView);
        itemDate = (TextView) findViewById(R.id.itemDateView);
        itemIcon = (ImageView) findViewById(R.id.itemIcon);


        itemBundle = getIntent().getExtras();
        item = Model.getItemList().get(itemBundle.getInt("itemIndex"));
        itemName.setText(item.getName());
        itemDescription.setText(item.getDescription());
        itemLocation.setText(item.getLocation());
        itemReward.setText(Double.toString(item.getReward()));
        itemCreator.setText(item.getCreator().getName());
        itemType.setText(item.getItemType().toString());
        itemCategory.setText(item.getCategory().toString());
        itemDate.setText(item.getDate().toString());
        if (item.getIcon() != null) {
            itemIcon.setImageBitmap(item.getIcon());
        }

        editButton = (Button) findViewById(R.id.editButton);
        removeButton = (Button) findViewById(R.id.removeButton);
        if (Model.getActiveUser().getLoginID().equals(item.getCreator().getLoginID())
                || Model.getActiveUser().getClass().equals(Admin.class)) {
            editButton.setVisibility(View.VISIBLE);
            removeButton.setVisibility(View.VISIBLE);
        } else {
            editButton.setVisibility(View.INVISIBLE);
            removeButton.setVisibility(View.INVISIBLE);
        }
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToPage(SubmitItemActivity.class, itemBundle);
            }
        });


        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Model.removeItem(item);
                goToPage(MainActivity.class);
                finish();
            }
        });

        viewButton = (Button) findViewById(R.id.viewButton);
        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToPage(MainActivity.class, itemBundle);
            }
        });
    }


    private void goToPage(Class next, Bundle bundle) {
        Intent intent = new Intent(this, next);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void goToPage(Class next) {
        Intent intent = new Intent(this, next);
        startActivity(intent);
    }
}
