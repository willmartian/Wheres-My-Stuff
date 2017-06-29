package group14.wheresmystuff;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.app.ListActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.app.Activity;

/**
 * Created by Richard on 6/28/2017.
 */

public class DisplayAllItemActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displayitems);
        populateListView();
        Button backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                goToMainActivity();

            }

        });
    }
    private void populateListView() {
        Object[] Items = Model.getItemList().toArray();
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.simple_list_item_1, Items);
        ListView list = (ListView) findViewById(R.id.listViewMain);
        list.setAdapter(adapter);
    }

    private void goToMainActivity(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

}
