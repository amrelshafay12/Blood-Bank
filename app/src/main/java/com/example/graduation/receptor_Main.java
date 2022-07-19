package com.example.graduation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class receptor_Main extends AppCompatActivity {

    Button am1,am2,am3,am4,am5,am6,am7,am8;
    FusedLocationProviderClient fusedLocationProviderClient;
    private final static int REQUST_CODE = 100;
    String blood;
    double lat,lan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_receptor_main);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        getLastLocation();
        am1 = findViewById(R.id.am1);
        am2 = findViewById(R.id.am2);
        am3 = findViewById(R.id.am3);
        am4 = findViewById(R.id.am4);
        am5 = findViewById(R.id.am5);
        am6 = findViewById(R.id.am6);
        am7 = findViewById(R.id.am7);
        am8 = findViewById(R.id.am8);
    }

    private void getLastLocation() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED)
        {
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if(location != null)
                    {
                        List<Address> addresses = null;
                        Geocoder geocoder = new Geocoder(receptor_Main.this, Locale.getDefault());
                        try
                        {
                            addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(),1);
                            lat = addresses.get(0).getLatitude();
                            lan = addresses.get(0).getLongitude();
                        }
                        catch (IOException e)
                        {
                            e.printStackTrace();
                        }

                    }
                }
            });
        }
        else
        {
            askPremesion();
        }
    }

    private void askPremesion() {
        ActivityCompat.requestPermissions(receptor_Main.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUST_CODE);
    }

    public void am1(View view) {
        blood = (String) am1.getText();
        Intent intent = new Intent(receptor_Main.this,NearsetHospital.class);
        intent.putExtra("blood",blood);
        intent.putExtra("lat",String.valueOf(lat));
        intent.putExtra("lan",String.valueOf(lan));
        startActivity(intent);
    }

    public void am2(View view) {
        blood = (String) am2.getText();
        Intent intent = new Intent(receptor_Main.this,NearsetHospital.class);
        intent.putExtra("blood",blood);
        intent.putExtra("lat",String.valueOf(lat));
        intent.putExtra("lan",String.valueOf(lan));
        startActivity(intent);
    }

    public void am3(View view) {
        blood = (String) am3.getText();
        Intent intent = new Intent(receptor_Main.this,NearsetHospital.class);
        intent.putExtra("blood",blood);
        intent.putExtra("lat",String.valueOf(lat));
        intent.putExtra("lan",String.valueOf(lan));
        startActivity(intent);
    }

    public void am4(View view) {
        blood = (String) am4.getText();
        Intent intent = new Intent(receptor_Main.this,NearsetHospital.class);
        intent.putExtra("blood",blood);
        intent.putExtra("lat",String.valueOf(lat));
        intent.putExtra("lan",String.valueOf(lan));
        startActivity(intent);
    }

    public void am5(View view) {
        blood = (String) am5.getText();
        Intent intent = new Intent(receptor_Main.this,NearsetHospital.class);
        intent.putExtra("blood",blood);
        intent.putExtra("lat",String.valueOf(lat));
        intent.putExtra("lan",String.valueOf(lan));
        startActivity(intent);
    }

    public void am6(View view) {
        blood = (String) am6.getText();
        Intent intent = new Intent(receptor_Main.this,NearsetHospital.class);
        intent.putExtra("blood",blood);
        intent.putExtra("lat",String.valueOf(lat));
        intent.putExtra("lan",String.valueOf(lan));
        startActivity(intent);
    }

    public void am7(View view) {
        blood = (String) am7.getText();
        Intent intent = new Intent(receptor_Main.this,NearsetHospital.class);
        intent.putExtra("blood",blood);
        intent.putExtra("lat",String.valueOf(lat));
        intent.putExtra("lan",String.valueOf(lan));
        startActivity(intent);
    }

    public void am8(View view) {
        blood = (String) am8.getText();
        Intent intent = new Intent(receptor_Main.this,NearsetHospital.class);
        intent.putExtra("blood",blood);
        intent.putExtra("lat",String.valueOf(lat));
        intent.putExtra("lan",String.valueOf(lan));
        startActivity(intent);
    }
}