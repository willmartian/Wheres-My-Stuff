package group14.wheresmystuff;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.content.Intent;
import android.widget.Spinner;
import android.widget.ArrayAdapter;


/**
 * Created by Richard on 6/22/2017.
 */

public class SubmitItemActivity extends AppCompatActivity {
    private Spinner categorySpinner;
    private String[] categoryArray = new String[]{"Keepsake","Heirloom","Miscellaneous"};
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_submititem);
            Spinner categorySpinner = (Spinner) findViewById(R.id.categorySpinner);
            // Create an ArrayAdapter using the string array and a default spinner layout
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_dropdown_item, categoryArray);
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // Apply the adapter to the spinner
            categorySpinner.setAdapter(adapter);
        }
}
