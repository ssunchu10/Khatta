package com.example.khatta.database;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Expense")
public class Expense {

    @ColumnInfo(name="expenseID")
    @PrimaryKey(autoGenerate = true)
    private int expenseID;

    @ColumnInfo(name="recipients")
    private String name;
    @ColumnInfo(name="description")
    private String description;
    @ColumnInfo(name="amount")
    private String amount;

    @Ignore
    public Expense() {
    }

    public Expense(String name, String description, String amount) {
        this.name = name;
        this.description = description;
        this.amount = amount;
    }

    public int getExpenseID() {
        return expenseID;
    }

    public void setExpenseID(int expenseID) {
        this.expenseID = expenseID;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getAmount() {
        return amount;
    }



}

