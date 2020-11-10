package com.example.myhotel;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.myhotel.adapter.HotelAdapter;
import com.example.myhotel.helper.SharedpreferanceHelper;
import com.example.myhotel.model.Hotel;
import com.example.myhotel.model.User;

import java.util.ArrayList;

public class ProfileFragment extends Fragment {
    private TextView _username,_useremail, _usergender, _userphonenumber;
    private Button _btnLogout;
    private SharedpreferanceHelper shredHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_hotel,
                container,false);

        _username = view.findViewById(R.id.name);
        _useremail = view.findViewById(R.id.email);
        _usergender = view.findViewById(R.id.gender);
        _userphonenumber = view.findViewById(R.id.phonenumber);


        User user = shredHelper.load();
        _username.setText("Name : "+user.getUsername());
        _useremail.setText("email : "+user.getEmail());
        _usergender.setText("gender : "+user.getGender());
        _userphonenumber.setText("phonenumber : "+user.getPhoneNumber());

        _btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shredHelper.logout(view.getContext());
            }
        });



        return view;
    }
}