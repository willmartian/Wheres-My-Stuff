package group14.wheresmystuff.controller;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
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
    ListView list;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displayitems);
        list = (ListView) findViewById(R.id.listViewMain);
        //filtering the list view to only show results with the word "cat" would look like:
        //  populateListView(Model.getItemList("cat"));
        populateListView(Model.getItemList(Item.ItemType.LOST));
        populateListView(); //makes and implicit call to: populateListView(Model.getItemList());

        list.setOnItemClickListener(new OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                getItemAtPosition(position);
                Bundle bundle = new Bundle();
                bundle.putInt("itemIndex", position);
                goToPage(ItemActivity.class, bundle);
            }
        });
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

                try {
                    populateListView(Model.getItemList(ItemType.valueOf(str.toUpperCase())));
                } catch(IllegalArgumentException e) {
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
        list = (ListView) findViewById(R.id.listViewMain);
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


    public void goToPage(Class next, Bundle bundle) {
        Intent intent = new Intent(this, next);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}


