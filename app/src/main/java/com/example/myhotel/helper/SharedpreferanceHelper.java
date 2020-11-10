package com.example.myhotel.helper;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.myhotel.model.User;

public class SharedpreferanceHelper {

    private SharedPreferences sharedPreferences;

    public SharedpreferanceHelper(Context context){
        sharedPreferences = context.getSharedPreferences("sharedPref", Context.MODE_PRIVATE);

    }

    public void save(Context context, User user){
        sharedPreferences = context.getSharedPreferences("sharedPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("password", user.getPassword());
        editor.putString("email", user.getEmail());
        editor.putString("userId", user.getUserId());
        editor.putString("username", user.getUsername());
        // you can use apply() or commit() to saving data to shared preferences xml
        editor.apply();
    }

    public User load(){
        User user = new User();
        user.setUsername(sharedPreferences.getString("email", ""));
        user.setPassword(sharedPreferences.getString("password", ""));
        user.setUserId(sharedPreferences.getString("userId", ""));
        user.setUserId(sharedPreferences.getString("username", ""));
        return user;
    }

    public void logout(Context context){
        sharedPreferences = context.getSharedPreferences("sharedPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear().commit();

    }
}
