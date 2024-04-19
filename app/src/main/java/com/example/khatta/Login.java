package com.example.khatta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.khatta.database.DatabaseInitializer;
import com.example.khatta.database.UserDao;

public class Login extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);

        Button loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                if (validateAdmin(username, password)) {
                    Intent intent = new Intent(Login.this, LandingPageActivity.class);
                    startActivity(intent);
//                } else if(validate(username,password)){
//                    Intent intent = new Intent(Login.this, LandingPageActivity.class);
//                    startActivity(intent);
                }else {
                    Toast.makeText(Login.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

//    private boolean validate(String username, String password) {
//        UserDao userDao = DatabaseInitializer.getInstance(getApplicationContext()).getDatabase().userDao();
//        User user = userDao.getUserByUsernameAndPassword(username, password);
//        return user != null;
//    }

    private boolean validateAdmin(String username, String password) {

        return username.equals("admin") && password.equals("admin123");
    }

}