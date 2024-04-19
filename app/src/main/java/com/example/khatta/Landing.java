package com.example.khatta;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.khatta.database.AppDatabase;
import com.example.khatta.database.User;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Landing extends AppCompatActivity {

    private TextView usernameTextView;
    private AppDatabase userDB;
    private List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_page);

        usernameTextView = findViewById(R.id.usernameTextView);
        RoomDatabase.Callback myCallback = new RoomDatabase.Callback() {
            @Override
            public void onCreate(SupportSQLiteDatabase db) {
                super.onCreate(db);
            }

            @Override
            public void onOpen(SupportSQLiteDatabase db) {
                super.onOpen(db);
            }
        };

        userDB = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "userDB").addCallback(myCallback).build();

        Button getUsersButton = findViewById(R.id.update_users_button);
        getUsersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUserInBackground();
            }
        });
    }

    public void getUserInBackground() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                userList = userDB.getUserDAO().getAllUser();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        StringBuilder sb = new StringBuilder();
                        for (User user : userList) {
                            sb.append(user.getUsername()).append(" : ").append(user.getPassword());
                            sb.append("\n");
                        }
                        String finalData = sb.toString();
                        Toast.makeText(Landing.this, finalData, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
