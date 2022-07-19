package com.example.graduation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

public class Booking extends AppCompatActivity {

    EditText t1,t2;
    String name,phone;
    Button booking,okay,cancle;
    Dialog dialog;
    ArrayList<Integer> l = new ArrayList<>();
    Integer random;
    TextView code;
    String number,blood,hos,payment;
    DatabaseReference reff,databaseReference;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_booking);

        Spinner spinner = findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(this,R.array.numbers, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);
                if(position > 0){
                    number = selectedItemText;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        t1 = findViewById(R.id.receptor_Name);
        t2 = findViewById(R.id.receptor_Phone);
        booking = findViewById(R.id.bookk);
        blood = getIntent().getStringExtra("blood");
        hos = getIntent().getStringExtra("hos_name");
        payment = getIntent().getStringExtra("payment");



    }

    @SuppressLint("SetTextI18n")
    public void booking(View view) {
        name = t1.getText().toString().trim();
        phone = t2.getText().toString().trim();


        if(TextUtils.isEmpty(name) || TextUtils.isEmpty(phone))
        {
            Snackbar.make(booking , "Please Enter Data " , Snackbar.LENGTH_SHORT).show();
        }

        else
        {
            dialog = new Dialog(Booking.this);
            dialog.setContentView(R.layout.done_booking);
            dialog.show();
            okay = dialog.findViewById(R.id.btn_okay);
            cancle = dialog.findViewById(R.id.btn_cancel);
            code = dialog.findViewById(R.id.code);
            int k = 0;
            if(Objects.equals(l.size(), 0)) {
                random = new Random().nextInt((1000) + 1);
            }
            else
            {
                for (int i = 0; i < l.size(); i++) {
                    if (Objects.equals(random, l.get(i))) {
                        random = new Random().nextInt((1000) + 1);
                        k = 1;
                    }
                }
                if (k == 0) {
                    random = new Random().nextInt((1000) + 1);
                }
            }
            l.add(random);

            code.setText("Save this Code " + random + " to show your order details");

            okay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    reff = FirebaseDatabase.getInstance().getReference().child(String.valueOf(random));
                    Map<String, Object> user = new HashMap<>();
                    user.put("num", number);
                    user.put("blood", blood);
                    user.put("hos_name",hos);
                    user.put("name",name);
                    user.put("phone",phone);
                    user.put("payment",payment);
                    String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
                    user.put("time",currentTime);
                    reff.child(phone).setValue(user);

                    Intent intent = new Intent(Booking.this,basket.class);
                    intent.putExtra("id",String.valueOf(random));
                    intent.putExtra("phone",phone);
                    startActivity(intent);
                    finish();
                }
            });
            cancle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
        }
    }
}