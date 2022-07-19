package com.example.graduation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class basket extends AppCompatActivity {

    RecyclerView recyclerView;
    basket_adapter myAdapter;
    ArrayList<basket_user> list;
    String id,num,blood,hos,name,phone,payment;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_basket);

        id = getIntent().getStringExtra("id");
        //num = getIntent().getStringExtra("num");
        //blood = getIntent().getStringExtra("blood");
        //hos = getIntent().getStringExtra("hos_name");
        //name = getIntent().getStringExtra("name");
        phone = getIntent().getStringExtra("phone");
        //payment = getIntent().getStringExtra("payment");

        recyclerView = findViewById(R.id.MyRecyclerView);

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();

        myAdapter = new basket_adapter(this,list,id);

        recyclerView.setAdapter(myAdapter);

        databaseReference = FirebaseDatabase.getInstance().getReference(id);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    basket_user user = dataSnapshot.getValue(com.example.graduation.basket_user.class);
                    list.add(user);
                }
                myAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}