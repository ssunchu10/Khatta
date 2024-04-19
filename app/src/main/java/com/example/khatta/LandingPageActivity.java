package com.example.khatta;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.khatta.database.AppDatabase;
import com.example.khatta.database.User;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LandingPageActivity extends AppCompatActivity {

    private static final long SPLASH_DELAY = 10000; // 10 seconds
    private TextView usernameTextView;
    AppDatabase userDB;
    Button getUsers = findViewById(R.id.update_users_button);

    List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_page);

        usernameTextView = findViewById(R.id.usernameTextView);

        RoomDatabase.Callback myCallback = new RoomDatabase.Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
            }

            @Override
            public void onOpen(@NonNull SupportSQLiteDatabase db) {
                super.onOpen(db);
            }
        };

        userDB = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "userDB").addCallback(myCallback).build();


        // Retrieve username from Shared Preferences
//        SharedPreferences sharedPreferences = getSharedPreferences("user_login_info", MODE_PRIVATE);
//        String loggedInUsername = sharedPreferences.getString("username", "");
//
//        // Check for empty username and display accordingly
//        if (loggedInUsername.isEmpty()) {
//            usernameTextView.setText("Not logged in");
//        } else {
//            usernameTextView.setText(loggedInUsername); // Display welcome message with username
//        }

        getUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUserInBackground();
            }
        });

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent intent = new Intent(LandingPageActivity.this, MainActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        }, SPLASH_DELAY);
    }

    public void getUserInBackground(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                // background task
                userList = userDB.getUserDAO().getAllUser();

                //on finishing task
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        StringBuilder sb = new StringBuilder();
                        for(User user : userList){
                            sb.append(user.getUsername()).append(" : ").append(user.getPassword());
                            sb.append("\n");
                        }
                        String finalData = sb.toString();
                        Toast.makeText(LandingPageActivity.this, ""+finalData, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}