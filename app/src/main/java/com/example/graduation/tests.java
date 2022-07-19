package com.example.graduation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class tests extends AppCompatActivity {

    Button upload;
    String gender,user,userID;
    CardView cardView;
    FirebaseUser users;
    DatabaseReference databaseReference,reference,reff;
    ListView listView;
    ArrayList<String> myArrayList = new ArrayList<>();
    String name,phone,bloodtype,lat,lan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_tests);
        upload = findViewById(R.id.CreateAccount);
        cardView = findViewById(R.id.cardView);
        listView = findViewById(R.id.list);
        users = FirebaseAuth.getInstance().getCurrentUser();
        reference =FirebaseDatabase.getInstance().getReference();
        userID = users.getUid();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                gender = snapshot.child(userID).child("gender").getValue(String.class);
                name = snapshot.child(userID).child("name").getValue(String.class);
                phone = snapshot.child(userID).child("phone").getValue(String.class);
                bloodtype = snapshot.child(userID).child("bloodType").getValue(String.class);
                lat = snapshot.child(userID).child("Latitude").getValue(String.class);
                lan = snapshot.child(userID).child("Longitude").getValue(String.class);
                ArrayAdapter<String> myArrayAdapter = new ArrayAdapter<String>(tests.this, android.R.layout.simple_list_item_1,myArrayList);

                listView.setAdapter(myArrayAdapter);
                if(gender.equals("Female"))
                {
                    user = "Female";
                }
                else if(gender.equals("Male"))
                {
                    user = "Male";
                }

                databaseReference = FirebaseDatabase.getInstance().getReference().child(user);
                databaseReference.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        String value = snapshot.getValue(String.class);
                        myArrayList.add(value);
                        myArrayAdapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                        myArrayAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        }

    public void upload(View view) {
        Intent intent = new Intent(tests.this, TestResult.class);
        intent.putExtra("Latitude", lat);
        intent.putExtra("Longitude", lan);
        intent.putExtra("phone",phone);
        intent.putExtra("name",name);
        intent.putExtra("bloodType",bloodtype);
        startActivity(intent);
        finish();
    }

    public void add(View view) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this)
                .setView(R.layout.finish_donor).setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Continue with delete operation
                        addDonor();
                        Intent intent = new Intent(tests.this, DonorList.class);
                        startActivity(intent);
                        finish();
                    }
                });
        dialog.show();
    }

    private void addDonor() {
        reff = FirebaseDatabase.getInstance().getReference().child("Users");

        Map<String, Object> user = new HashMap<>();

        user.put("Latitude", lat);
        user.put("Longitude", lan);
        user.put("phone",phone);
        user.put("name",name);
        user.put("bloodType",bloodtype);

        reff.child(FirebaseAuth.getInstance().getUid()).setValue(user);
    }
}