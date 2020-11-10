package com.example.myhotel.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Hotel implements Parcelable {
    private String hotelId;
    private String hotelName;
    private String hotelLocation;
    private int price;
    private String desc;

    public Hotel() {

    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelLocation() {
        return hotelLocation;
    }

    public void setHotelLocation(String hotelLocation) {
        this.hotelLocation = hotelLocation;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Hotel(Parcel in) {
        hotelId = in.readString();
        hotelName = in.readString();
        hotelLocation = in.readString();
        price = in.readInt();
        desc = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(hotelId);
        dest.writeString(hotelName);
        dest.writeString(hotelLocation);
        dest.writeInt(price);
        dest.writeString(desc);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Hotel> CREATOR = new Creator<Hotel>() {
        @Override
        public Hotel createFromParcel(Parcel in) {
            return new Hotel(in);
        }

        @Override
        public Hotel[] newArray(int size) {
            return new Hotel[size];
        }
    };
}
