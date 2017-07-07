package group14.wheresmystuff.controller;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;

import group14.wheresmystuff.R;
import group14.wheresmystuff.model.Item;
import group14.wheresmystuff.model.Item.*;
import group14.wheresmystuff.model.Model;


/**
 * Created by Richard on 6/22/2017.
 */

public class SubmitItemActivity extends AppCompatActivity {
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
            Button cancelButton = (Button) findViewById(R.id.cancelButton);
            cancelButton.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {

                    goToPage(MainActivity.class);

                }

            });
            Button addItem = (Button) findViewById(R.id.addItemButton);
            addItem.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {



                    name = nameBox.getText().toString();
                    description = descriptionBox.getText().toString();
                    reward = rewardBox.getText().toString();
                    if(reward.length() == 0) {
                        reward = "0";
                    }
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
                    if (checkComplete()) {
                        Model.addItem(new Item(itemType, name, description, location, itemCategory, new Double(reward), Model.getUserList().get(0)));
                        goToPage(DisplayItemsActivity.class);
                    }
                }

            });
        }

    /**
     * Checks if all of the EditText elements in the activity are complete
     * @return boolean true if all elements are complete
     */
    private boolean checkComplete() {
        EditText[] elementArray = {nameBox, descriptionBox, rewardBox, locationBox};
        String[] valueArray = {name, description, reward, location};
        boolean cancel = false;
        View focusView = null;
        for (int i = 0; i < elementArray.length; i++) {
            if (TextUtils.isEmpty(valueArray[i])) {
                elementArray[i].setError(getString(R.string.error_field_required));
                focusView = nameBox;
                cancel = true;
            }
        }
        return !cancel;
    }

    public void goToPage(Class next) {
        Intent intent = new Intent(this, next);
        startActivity(intent);
    }
}
