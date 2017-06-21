package group14.wheresmystuff;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.content.Intent;



public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Button login = (Button) findViewById(R.id.login_button);
        login.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                goToLoginActivity();

            }

        });
    }

    private void goToLoginActivity(){
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }


}
