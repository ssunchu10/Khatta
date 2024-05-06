package com.example.khatta;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.khatta.Expense;
import com.example.khatta.R;

import java.util.ArrayList;
import java.util.List;

public class activity extends Fragment {

    RecyclerView recyclerView;
    ExpenseAdapter expenseAdapter;
    List<Expense> expenseList;

    public activity() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_activity, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        expenseList = new ArrayList<>();
        expenseAdapter = new ExpenseAdapter(getActivity(), expenseList);
        recyclerView.setAdapter(expenseAdapter);
        addSampleExpense();

        return view;
    }

    private void addSampleExpense() {
        Expense expense = new Expense("John", "Lunch", "10.50");
        expenseList.add(expense);
        expenseAdapter.notifyDataSetChanged();
    }
}
