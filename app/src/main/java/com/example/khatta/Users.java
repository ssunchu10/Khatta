package com.example.khatta;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Users extends AppCompatActivity {
    private ListView listView;
    private String[] users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.users);
        listView = findViewById(R.id.userList);

        // Get intent extras here
        users = getIntent().getStringArrayExtra("users");

        // Check if users is not null before using it
        if (users != null && users.length > 0) {
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1, users);
            listView.setAdapter(arrayAdapter);
        } else {
            Toast.makeText(this, "No users found", Toast.LENGTH_SHORT).show();
        }
    }

    private void showConfirmationDialog(final String username) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.delete_user));
        builder.setMessage(getString(R.string.confirm_delete_user, username));
        builder.setPositiveButton(R.string.yes, (dialog, which) -> {
            showToast(getString(R.string.user_deleted, username));
            dialog.dismiss();
        });
        builder.setNegativeButton(R.string.no, (dialog, which) -> {
            dialog.dismiss();
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


}