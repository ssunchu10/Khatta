package com.example.khatta;

import android.content.Intent;
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

        String loggedInUsername = getLoggedInUsername();
        usernameTextView.setText(loggedInUsername);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(LandingPageActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_DELAY);
    }

    private String getLoggedInUsername() {
        return "USERS";
    }
}