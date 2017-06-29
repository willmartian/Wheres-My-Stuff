package group14.wheresmystuff;

import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import group14.wheresmystuff.Item.*;



/**
 * Created by Richard on 6/22/2017.
 */

public class SubmitItemActivity extends AppCompatActivity{
    String name;
    String description;
    String reward;
    String location;
    Category itemCategory;
    RadioButton foundButton;
    RadioButton lostButton;
    RadioButton donateButton;
    EditText nameBox;
    EditText descriptionBox;
    EditText rewardBox;
    EditText locationBox;
    Spinner spinnerBox;
    ItemType itemType;
    String[] categoryArray;
    Spinner categorySpinner;
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_submititem);


            //grabbing form data
            foundButton = (RadioButton) findViewById(R.id.foundButton);
            lostButton = (RadioButton) findViewById(R.id.lostButton);
            donateButton = (RadioButton) findViewById(R.id.donateButton);
            nameBox = (EditText) findViewById(R.id.nameTextBox);
            descriptionBox = (EditText) findViewById(R.id.nameTextBox);
            rewardBox = (EditText) findViewById(R.id.rewardTextBox);
            locationBox = (EditText) findViewById(R.id.locationTextBox);
            spinnerBox = (Spinner) findViewById(R.id.categorySpinner);
            categorySpinner = (Spinner) findViewById(R.id.categorySpinner);
            categoryArray = Item.Category.stringEnumArray();


            // Create an ArrayAdapter using the string array and a default spinner layout
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_dropdown_item, categoryArray);
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // Apply the adapter to the spinner

            categorySpinner.setAdapter(adapter);
            Button addItem = (Button) findViewById(R.id.addItemButton);
            addItem.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    name = nameBox.getText().toString();
                    description = descriptionBox.getText().toString();
                    reward = rewardBox.getText().toString();
                    location = locationBox.getText().toString();
                    itemCategory = Category.valueOf(spinnerBox.getSelectedItem().toString());

                    if (foundButton.isChecked()) {
                        itemType = ItemType.FOUND;
                    }
                    if (lostButton.isChecked()) {
                        itemType = ItemType.LOST;
                    }
                    if (donateButton.isChecked()) {
                        itemType = ItemType.NEED;
                    }
                    Model.getItemList().add(new Item(itemType, name , description, location, itemCategory, new Double(reward), Model.getUserList().get(0)));
                    goToDisplayAllItemActivity();
                }

            });
        }
    private void goToDisplayAllItemActivity(){
        Intent intent = new Intent(this,DisplayAllItemActivity.class);
        startActivity(intent);
    }

}
