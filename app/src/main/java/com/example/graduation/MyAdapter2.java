package com.example.graduation;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.MyViewHolder2>{

    Context context;
    ArrayList<hospital> list;
    String blood;

    public MyAdapter2(Context context, ArrayList<hospital> list, String blood) {
        this.context = context;
        this.list = list;
        this.blood = blood;
    }

    @NonNull
    @Override
    public MyAdapter2.MyViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.doctor_item,parent,false);

        return new MyAdapter2.MyViewHolder2(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter2.MyViewHolder2 holder, int position) {

        hospital Hospital = list.get(position);
        holder.doctor_name.setText(Hospital.getHospital_name().substring(0, 1).toUpperCase() + Hospital.getHospital_name().substring(1));
        if(holder.doctor_name.getText().toString().equals("Demerdash Hospital")
        || holder.doctor_name.getText().toString().equals("The main blood-Bank of Ain Shams")
        || holder.doctor_name.getText().toString().equals("Nile Blood Bank")
        || holder.doctor_name.getText().toString().equals("Egyptian Red Crescent-ERC"))
        {
            holder.payment.setText("Free");
        }
        else
        {
            holder.payment.setText(" ");
        }
        holder.b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,Booking.class);
                intent.putExtra("blood",blood);
                intent.putExtra("hos_name", holder.doctor_name.getText().toString());
                intent.putExtra("payment", holder.payment.getText().toString());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder2 extends RecyclerView.ViewHolder{

        TextView doctor_name,payment;
        ImageView doctor_img;
        Button b;

        public MyViewHolder2(@NonNull View itemView) {
            super(itemView);

            doctor_name = itemView.findViewById(R.id.doctor_name);
            doctor_img = itemView.findViewById(R.id.doctor_img);
            payment = itemView.findViewById(R.id.payment);
            b = itemView.findViewById(R.id.book);
        }
    }
}
