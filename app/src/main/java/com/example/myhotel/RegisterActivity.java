package com.example.myhotel;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myhotel.helper.UserHelper;
import com.example.myhotel.model.User;

public class RegisterActivity extends AppCompatActivity {

    private EditText _username,_password,_phonenumber,_email,_confirmPassword;
    private RadioGroup _rgGender;
    private RadioButton _gender;
    private UserHelper userHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        init();
    }

    private void init(){
        userHelper = new UserHelper(this);
        _username = findViewById(R.id.register_username);
        _password = findViewById(R.id.register_password);
        _phonenumber = findViewById(R.id.register_phonenumber);
        _email = findViewById(R.id.register_email);
        _confirmPassword = findViewById(R.id.register_confirmpassword);

        _rgGender = (RadioGroup)findViewById(R.id.register_rbgendergrup);

        Button register = findViewById(R.id.register_btnregister);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyRegis();
            }
        });
    }

    public void verifyRegis() {
        String strUsername = _username.getText().toString();
        String strPassword = _password.getText().toString();
        String strEmail = _email.getText().toString();
        String strConfirmPassword = _confirmPassword.getText().toString();
        String strPhonenumber = _phonenumber.getText().toString();
        String strGender;

        if(!strEmail.contains(" ")){
            Toast.makeText(RegisterActivity.this, "Fullname must contains two word or more", Toast.LENGTH_SHORT).show();
            return;
        }
        Cursor cursor = userHelper.getAllDataUser();
        Boolean flagLogin = false;

        if(cursor != null){
            while (cursor.moveToNext()){
                if(cursor.getString(cursor.getColumnIndex("user_email")).equals(strEmail)) {
                    Toast.makeText(RegisterActivity.this, "Email already been use", Toast.LENGTH_SHORT).show();
                }
            }
        }


        if(strUsername.length()<5 || strUsername.length() > 25){
            Toast.makeText(RegisterActivity.this, "Username must be between 5 and 25 characters", Toast.LENGTH_SHORT).show();
            return;
        }
        if(strPassword.contains(" ")){
            Toast.makeText(RegisterActivity.this, "Password must not contains whitespace", Toast.LENGTH_SHORT).show();
            return;
        }
        if(strPassword.length() > 15){
            Toast.makeText(RegisterActivity.this, "Password not longer than 15 characters", Toast.LENGTH_SHORT).show();
            return;
        }
        if(!checkString(strPassword)){
            Toast.makeText(RegisterActivity.this, "Password must contains 1 uppercase, 1 special character and 1 numeric character ", Toast.LENGTH_SHORT).show();
            return;
        }
        if(!strPassword.equals(strConfirmPassword)){
            Toast.makeText(RegisterActivity.this, "Your confirm password didnt match with password", Toast.LENGTH_SHORT).show();
            return;
        }

        if(!strPhonenumber.startsWith("+62")){
            Toast.makeText(RegisterActivity.this, "Phonenumber must start with +62", Toast.LENGTH_SHORT).show();
            return;
        }

        if(strPhonenumber.length() <12){
            Toast.makeText(RegisterActivity.this, "Phonenumber must longer equals than 12 digit", Toast.LENGTH_SHORT).show();
            return;
        }

        int selectedId = _rgGender.getCheckedRadioButtonId();
        if(selectedId == -1){
            Toast.makeText(RegisterActivity.this, "Please select your gender", Toast.LENGTH_SHORT).show();
            return;
        }else{
            _gender = (RadioButton) findViewById(selectedId);
            strGender = _gender.getText().toString();
        }

        User newUser = new User();
        newUser.setEmail(strEmail);
        newUser.setGender(strGender);
        newUser.setPassword(strPassword);
        newUser.setPhoneNumber(strPhonenumber);
        newUser.setUsername(strUsername);
        userHelper.insertDataUser(newUser);

        Toast.makeText(RegisterActivity.this, "Register Success", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
        startActivity(intent);
        finish();

    }

    private static boolean checkString(String str) {
        char ch;
        boolean capitalFlag = false;
        boolean lowerCaseFlag = false;
        boolean numberFlag = false;
        boolean specialFlag = false;
        for(int i=0;i < str.length();i++) {
            ch = str.charAt(i);
            if( Character.isDigit(ch)) {
                numberFlag = true;
            }else if(Character.isLetter(ch)){
                if (Character.isUpperCase(ch)) {
                    capitalFlag = true;
                } else if (Character.isLowerCase(ch)) {
                    lowerCaseFlag = true;
                }
            }else{
                specialFlag = true;
            }
            if(numberFlag && capitalFlag && lowerCaseFlag && specialFlag)
                return true;
        }
        return false;
    }
}