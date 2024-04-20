package com.example.khatta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class Login extends AppCompatActivity {

    EditText username, password;
    private Button loginButton;
    private AppDatabase userDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);

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

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredUsername = username.getText().toString();
                String enteredPassword = password.getText().toString();
                validateCredentials(enteredUsername, enteredPassword);

            }
        });

    }
    private boolean validateAdmin(String username, String password) {

        return username.equals("admin") && password.equals("admin123");
    }

    public void validateCredentials(String username, String password) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                List<User> userList = userDB.getUserDAO().getAllUser();
                boolean credentialsMatch = false;
                for (User user : userList) {
                    if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                        credentialsMatch = true;
                        break;
                    }
                }
                boolean finalCredentialsMatch = credentialsMatch;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (finalCredentialsMatch) {
                            Intent intent = new Intent(Login.this, Landing.class);
                            intent.putExtra("username", username);
                            startActivity(intent);
                        } else {
                            Toast.makeText(Login.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

}