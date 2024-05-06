package com.example.khatta.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ExpenseDao{

    @Insert
    public void addExpense(Expense expense);

    @Update
    public void updateExpense(Expense expense);

    @Delete
    public void deleteExpense(Expense expense);

    @Query("select * from Expense")
    public List<Expense> getAllExpense();

    @Query("delete from Expense")
    public void deleteAll();
}