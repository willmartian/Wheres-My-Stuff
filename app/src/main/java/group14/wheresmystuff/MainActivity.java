package group14.wheresmystuff;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.content.Intent;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button logout = (Button) findViewById(R.id.logoutButton);
        logout.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                goToSplashActivity();

            }

        });
    }


    private void goToSplashActivity(){
        Intent intent = new Intent(this, SplashActivity.class);
        startActivity(intent);
    }


}
