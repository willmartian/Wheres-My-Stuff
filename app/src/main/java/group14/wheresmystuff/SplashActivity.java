package group14.wheresmystuff;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.content.Intent;
import android.view.View.OnClickListener;



public class SplashActivity extends AppCompatActivity {

    private Model model = new Model();
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
        Button registerButton = (Button) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                goToRegisterActivity();
            }
        });
    }

    private void goToLoginActivity(){
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }

    private void goToRegisterActivity(){
        Intent intent = new Intent(this,RegistrationActivity.class);
        startActivity(intent);
    }


}
