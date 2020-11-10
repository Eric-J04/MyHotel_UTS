package com.example.myhotel;

import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myhotel.adapter.HotelAdapter;
import com.example.myhotel.helper.HotelHelper;
import com.example.myhotel.model.Hotel;

import java.util.ArrayList;


public class HotelFragment extends Fragment {

    private RecyclerView recyclerView;
    private LinearLayoutManager llnLayout;
    private ArrayList<Hotel> HotelList;
    private HotelHelper hotelHelper;
    private HotelAdapter _HotelAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_hotel,
                container,false);


        recyclerView = view.findViewById(R.id.hotel_recycle_view);
        llnLayout= new LinearLayoutManager(this.getActivity().getBaseContext());
        recyclerView.setLayoutManager(llnLayout);
        HotelList = new ArrayList<>();

        //dummy data
        Cursor cursor = hotelHelper.getAllDataHotels();
        if(cursor != null){
            while (cursor.moveToNext()){
                Hotel temp = new Hotel();
                temp.setHotelId(cursor.getString(cursor.getColumnIndex("hotel_id")));
                temp.setDesc(cursor.getString(cursor.getColumnIndex("hotel_desc")));
                temp.setHotelLocation(cursor.getString(cursor.getColumnIndex("hotel_location")));
                temp.setHotelName(cursor.getString(cursor.getColumnIndex("hotel_name")));
                temp.setPrice(cursor.getInt(cursor.getColumnIndex("hotel_price")));

                HotelList.add(temp);
            }
        }

        setAdapter();
        return view;

    }
    public void setAdapter(){
        _HotelAdapter = new HotelAdapter(HotelList, new HotelAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Hotel hotel) {
                StringBuffer message = new StringBuffer();
                message.append("Desc : "+hotel.getDesc());
                message.append("Price : Rp"+hotel.getPrice()+",00");
                message.append("Location : "+hotel.getHotelLocation());

                messageDialog(hotel,message);
            }
        });
        recyclerView.setAdapter(_HotelAdapter);
    }

    private void messageDialog(Hotel hotel, StringBuffer message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity().getBaseContext());
        builder.setCancelable(true);
        builder.setTitle(hotel.getHotelName());
        builder.setMessage(message);
        builder.show();
    }
}