package com.example.khatta;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class activityFragment extends Fragment {

    EditText editTextDetails;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_activity, container, false);
        editTextDetails = view.findViewById(R.id.details);

        return view;
    }
    public void setLogDetails(String logDetails) {
        editTextDetails.setText(logDetails);
    }
}
