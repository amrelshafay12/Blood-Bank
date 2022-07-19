package com.example.graduation;

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
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Handler;

public class MainActivity extends AppCompatActivity {

    TextView t1,t2,t3;
    double lat,lan;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        t1 = findViewById(R.id.Medical_follow_up1);
        t2 = findViewById(R.id.Medical_follow_up2);
        t3 = findViewById(R.id.Medical_follow_up22);
        //calculateHospitalLatLan();
    }




   /** private void calculateHospitalLatLan() {

        reference = FirebaseDatabase.getInstance().getReference().child("Hospitals").child("Egyptian Red Crescent-ERC");

        Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
        try {
            List addressList = geocoder.getFromLocationName("Egyptian Red Crescent-ERC", 1);
            if (addressList != null && addressList.size() > 0) {
                Address address = (Address) addressList.get(0);
                lat = address.getLatitude();
                lan = address.getLongitude();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String, Object> user = new HashMap<>();
        user.put("lat",lat);
        user.put("lan",lan);
        reference.setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {

            }
        });
    }**/

    public void donor(View view) {
        Intent intent = new Intent(this,login.class);
        startActivity(intent);
    }

    public void receptor(View view) {
        Intent intent = new Intent(MainActivity.this,receptor_login.class);
        startActivity(intent);
    }

    public void toBeHealthy(View view) {
        Intent intent = new Intent(MainActivity.this,HealthyMain.class);
        startActivity(intent);
    }

}