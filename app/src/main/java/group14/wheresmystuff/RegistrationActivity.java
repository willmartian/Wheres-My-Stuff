package group14.wheresmystuff;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.content.Intent;



public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Button register = (Button) findViewById(R.id.register_button);
        Button cancel = (Button) findViewById(R.id.cancel_button);
        cancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSplashActivity();
            }
        });
        register.setOnClickListener(new OnClickListener() {

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
    private void goToSplashActivity(){
        Intent intent = new Intent(this, SplashActivity.class);
        startActivity(intent);
    }

}
