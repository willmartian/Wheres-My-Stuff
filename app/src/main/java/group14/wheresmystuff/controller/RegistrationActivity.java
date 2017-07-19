package group14.wheresmystuff.controller;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.RadioButton;

import java.util.ArrayList;

import group14.wheresmystuff.R;
import group14.wheresmystuff.model.Admin;
import group14.wheresmystuff.model.Model;
import group14.wheresmystuff.model.User;


public class RegistrationActivity extends AppCompatActivity {

    private EditText mNameView;
    private EditText mPasswordView;
    private EditText mUserIDView;
    private RadioButton mAdminRadio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        getSupportActionBar().setTitle("Where's My Stuff? - Register");
        //
        mNameView = (EditText) findViewById(R.id.name_textBox);
        mPasswordView = (EditText) findViewById(R.id.password_textBox);
        mUserIDView = (EditText) findViewById(R.id.username_textBox);
        mAdminRadio = (RadioButton) findViewById(R.id.admin_radioButton);

        Button register = (Button) findViewById(R.id.register_button);
        Button cancel = (Button) findViewById(R.id.cancel_button);
        cancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                goToPage(LoginActivity.class);
            }
        });
        register.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                String userID = mUserIDView.getText().toString().trim();
                String password = mPasswordView.getText().toString();
                String name = mNameView.getText().toString();

                mNameView.setError(null);
                mUserIDView.setError(null);
                mPasswordView.setError(null);

                boolean cancel = false;
                View focusView = null;

                if (!isPasswordValid(password)) {
                    mPasswordView.setError(getString(R.string.error_invalid_password));
                    focusView = mPasswordView;
                    cancel = true;
                }

                if (TextUtils.isEmpty(password)) {
                    mPasswordView.setError(getString(R.string.error_field_required));
                    focusView = mPasswordView;
                    cancel = true;
                }

                if (TextUtils.isEmpty(userID)) {
                    mUserIDView.setError(getString(R.string.error_field_required));
                    focusView = mUserIDView;
                    cancel = true;
                }

                if (!isUserIDValid(userID)) {
                    mUserIDView.setError("Username already exists");
                    focusView = mUserIDView;
                    cancel = true;
                }

                if (TextUtils.isEmpty(name)) {
                    mNameView.setError(getString(R.string.error_field_required));
                    focusView = mNameView;
                    cancel = true;
                }

                if (cancel) {
                    focusView.requestFocus();
                } else {
                    boolean isAdmin = mAdminRadio.isChecked();
                    if (isAdmin) {
                        Model.addUser(new Admin(name, userID, password, ""));
                    } else {
                        Model.addUser(new User(name, userID, password, ""));
                    }
                    goToPage(LoginActivity.class);
                }
            }

        });
    }

    private boolean isUserIDValid(String userID){
        boolean valid = true;
        ArrayList<User> userList = Model.getUserList();
        for(int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getLoginID().equals(userID)){
                valid = false;
            }
        }
        return valid;
    }


    private boolean isPasswordValid(String password){
        return password.length() >= 4;
    }

    public void goToPage(Class next) {
        Intent intent = new Intent(this, next);
        startActivity(intent);
    }
}
