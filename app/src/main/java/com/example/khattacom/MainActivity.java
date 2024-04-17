package com.example.khattacom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.khattacom.R;

public class MainActivity extends AppCompatActivity {

    private Button signupButton;
    private Button adminLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signupButton = findViewById(R.id.signupbutton);
        adminLogin = findViewById(R.id.adminloginbutton);

//        signupButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, NewUserLogin.class);
//                startActivity(intent);
//            }
//        });
//
//        adminLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, AdminLoginPage.class);
//                startActivity(intent);
//            }
//        });
    }
}