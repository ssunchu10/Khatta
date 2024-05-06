package com.example.khatta;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Fragment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_navigation_view);
        TextView usernameTextView = findViewById(R.id.usernameTextView);
        String username = getIntent().getStringExtra("username");
        usernameTextView.setText(username);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            androidx.fragment.app.Fragment selectedFragment = null;

            if (item.getItemId() == R.id.menu_add_expense) {
                selectedFragment = new AddExpenseFragment();
            } else if (item.getItemId() == R.id.menu_activity) {
                selectedFragment = new Activities();
            } else if (item.getItemId() == R.id.menu_logout) {
                logout();
                return true;
            }

            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                return true;
            }

            return false;
        });
    }

    private void logout() {
        Intent intent = new Intent(Fragment.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
