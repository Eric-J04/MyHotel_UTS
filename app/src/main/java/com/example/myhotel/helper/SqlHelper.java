package com.example.myhotel.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SqlHelper extends SQLiteOpenHelper {

    //nama database
    public  static final String _databaseName = "MyHotel.db";
    //nama tabel
    //nama table + s / es
    public  static final String _databaseTableUsers = "Users";
    public  static final String _databaseTableHotels = "Hotels";
    public  static final String _databaseTableOrder = "Orders";

    //versi DB
    public  static final int _databaseVersion = 1;
    //column / attributs user
    public  static final String _userId  = "user_id";
    public  static final String _userName = "user_name";
    public  static final String _userPass  = "user_pass";
    public  static final String _phoneNumber  = "user_phonenumber";
    public  static final String _userGender  = "user_gender";
    public  static final String _userEmail  = "user_email";

    public  static final String _hotelId  = "hotel_id";
    public  static final String _hotelName = "hotel_name";
    public  static final String _hotelLocation  = "hotel_location";
    public  static final String _hotel_price  = "hotel_price";
    public  static final String _hotelDesc  = "hotel_desc";


    public SqlHelper(@Nullable Context context) {
        super(context, _databaseName, null, _databaseVersion);
        SQLiteDatabase db = this.getReadableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE "+_databaseTableUsers+" ("
                +_userId+" VARCHAR(50) PRIMARY KEY, "
                +_userName+" VARCHAR(50), "
                +_phoneNumber+" VARCHAR(50), "
                +_userGender+" VARCHAR(50), "
                +_userEmail+" VARCHAR(50), "
                +_userPass+" VARCHAR(50) " +
                ");";

        sqLiteDatabase.execSQL(query);

        query = "CREATE TABLE "+_databaseTableHotels+" ("
                +_hotelId+" VARCHAR(50) PRIMARY KEY, "
                +_hotelName+" VARCHAR(50), "
                +_hotel_price+" INT, "
                +_hotelLocation+" VARCHAR(50), "
                +_hotelDesc+" VARCHAR(50)" +
                ");";

        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String query = "DROP TABLE IF EXISTS "+_databaseTableUsers;
        sqLiteDatabase.execSQL(query);
        query = "DROP TABLE IF EXISTS "+_databaseTableHotels;
        sqLiteDatabase.execSQL(query);
        onCreate(sqLiteDatabase);
    }



}
