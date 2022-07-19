package com.example.graduation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class basket_adapter extends RecyclerView.Adapter<basket_adapter.MyViewHolder> {

    Context context;
    ArrayList<basket_user> list;
    String id;

    public basket_adapter(Context context, ArrayList<basket_user> list, String id) {
        this.context = context;
        this.list = list;
        this.id = id;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.basket_item,parent,false);

        return new MyViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        basket_user basket_user = list.get(position);
        holder.name.setText(basket_user.getHos_name().substring(0, 1).toUpperCase() + basket_user.getHos_name().substring(1));
        holder.phone.setText(basket_user.getBlood());
        holder.bloodType.setText(basket_user.getNum());
        holder.phone1.setText(basket_user.getPhone());
        holder.b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,booking_details.class);
                intent.putExtra("phone",holder.phone1.getText());
                intent.putExtra("id",id);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name,bloodType,phone,phone1;
        Button b;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name1);
            phone = itemView.findViewById(R.id.type1);
            bloodType = itemView.findViewById(R.id.q1);
            phone1 = itemView.findViewById(R.id.phone1);
            b = itemView.findViewById(R.id.detail);
        }
    }

}
