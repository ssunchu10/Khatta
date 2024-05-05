package com.example.khatta;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

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

public class Landing extends AppCompatActivity {
    private AppDatabase userDB;
    private List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_page);

        TextView usernameTextView = findViewById(R.id.usernameTextView);
        ImageButton logout = findViewById(R.id.logout_button);
        String username = getIntent().getStringExtra("username");
        usernameTextView.setText(username);
        int admin = getIntent().getIntExtra("admin", 0);
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

        Button getUsersButton = findViewById(R.id.update_users_button);
        getUsersButton.setEnabled(false);
        if(admin==1){
            getUsersButton.setEnabled(true);
        }
        getUsersButton.setOnClickListener(v -> getUserInBackground());
        logout.setOnClickListener(v -> {
            Intent intent = new Intent(Landing.this, MainActivity.class);
            startActivity(intent);
        });
    }

    public void getUserInBackground() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            userList = userDB.getUserDAO().getAllUser();

            runOnUiThread(() -> {

                String[] users = new String[userList.size()];
                int i = 0;
                for (User user : userList) {
                    users[i] = user.getUsername();
                    i++;
                }
                Intent intent = new Intent(Landing.this, Users.class);
                intent.putExtra("users", users);
                startActivity(intent);
            });
        });
    }
}
