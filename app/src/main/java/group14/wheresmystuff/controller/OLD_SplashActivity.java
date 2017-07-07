//package group14.wheresmystuff.controller;
//
//import android.support.v7.app.AppCompatActivity;
//
//import android.os.Bundle;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.Button;
//import android.content.Intent;
//
//import group14.wheresmystuff.R;
//import group14.wheresmystuff.model.Model;
//
//
//public class SplashActivity extends AppCompatActivity {
//
//    private Model model = new Model();
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_splash);
//        Button login = (Button) findViewById(R.id.login_button);
//        login.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//
//                goToPage(LoginActivity.class);
//
//            }
//
//        });
//        Button registerButton = (Button) findViewById(R.id.registerButton);
//        registerButton.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                goToPage(RegistrationActivity.class);
//            }
//        });
//    }
//
//    public void goToPage(Class next) {
//        Intent intent = new Intent(this, next);
//        startActivity(intent);
//    }
//}
