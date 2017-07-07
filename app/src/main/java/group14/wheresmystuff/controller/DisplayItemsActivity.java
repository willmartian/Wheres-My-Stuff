package group14.wheresmystuff.controller;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.content.Intent;


import group14.wheresmystuff.R;
import group14.wheresmystuff.model.Model;

/**
 * Created by Richard on 6/28/2017.
 */

public class DisplayItemsActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displayitems);
        populateListView();
        Button backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                goToPage(MainActivity.class);

            }

        });
    }
    private void populateListView() {
        Object[] Items = Model.getItemList().toArray();
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.simple_list_item_1, Items);
        ListView list = (ListView) findViewById(R.id.listViewMain);
        list.setAdapter(adapter);
    }
    public void goToPage(Class next) {
        Intent intent = new Intent(this, next);
        startActivity(intent);
    }
}
