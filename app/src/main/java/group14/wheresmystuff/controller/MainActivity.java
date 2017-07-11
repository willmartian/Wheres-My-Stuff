package group14.wheresmystuff.controller;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.content.Intent;

import com.google.android.gms.maps.OnMapReadyCallback;

import group14.wheresmystuff.R;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button logout = (Button) findViewById(R.id.logoutButton);
        logout.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                goToPage(LoginActivity.class);

            }

        });

        Button addItem = (Button) findViewById(R.id.addItemButton);
        addItem.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                goToPage(SubmitItemActivity.class);

            }

        });

        Button showItems = (Button) findViewById(R.id.viewItemsButton);
        showItems.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                goToPage(DisplayItemsActivity.class);

            }

        });
    }



    public void goToPage(Class next) {
        Intent intent = new Intent(this, next);
        startActivity(intent);
    }
}
