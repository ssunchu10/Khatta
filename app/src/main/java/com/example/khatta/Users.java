package com.example.khatta;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Users extends AppCompatActivity {
    private LinearLayout usersLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.users);

        usersLayout = findViewById(R.id.usersLayout);

        String[] userList = {"User1", "User2", "User3", "User4", "User5"};

        for (String user : userList) {
            Button button = createUserButton(user);
            usersLayout.addView(button);
        }

    }

    private void showConfirmationDialog(final String username) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.delete_user));
        builder.setMessage(getString(R.string.confirm_delete_user, username));
        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showToast(getString(R.string.user_deleted, username));
                dialog.dismiss(); // Dismiss dialog after user confirmation
            }
        });
        builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss(); // Dismiss dialog if user chooses not to delete
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    // Function to show a toast message (for demonstration purpose)
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private Button createUserButton(String username) {
        Button button = new Button(this);
        button.setText(username);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        button.setLayoutParams(params);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showConfirmationDialog(username);
            }
        });
        return button;
    }

}
