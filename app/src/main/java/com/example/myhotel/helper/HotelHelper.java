package com.example.myhotel.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.myhotel.model.Hotel;
import com.example.myhotel.model.User;

public class HotelHelper extends SqlHelper{
    public HotelHelper(@Nullable Context context) {
        super(context);
    }
    public boolean insertDataHotel(Hotel hotel){
        int count = (int) getHotelCount();

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv  = new ContentValues();
        cv.put(_hotelId,String.format("HT%03d", count));
        cv.put(_hotelName,hotel.getHotelName());
        cv.put(_hotel_price,hotel.getPrice());
        cv.put(_hotelDesc,hotel.getDesc());
        cv.put(_hotelLocation,hotel.getHotelLocation());

        long result = db.insert(_databaseTableHotels,null ,cv);
        if(result == -1)return false;
        return true;
    }

    public boolean updateDataUser(Hotel hotel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv  = new ContentValues();
        cv.put(_hotelId,hotel.getHotelId());
        cv.put(_hotelName,hotel.getHotelName());
        cv.put(_hotel_price,hotel.getPrice());
        cv.put(_hotelDesc,hotel.getDesc());
        cv.put(_hotelLocation,hotel.getHotelLocation());

        long result = db.update(_databaseTableUsers
                ,cv
                ,"where hotel_id = ?"
                , new String[]{hotel.getHotelId()} );

        if(result == -1)return false;
        return true;
    }

    public boolean deleteDataUser(String hotelId){
        SQLiteDatabase db = this.getWritableDatabase();

        long result = db.delete(_databaseTableUsers
                ,"where hotel_id = ?"
                , new String[]{hotelId} );

        if(result == -1)return false;
        return true;
    }

    //read cursor
    public Cursor getAllDataHotels(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT *"+
                "From Hotels", null);
        return result;
    }

    public long getHotelCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db, _databaseTableHotels);
        db.close();
        return count;
    }

}
