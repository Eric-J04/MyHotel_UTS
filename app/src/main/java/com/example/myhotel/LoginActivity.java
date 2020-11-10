package com.example.myhotel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myhotel.helper.SharedpreferanceHelper;
import com.example.myhotel.helper.UserHelper;
import com.example.myhotel.model.User;

public class LoginActivity extends AppCompatActivity {
    private EditText _email,_password;
    private UserHelper userHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();

        SharedpreferanceHelper sharedPref = new SharedpreferanceHelper(LoginActivity.this);
        User user = sharedPref.load();

        // if the user that loaded from shared pref is not null, then intent to HomeActivity
        if(!user.getUsername().equals("") && !user.getPassword().equals("")){
            // Move to Home Activity
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

    }

    private void init(){
        userHelper = new UserHelper(this);

        _email = findViewById(R.id.login_email);
        _password = findViewById(R.id.login_password);

        Button login = findViewById(R.id.login_btnlogin);
        Button register = findViewById(R.id.login_btnregister);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLogin();
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToRegisPage();
            }
        });
    }

    private void goToRegisPage() {
        //go to regispage
    }

    private void checkLogin() {

        String email = _email.getText().toString().trim();
        String password = _password.getText().toString().trim();

        Cursor cursor = userHelper.getAllDataUser();
        Boolean flagLogin = false;

        if(email.isEmpty() || password.isEmpty()){
            Toast.makeText(LoginActivity.this, "Please input email and password", Toast.LENGTH_SHORT).show();
        }

        if(cursor != null){
            while (cursor.moveToNext()){
                if(cursor.getString(cursor.getColumnIndex("user_email")).equals(email)
                        && cursor.getString(cursor.getColumnIndex("user_pass")).equals(password))
                {
                    flagLogin = true;
                }
            }
        }

        if(flagLogin){
            //to home page
        }else{
            Toast.makeText(LoginActivity.this, "Wrong password or email", Toast.LENGTH_SHORT).show();
        }
    }
}