package com.example.khatta;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Users extends AppCompatActivity {
    String[] users = getIntent().getStringArrayExtra("users");
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.users);
        listView = findViewById(R.id.userList);

//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
//                R.layout.item, R.id.itemTextView, users);
//        listView.setAdapter(arrayAdapter);

//        if (users != null && users.length > 0) {
//            CustomAdapter adapter = new CustomAdapter(new String[]{users[0]});
//            listView.setAdapter(adapter);
//        } else {
//            Toast.makeText(this, "No users found", Toast.LENGTH_SHORT).show();
//        }
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