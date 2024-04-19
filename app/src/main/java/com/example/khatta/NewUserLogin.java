package com.example.khatta;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

public class NewUserLogin extends Activity {

    private EditText newUsername, newPassword, confirmPassword;
    private Button createAccountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_user_login);

        newUsername = findViewById(R.id.new_username_edit_text);
        newPassword = findViewById(R.id.new_password_edit_text);
        confirmPassword = findViewById(R.id.confirm_password_edit_text);
        createAccountButton = findViewById(R.id.create_account_button);

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = newUsername.getText().toString().trim();
                String password = newPassword.getText().toString().trim();
                String confirm = confirmPassword.getText().toString().trim();

                // Validate input fields
                if (validateInput(username, password, confirm)) {
                    // Store username in SharedPreferences
                    storeUsername(username);

                    // Display success toast and potentially navigate to another activity
                    Toast.makeText(NewUserLogin.this, "Account created successfully!", Toast.LENGTH_SHORT).show();
                    // You might want a different activity after account creation
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

        return true; // All validations passed
    }
    private void storeUsername(String username) {
        SharedPreferences sharedPreferences = getSharedPreferences("user_login_info", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", username);

        // Use commit() for synchronous saving
        try {
            editor.commit();
            Log.d("TAG", "Username stored successfully: " + username);
        } catch (Exception e) {
            Log.e("TAG", "Error storing username: " + e.getMessage());
        }
    }


//    private void storeUsername(String username) {
//        SharedPreferences sharedPreferences = getSharedPreferences("user_login_info", MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString("username", username);
//        editor.apply(); // Use apply() for asynchronous saving
//    }
}
