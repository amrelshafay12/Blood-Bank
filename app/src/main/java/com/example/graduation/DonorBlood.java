package com.example.graduation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class DonorBlood extends AppCompatActivity {

    String email,password,phone,name,gender,Country,Latitude,Longitude,Address,City,bloodType;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Button bt1,bt2,bt3,bt4,bt5,bt6,bt7,bt8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_donor_blood);

        bt1 = findViewById(R.id.bt1);
        bt2 = findViewById(R.id.bt2);
        bt3 = findViewById(R.id.bt3);
        bt4 = findViewById(R.id.bt4);
        bt5 = findViewById(R.id.bt5);
        bt6 = findViewById(R.id.bt6);
        bt7 = findViewById(R.id.bt7);
        bt8 = findViewById(R.id.bt8);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child(FirebaseAuth.getInstance().getUid());


        email = getIntent().getStringExtra("email");
        password = getIntent().getStringExtra("password");
        phone = getIntent().getStringExtra("phone");
        name = getIntent().getStringExtra("name");
        gender = getIntent().getStringExtra("gender");
        Country = getIntent().getStringExtra("Country");
        Latitude = getIntent().getStringExtra("Latitude");
        Longitude = getIntent().getStringExtra("Longitude");
        Address = getIntent().getStringExtra("Address");
        City = getIntent().getStringExtra("City");

    }

    public void bt1(View view) {
        bloodType = bt1.getText().toString();
        uploadData(bloodType);
    }

    private void uploadData(String bloodType) {
        Map<String, Object> user = new HashMap<>();

        user.put("Country", Country);
        user.put("Latitude", Latitude);
        user.put("Longitude", Longitude);
        user.put("Address", Address);
        user.put("City", City);
        user.put("email",email);
        user.put("password",password);
        user.put("phone",phone);
        user.put("name",name);
        user.put("gender",gender);
        user.put("bloodType",bloodType);

        databaseReference.setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Intent intent = new Intent(DonorBlood.this,tests.class);
                startActivity(intent);
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

    public void bt2(View view) {
        bloodType = bt2.getText().toString();
        uploadData(bloodType);
    }

    public void bt3(View view) {
        bloodType = bt3.getText().toString();
        uploadData(bloodType);
    }

    public void bt4(View view) {
        bloodType = bt4.getText().toString();
        uploadData(bloodType);
    }

    public void bt5(View view) {
        bloodType = bt5.getText().toString();
        uploadData(bloodType);
    }

    public void b6(View view) {
        bloodType = bt6.getText().toString();
        uploadData(bloodType);
    }

    public void bt7(View view) {
        bloodType = bt7.getText().toString();
        uploadData(bloodType);
    }

    public void bt8(View view) {
        bloodType = bt8.getText().toString();
        uploadData(bloodType);
    }
}