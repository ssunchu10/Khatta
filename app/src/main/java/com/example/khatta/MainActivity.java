package com.example.khatta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.khatta.database.AppDatabase;
import com.example.khatta.database.DatabaseInitializer;


public class MainActivity extends AppCompatActivity {

    private AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button alreadyhaveanaccount = findViewById(R.id.already_have_an_account_button);
        Button createaccount = findViewById(R.id.create_account_button);

        database = DatabaseInitializer.getInstance(this).getDatabase();//chaange it later
        createaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewUserLogin.class);
                startActivity(intent);
            }
        });
        alreadyhaveanaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
            }
        });

    }
}