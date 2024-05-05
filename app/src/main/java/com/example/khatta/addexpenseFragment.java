package com.example.khatta;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class addexpenseFragment extends Fragment {

    EditText editTextUsername, editTextDescription, editTextAmount;
    Button addButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_addexpense, container, false);

        editTextUsername = view.findViewById(R.id.edit_Text_Username);
        editTextDescription = view.findViewById(R.id.description_edit_text);
        editTextAmount = view.findViewById(R.id.editTextAmount);
        addButton = view.findViewById(R.id.add_split_button);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsername.getText().toString();
                String description = editTextDescription.getText().toString();
                double amount = Double.parseDouble(editTextAmount.getText().toString());
                String logDetails = "Username: " + username + "\nDescription: " + description + "\nAmount: " + amount;
                ((MainActivity)getActivity()).setLogDetails(logDetails);
            }
        });

        return view;
    }
}
