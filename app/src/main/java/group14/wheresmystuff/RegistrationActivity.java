package group14.wheresmystuff;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;


public class RegistrationActivity extends AppCompatActivity {

    private EditText mNameView;
    private EditText mPasswordView;
    private EditText mUserIDView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        //
        mNameView = (EditText) findViewById(R.id.name_textBox);
        mPasswordView = (EditText) findViewById(R.id.password_textBox);
        mUserIDView = (EditText) findViewById(R.id.username_textBox);

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

                String userID = mUserIDView.getText().toString();
                String password = mPasswordView.getText().toString();
                String name = mNameView.getText().toString();
                Model.getUserList().add(new User(name, userID, password, ""));
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
