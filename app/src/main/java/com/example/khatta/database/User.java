package com.example.khatta.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "User")
public class User {
    @ColumnInfo(name="userID")
    @PrimaryKey(autoGenerate = true)
    private int userId;

    @ColumnInfo(name="username")
    private String username;
    @ColumnInfo(name="password")
    private String password;

    @Ignore
    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.userId = 0;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}