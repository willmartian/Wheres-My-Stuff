package group14.wheresmystuff.controller;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import android.location.Location;
import android.content.Context;

import java.io.FileNotFoundException;
import java.io.IOException;

import group14.wheresmystuff.R;
import group14.wheresmystuff.model.Item;
import group14.wheresmystuff.model.Item.*;
import group14.wheresmystuff.model.Model;


/**
 * Created by Richard on 6/22/2017.
 */

public class SubmitItemActivity extends AppCompatActivity {
    public static final int GET_FROM_GALLERY = 13;
    private FusedLocationProviderClient fusedLocationClient;
    String name;
    String description;
    String reward;
    String location;
    Bitmap icon;
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

    Boolean edit = false;
    Item item;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submititem);
        getSupportActionBar().setTitle("Where's My Stuff? - " + Model.getActiveUser().getName());

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        Bundle itemBundle = getIntent().getExtras();
        if (itemBundle != null) {
            item = Model.getItemList().get(itemBundle.getInt("itemIndex"));
            edit = true;
        }
        //grabbing form data
        foundButton = (RadioButton) findViewById(R.id.foundButton);
        lostButton = (RadioButton) findViewById(R.id.lostButton);
        donateButton = (RadioButton) findViewById(R.id.donateButton);
        nameBox = (EditText) findViewById(R.id.nameTextBox);
        descriptionBox = (EditText) findViewById(R.id.descriptionTextBox);
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


        final AppCompatActivity thisAct = this;
        Button locationButton = (Button) findViewById(R.id.locationButton);
        locationButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                final int MY_PERMISSION_ACCESS_COURSE_LOCATION = 11;
                if ( ContextCompat.checkSelfPermission( thisAct, android.Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED ) {

                    ActivityCompat.requestPermissions( thisAct, new String[] {  android.Manifest.permission.ACCESS_COARSE_LOCATION  },
                            MY_PERMISSION_ACCESS_COURSE_LOCATION );
                }

                fusedLocationClient.getLastLocation()
                        .addOnSuccessListener(thisAct, new OnSuccessListener<Location>() {
                            @Override
                            public void onSuccess(Location location) {
                                // Got last known location. In some rare situations this can be null.
                                if (location != null) {
                                    locationBox.setText(location.getLatitude() + ", " + location.getLongitude());
                                }
                            }
                        });

            }

        });

        if (edit) {
            fillInfo();
        }

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
                    if (edit) {
                        Model.removeItem(item);
                    }

                    Model.addItem(new Item(itemType, name, description, location, itemCategory, new Double(reward), Model.getActiveUser(), icon));
                    goToPage(DisplayItemsActivity.class);
                }
            }

        });
    }


    public void onImageClick(View view) {
        startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        //Detects request codes
        if(requestCode==GET_FROM_GALLERY && resultCode == Activity.RESULT_OK) {
            Uri selectedImage = data.getData();
//            Bitmap bitmap = null;
            try {
                icon = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
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

    private void fillInfo() {
        nameBox.setText(item.getName());
        descriptionBox.setText(item.getDescription());
        rewardBox.setText(new Double(item.getReward()).toString());
        locationBox.setText(item.getLocation());
        icon = item.getIcon();
    }

    public void goToPage(Class next) {
        Intent intent = new Intent(this, next);
        startActivity(intent);
    }
}
