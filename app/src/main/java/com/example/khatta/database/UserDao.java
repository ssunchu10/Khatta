package com.example.khatta.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao{

    @Insert
    public void addUser(User user);

    @Update
    public void updateUser(User user);

    @Delete
    public void deleteUser(User user);

    @Query("select * from User where username ==:username AND password ==:password")
    User getUserByUsernameAndPassword(String username, String password);

    @Query("select * from User")
    public List<User> getAllUser();

    @Query("select * from User where userID==:user_id")
    public User getUser(int user_id);

    @Query("delete from User")
    public void deleteAll();
}