package com.example.khatta;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class AddExpenseFragment extends Fragment {

    EditText usernameEditText, descriptionEditText, amountEditText;
    Button addSplitButton;

    public AddExpenseFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_expense_fragment, container, false);


        usernameEditText = view.findViewById(R.id.edit_Text_Username);
        descriptionEditText = view.findViewById(R.id.description_edit_text);
        amountEditText = view.findViewById(R.id.editTextAmount);
        addSplitButton = view.findViewById(R.id.add_split_button);


        addSplitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String description = descriptionEditText.getText().toString();
                String amount = amountEditText.getText().toString();


                if (username.isEmpty() || description.isEmpty() || amount.isEmpty()) {
                    Toast.makeText(getActivity(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                String message = "Expense added: \nUsername: " + username + "\nDescription: " + description + "\nAmount: " + amount;
                Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();

                usernameEditText.setText("");
                descriptionEditText.setText("");
                amountEditText.setText("");
            }
        });

        return view;
    }
}
