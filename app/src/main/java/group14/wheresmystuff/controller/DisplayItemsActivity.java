package group14.wheresmystuff.controller;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.content.Intent;


import java.util.ArrayList;

import group14.wheresmystuff.R;
import group14.wheresmystuff.model.Item;
import group14.wheresmystuff.model.Item.ItemType;
import group14.wheresmystuff.model.Model;

import static group14.wheresmystuff.R.id.searchBar;

/**
 * Created by Richard on 6/28/2017.
 */

public class DisplayItemsActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displayitems);
        //filtering the list view to only show results with the word "cat" would look like:
        //  populateListView(Model.getItemList("cat"));
        populateListView(); //makes and implicit call to: populateListView(Model.getItemList());
        Button backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                goToPage(MainActivity.class);

            }

        });
        ImageButton searchButton = (ImageButton) findViewById(R.id.imageButton);
        searchButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                EditText edit = (EditText) findViewById(R.id.searchBar);
                String str = edit.getText().toString();
                if (str.toUpperCase().equals("FOUND")) {
                    populateListView(Model.getItemList(Item.ItemType.FOUND));
                }
                else if (str.toUpperCase().equals("LOST")) {
                    populateListView(Model.getItemList(Item.ItemType.LOST));
                }
                else if (str.toUpperCase().equals("NEED")) {
                    populateListView(Model.getItemList(Item.ItemType.NEED));
                }
                else {
                    populateListView(Model.getItemList(str));
                }
            }

        });
    }
    /*
     * Calls the overloaded version of populateListView
     */
    private void populateListView() {
        populateListView(Model.getItemList());
    }

    /**
     * Populates the list view with a given item list.
     * @param itemList the list to populate with
     */
    private void populateListView(ArrayList itemList) {
        Object[] Items = itemList.toArray();
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.simple_list_item_1, Items);
        ListView list = (ListView) findViewById(R.id.listViewMain);
        list.setAdapter(adapter);
    }

    /**
     * Set the app view to a given activity
     * @param next the activity to go to
     */
    public void goToPage(Class next) {
        Intent intent = new Intent(this, next);
        startActivity(intent);
    }
}