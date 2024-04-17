package com.example.khatta;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewUserLogin extends Activity {
    EditText newUsername, newPassword, confirmPassword;
    Button createAccountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_user_login);
        createAccountButton = findViewById(R.id.create_account_button);
        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = newUsername.getText().toString().trim();
                String password = newPassword.getText().toString().trim();
                String confirm = confirmPassword.getText().toString().trim();
                Intent intent = new Intent(NewUserLogin.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

}
