package com.example.graduation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class TestResult extends AppCompatActivity {
    private OkHttpClient okHttpClient;
    EditText t1,t3,t4,t5;
    Button button;
    String gender;
    String pressure;
    String diabates;
    String weight;
    String name,phone,bloodtype,lat,lan;
    DatabaseReference reff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_test_result);

        t1 = findViewById(R.id.result2);
        t3 = findViewById(R.id.result6);
        t4 = findViewById(R.id.result7);
        t5 = findViewById(R.id.result8);
        lat = getIntent().getStringExtra("Latitude");
        lan = getIntent().getStringExtra("Longitude");
        phone = getIntent().getStringExtra("phone");
        name = getIntent().getStringExtra("name");
        bloodtype = getIntent().getStringExtra("bloodType");

        button = findViewById(R.id.done);






        okHttpClient = new OkHttpClient();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String dummyText = t1.getText().toString();
                String dummyText3 = t3.getText().toString();
                String dummyText4 = t4.getText().toString();
                String dummyText5 = t5.getText().toString();

                   RequestBody requestBody = new FormBody.Builder().add("gender",gender).add("age",dummyText)
                           .add("weight",weight).add("presuree",pressure).add("diabets",diabates)
                           .add("hemoglobin",dummyText3).add("hcv",dummyText4).add("hb",dummyText5).build();
                   Request request = new Request.Builder().url("http://192.168.1.8:5000/debug").post(requestBody).build();
                   okHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(
                            @NotNull Call call,
                            @NotNull IOException e) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(), "server down", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        if(response.body().string().equals("0"))
                        {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    AlertDialog.Builder dialog = new AlertDialog.Builder(TestResult.this)
                                            .setView(R.layout.nodonate).setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int which) {
                                                    FirebaseAuth.getInstance().signOut();
                                                    startActivity(new Intent(getApplicationContext() , MainActivity.class));
                                                    finish();
                                                }
                                            });
                                    dialog.show();
                                }
                            });

                        }

                        else
                        {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    AlertDialog.Builder dialog = new AlertDialog.Builder(TestResult.this)
                                            .setView(R.layout.donate).setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int which) {
                                                    reff = FirebaseDatabase.getInstance().getReference().child("Users");
                                                    Map<String, Object> user = new HashMap<>();
                                                    user.put("Latitude", lat);
                                                    user.put("Longitude", lan);
                                                    user.put("phone",phone);
                                                    user.put("name",name);
                                                    user.put("bloodType",bloodtype);
                                                    reff.child(FirebaseAuth.getInstance().getUid()).setValue(user);
                                                    FirebaseAuth.getInstance().signOut();
                                                    startActivity(new Intent(getApplicationContext() , DonorList.class));
                                                    finish();
                                                }
                                            });
                                    dialog.show();
                                }
                            });

                        }

                    }
                });
            }
        });










        final Spinner spinner = findViewById(R.id.result1);
        String[] Gender = new String[]{
                "Select Gender...",
                "Male",
                "Female"
        };
        final List<String> plantsList = new ArrayList<>(Arrays.asList(Gender));
        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this,R.layout.spinner_item,plantsList){
            @Override
            public boolean isEnabled(int position){
                return position != 0;
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerArrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);
                if(position > 0){
                    gender = selectedItemText;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        final Spinner spinner1 = findViewById(R.id.result4);
        String[] Blood_Pressure = new String[]{
                "Have Pressure...",
                "Yes",
                "No"
        };
        final List<String> pressure_list = new ArrayList<>(Arrays.asList(Blood_Pressure));
        final ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(
                this,R.layout.spinner_item,pressure_list){
            @Override
            public boolean isEnabled(int position){
                return position != 0;
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(spinnerAdapter);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);
                if(position > 0){
                    pressure = selectedItemText;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        final Spinner spinner2 = findViewById(R.id.result5);
        String[] Blood_diabetes = new String[]{
                "Have Diabetes...",
                "Yes",
                "No"
        };
        final List<String> diabetes_list = new ArrayList<>(Arrays.asList(Blood_diabetes));
        final ArrayAdapter<String> spinnerdapter = new ArrayAdapter<String>(
                this,R.layout.spinner_item,diabetes_list){
            @Override
            public boolean isEnabled(int position){
                return position != 0;
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        spinnerdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(spinnerdapter);

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);
                if(position > 0){
                    diabates = selectedItemText;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });



        final Spinner spinner3 = findViewById(R.id.result3);
        String[] Weight = new String[]{
                "Weight...",
                "<50",
                ">=50"
        };
        final List<String> weight_list = new ArrayList<>(Arrays.asList(Weight));
        final ArrayAdapter<String> spinnerdapter2 = new ArrayAdapter<String>(
                this,R.layout.spinner_item,weight_list){
            @Override
            public boolean isEnabled(int position){
                return position != 0;
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        spinnerdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(spinnerdapter2);

        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);
                if(position > 0){
                    weight = selectedItemText;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }


}