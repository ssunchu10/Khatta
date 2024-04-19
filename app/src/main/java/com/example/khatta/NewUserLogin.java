package com.example.khatta;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.khatta.database.AppDatabase;
import com.example.khatta.database.User;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NewUserLogin extends Activity {

    private EditText newUsername, newPassword, confirmPassword;
    private Button createAccountButton;
    AppDatabase userDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_user_login);

        newUsername = findViewById(R.id.new_username_edit_text);
        newPassword = findViewById(R.id.new_password_edit_text);
        confirmPassword = findViewById(R.id.confirm_password_edit_text);
        createAccountButton = findViewById(R.id.create_account_button);

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

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = newUsername.getText().toString().trim();
                String password = newPassword.getText().toString().trim();
                String confirm = confirmPassword.getText().toString().trim();

                if (validateInput(username, password, confirm)) {
                    User user = new User(username, password);
                    addUserInBackground(user);

                    Toast.makeText(NewUserLogin.this, "Account created successfully!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(NewUserLogin.this, Login.class); // Or another activity
                    startActivity(intent);
                }
            }
        });
    }

    private boolean validateInput(@NonNull String username, @NonNull String password, @NonNull String confirm) {
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, "Username cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Password cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (TextUtils.isEmpty(confirm)) {
            Toast.makeText(this, "Confirm password cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!password.equals(confirm)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    public void addUserInBackground(User user){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                userDB.getUserDAO().addUser(user);

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(NewUserLogin.this, "Added to Database", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
