package com.example.myhotel.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.myhotel.model.User;

public class UserHelper extends SqlHelper {

    public UserHelper(@Nullable Context context) {
        super(context);
    }
    public boolean insertDataUser(User user){
        int count = (int) getuserCount();

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv  = new ContentValues();
        cv.put(_userId,String.format("US%03d", count));
        cv.put(_userName,user.getUsername());
        cv.put(_userPass,user.getPassword());
        cv.put(_userEmail,user.getEmail());
        cv.put(_userGender,user.getGender());
        cv.put(_phoneNumber, user.getPhoneNumber());


        long result = db.insert(_databaseTableUsers,null ,cv);
        if(result == -1)return false;
        return true;
    }

    public boolean updateDataUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv  = new ContentValues();
        cv.put(_userId,user.getUserId());
        cv.put(_userName,user.getUsername());
        cv.put(_userPass,user.getPassword());
        cv.put(_userEmail,user.getEmail());
        cv.put(_userGender,user.getGender());
        cv.put(_phoneNumber, user.getPhoneNumber());

        long result = db.update(_databaseTableUsers
                ,cv
                ,"where user_id = ?"
                , new String[]{user.getUsername()} );

        if(result == -1)return false;
        return true;
    }

    public boolean deleteDataUser(String userId){
        SQLiteDatabase db = this.getWritableDatabase();

        long result = db.delete(_databaseTableUsers
                ,"where user_id = ?"
                , new String[]{userId} );

        if(result == -1)return false;
        return true;
    }

    //read cursor
    public Cursor getAllDataUser(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT *"+
                "From Users", null);
        return result;
    }

    public long getuserCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db, _databaseTableUsers);
        db.close();
        return count;
    }

}
