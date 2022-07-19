package com.example.graduation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.InputType;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class donorLocation extends AppCompatActivity {

    String email,password,phone,name,gender;
    TextView location,Ask;
    Button MarkLocation,MarkMyLocation;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FusedLocationProviderClient fusedLocationProviderClient;
    private final static int REQUST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_donor_location);

        location = findViewById(R.id.location);
        Ask = findViewById(R.id.Ask);
        MarkLocation = findViewById(R.id.MarkLocation);
        MarkMyLocation = findViewById(R.id.MarkMyLocation);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child(FirebaseAuth.getInstance().getUid());


        email = getIntent().getStringExtra("email");
        password = getIntent().getStringExtra("password");
        phone = getIntent().getStringExtra("phone");
        name = getIntent().getStringExtra("name");
        gender = getIntent().getStringExtra("gender");

    }

    public void ask(View view) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this)
                .setView(R.layout.why_loc_dialog);
        dialog.show();
    }

    public void currentLocation(View view) {
        getLastLocation();
    }

    private void getLastLocation() {
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED)
        {
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if(location != null)
                    {
                        List<Address> addresses = null;
                        Map<String, Object> user = new HashMap<>();
                        Geocoder geocoder = new Geocoder(donorLocation.this, Locale.getDefault());
                        try
                        {
                             addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(),1);

                            Intent intent = new Intent(donorLocation.this,DonorBlood.class);

                            intent.putExtra("Country", addresses.get(0).getCountryName());
                            intent.putExtra("Latitude", Double.toString(addresses.get(0).getLatitude()));
                            intent.putExtra("Longitude", Double.toString(addresses.get(0).getLongitude()));
                            intent.putExtra("Address", addresses.get(0).getAddressLine(0));
                            intent.putExtra("City", addresses.get(0).getLocality());
                            intent.putExtra("email",email);
                            intent.putExtra("password",password);
                            intent.putExtra("phone",phone);
                            intent.putExtra("name",name);
                            intent.putExtra("gender",gender);

                            startActivity(intent);
                            finish();
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
        ActivityCompat.requestPermissions(donorLocation.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == REQUST_CODE)
        {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                getLastLocation();
            }
            else
            {
                Toast.makeText(this,"Please Provide the required premission",Toast.LENGTH_SHORT).show();
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void anotherLocation(View view) {
        Intent intent = new Intent(donorLocation.this,anotherLocation.class);
        intent.putExtra("email",email);
        intent.putExtra("password",password);
        intent.putExtra("phone",phone);
        intent.putExtra("name",name);
        intent.putExtra("gender",gender);
        startActivity(intent);
        finish();
    }
}