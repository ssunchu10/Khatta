package com.example.khatta;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LandingPageActivity extends AppCompatActivity {

    private static final long SPLASH_DELAY = 10000; // 10 seconds
    private TextView usernameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_page);

        usernameTextView = findViewById(R.id.usernameTextView);

        // Retrieve username from Shared Preferences
        SharedPreferences sharedPreferences = getSharedPreferences("user_login_info", MODE_PRIVATE);
        String loggedInUsername = sharedPreferences.getString("username", "");

        // Check for empty username and display accordingly
        if (loggedInUsername.isEmpty()) {
            usernameTextView.setText("Not logged in");
        } else {
            usernameTextView.setText(loggedInUsername); // Display welcome message with username
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(LandingPageActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_DELAY);
    }
}