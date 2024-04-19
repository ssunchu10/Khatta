package com.example.khatta;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    private Button loginButton, signupButton;
    private TextView usernameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        loginButton = findViewById(R.id.loginButton);
        usernameTextView = findViewById(R.id.username); // Assuming you have a TextView for username display

        // Retrieve username from Shared Preferences (optional, for pre-filling username)
        SharedPreferences sharedPreferences = getSharedPreferences("user_login_info", MODE_PRIVATE);
        String savedUsername = sharedPreferences.getString("username", "");
        usernameTextView.setText(savedUsername); // Pre-fill username if available

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement login logic here (e.g., check username/password against database or local storage)
                // For this example, we'll assume successful login

                // Retrieve username from Shared Preferences
                SharedPreferences sharedPreferences = getSharedPreferences("user_login_info", MODE_PRIVATE);
                String loggedInUsername = sharedPreferences.getString("username", "");

                // Display username or "Not logged in"
                if (loggedInUsername.isEmpty()) {
                    usernameTextView.setText("Not logged in");
                } else {
                    usernameTextView.setText(loggedInUsername);
                }

                // Navigate to LandingPageActivity
                Intent intent = new Intent(Login.this, LandingPageActivity.class);
                startActivity(intent);
            }
        });

//        signupButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Login.this, NewUserLogin.class);
//                startActivity(intent);
//            }
//        });
    }
    private boolean validateAdmin(String username, String password) {

        return username.equals("admin") && password.equals("admin123");
    }

}