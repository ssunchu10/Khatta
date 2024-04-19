package com.example.khatta;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.khatta.database.AppDatabase;
import com.example.khatta.database.User;

import java.util.List;

public class Landing extends AppCompatActivity {

    private TextView usernameTextView;
    AppDatabase userDB;
    Button getUsers = findViewById(R.id.update_users_button);
    List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_page);
        usernameTextView = findViewById(R.id.usernameTextView);
//        RoomDatabase.Callback myCallback = new RoomDatabase.Callback() {
//            @Override
//            public void onCreate(@NonNull SupportSQLiteDatabase db) {
//                super.onCreate(db);
//            }
//
//            @Override
//            public void onOpen(@NonNull SupportSQLiteDatabase db) {
//                super.onOpen(db);
//            }
//        };
//        userDB = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "userDB").addCallback(myCallback).build();
//
//        // Retrieve username from Shared Preferences
//        SharedPreferences sharedPreferences = getSharedPreferences("user_login_info", MODE_PRIVATE);
//        String loggedInUsername = sharedPreferences.getString("username", "");
//
//        // Check for empty username and display accordingly
//        if (loggedInUsername.isEmpty()) {
//            usernameTextView.setText("Not logged in");
//        } else {
//            usernameTextView.setText(loggedInUsername); // Display welcome message with username
//        }
//
//        getUsers.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getUserInBackground();
//            }
//        });
//    }
//
//    public void getUserInBackground() {
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
//        Handler handler = new Handler(Looper.getMainLooper());
//        executorService.execute(new Runnable() {
//            @Override
//            public void run() {
//                // background task
//                userList = userDB.getUserDAO().getAllUser();
//                //on finishing task
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        StringBuilder sb = new StringBuilder();
//                        for (User user : userList) {
//                            sb.append(user.getUsername()).append(" : ").append(user.getPassword());
//                            sb.append("\n");
//                        }
//                        String finalData = sb.toString();
//                        Toast.makeText(Landing.this, "" + finalData, Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//        });
    }
}