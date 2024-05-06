package com.example.khatta;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

public class Users extends AppCompatActivity {
    private RecyclerView userListView;
    private String[] users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.users);
        userListView = findViewById(R.id.UserList);

        users = getIntent().getStringArrayExtra("users");

        if (users != null && users.length > 0) {
            UserAdapter adapter = new UserAdapter(new ArrayList<>(Arrays.asList(users)));
            userListView.setAdapter(adapter);
            userListView.setLayoutManager(new LinearLayoutManager(this));
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("No Users Found");
            builder.setMessage("No users were found in the list.");
            builder.setPositiveButton("OK", (dialog, which) -> finish());
            builder.show();
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