package com.example.myhotel.model;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {

    private String userId;
    private String username;
    private String password;
    private String phoneNumber;
    private String gender;
    private String email;

    public User() {

    }



    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User(Parcel in) {
        userId = in.readString();
        username = in.readString();
        password = in.readString();
        phoneNumber = in.readString();
        gender = in.readString();
        email = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(userId);
        parcel.writeString(username);
        parcel.writeString(password);
        parcel.writeString(phoneNumber);
        parcel.writeString(gender);
        parcel.writeString(email);
    }
}
