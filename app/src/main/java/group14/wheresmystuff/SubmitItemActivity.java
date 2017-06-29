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
import group14.wheresmystuff.Item.ItemType;
import group14.wheresmystuff.Item.Category;



/**
 * Created by Richard on 6/22/2017.
 */

public class SubmitItemActivity extends AppCompatActivity{
    String name;
    String description;
    String reward;
    Category itemCategory;
    RadioButton foundButton = (RadioButton) findViewById(R.id.foundButton);
    RadioButton lostButton = (RadioButton) findViewById(R.id.lostButton);
    RadioButton donateButton = (RadioButton) findViewById(R.id.donateButton);
    EditText nameBox = (EditText) findViewById(R.id.nameTextBox);
    EditText descriptionBox = (EditText) findViewById(R.id.nameTextBox);
    EditText rewardBox = (EditText) findViewById(R.id.rewardTextBox);
    Spinner spinnerBox = (Spinner) findViewById(R.id.categorySpinner);
    ItemType itemType;
    private String[] categoryArray = new String[]{"Keepsake","Heirloom","Miscellaneous"};
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_submititem);
            if (foundButton.isChecked()) {
                itemType = ItemType.FOUND;
            }
            if (lostButton.isChecked()) {
                itemType = ItemType.LOST;
            }
            if (donateButton.isChecked()) {
                itemType = ItemType.NEED;
            }
            name = nameBox.getText().toString();
            description = descriptionBox.getText().toString();
            reward = rewardBox.getText().toString();
            itemCategory = Category.valueOf(spinnerBox.getSelectedItem().toString());
            Spinner categorySpinner = (Spinner) findViewById(R.id.categorySpinner);
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
                    Model.getItemList().add(new Item(itemType, name , description, "Atlanta", itemCategory, new Double(reward), Model.getUserList().get(0)));
                    goToDisplayAllItemActivity();
                }

            });
        }
    private void goToDisplayAllItemActivity(){
        Intent intent = new Intent(this,DisplayAllItemActivity.class);
        startActivity(intent);
    }

}
