package com.example.khatta;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.khatta.database.AppDatabase;
import com.example.khatta.database.User;
import com.example.khatta.database.UserDao;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class YourActivity extends AppCompatActivity {

    private AppDatabase userDB;
    private UserDao userDao;
    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete);
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


        Button deleteAllButton = findViewById(R.id.deleteAllButton);
        deleteAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAllUsers();
            }
        });
    }

    private void deleteAllUsers() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                userDB.getUserDAO().deleteAll();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showToast("All users deleted");
                    }
                });
            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(YourActivity.this, message, Toast.LENGTH_SHORT).show();

    }
}

