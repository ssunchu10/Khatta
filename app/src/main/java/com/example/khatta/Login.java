package com.example.khatta;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
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

    private EditText username, password;
    private AppDatabase userDB;
    private CheckBox isAdmin;

    private int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        Button loginButton = findViewById(R.id.loginButton);
        isAdmin = (CheckBox) findViewById(R.id.checkButton);

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

        loginButton.setOnClickListener(v -> {
            String enteredUsername = username.getText().toString();
            String enteredPassword = password.getText().toString();
            validateCredentials(enteredUsername, enteredPassword);

        });

    }

    public void validateCredentials(String username, String password) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            List<User> userList = userDB.getUserDAO().getAllUser();
            boolean credentialsMatch = false;
            for (User user : userList) {
                if("admin2".equals(username) && "admin2".equals(password) && isAdmin.isChecked()) {
                    flag = 1;
                    credentialsMatch = true;
                    break;
                } else if (user.getUsername().equals(username) && user.getPassword().equals(password) && !isAdmin.isChecked()) {
                    credentialsMatch = true;
                    break;
                }
            }
            boolean finalCredentialsMatch = credentialsMatch;
            runOnUiThread(() -> {
                if (finalCredentialsMatch && flag == 1) {
                    Intent intent = new Intent(Login.this, Landing.class);
                    intent.putExtra("username", username);
                    intent.putExtra("admin", flag);
                    startActivity(intent);
                } else if (finalCredentialsMatch && flag == 0){
                    Intent intent = new Intent(Login.this, YourActivity.class);
                    intent.putExtra("username", username);
                    startActivity(intent);
                }else {
                    Toast.makeText(Login.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

}