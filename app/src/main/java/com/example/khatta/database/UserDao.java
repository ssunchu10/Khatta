package com.example.khatta.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.khatta.User;

@Dao
public interface UserDao {
    @Insert
    void insert(User user);

    @Query("SELECT * FROM users WHERE username = :username AND password = :password")
    User getUserByUsernameAndPassword(String username, String password);

    @Query("DELETE FROM users")
    void deleteAll();
}