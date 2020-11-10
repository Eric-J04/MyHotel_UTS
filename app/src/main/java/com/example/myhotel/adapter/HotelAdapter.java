package com.example.myhotel.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myhotel.R;
import com.example.myhotel.model.Hotel;

import java.util.ArrayList;

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.HotelViewHolder>{


    public interface OnItemClickListener {
        void onItemClick(Hotel hotel);
    }
    private final OnItemClickListener listener;
    private final ArrayList<Hotel> HotelList;

    public HotelAdapter(ArrayList<Hotel> hotelList, OnItemClickListener listener) {
        this.HotelList = hotelList;
        this.listener = listener;
    }

    public class HotelViewHolder extends RecyclerView.ViewHolder{
        public TextView title,desc,price;
        //        public TextView username;
//        public TextView description;
        public LinearLayout llnLayout;
        public HotelViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.hotel_items_title);
            desc = itemView.findViewById(R.id.hotel_items_desc);
            price = itemView.findViewById(R.id.hotel_items_price);
            llnLayout = itemView.findViewById(R.id.hotel_rl_layout);

        }
        public void bind(final Hotel item, final OnItemClickListener listener) {
            title.setText(item.getHotelName());
            desc.setText(item.getDesc());
            price.setText("Rp "+item.getPrice()+",00");
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }

    @NonNull
    @Override
    public HotelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hotel_item,parent,false);
        HotelViewHolder vViewHolder = new HotelViewHolder(view);
        return vViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HotelViewHolder holder, int position) {
        Hotel item = HotelList.get(position);
        holder.bind(item,listener);
    }

    @Override
    public int getItemCount() {
        return HotelList.size();
    }
}
