package com.example.graduation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.net.ssl.SSLEngineResult;


public class anotherLocation extends FragmentActivity implements OnMapReadyCallback {

    private String api_key = "AIzaSyCtmANgz_4PDr0bJlyfrx6CuBefPcJIlpU";
    GoogleMap map;
    String email,password,phone,name,gender;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    double lat,lan;
    String countryName,adress,city;
    SearchView searchView;
    Marker mm;
    Button createAccont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_another_location);

        createAccont = findViewById(R.id.CreateAccount);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child(FirebaseAuth.getInstance().getUid());

        searchView = findViewById(R.id.sv_location);


        email = getIntent().getStringExtra("email");
        password = getIntent().getStringExtra("password");
        phone = getIntent().getStringExtra("phone");
        name = getIntent().getStringExtra("name");
        gender = getIntent().getStringExtra("gender");

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                String location = searchView.getQuery().toString();
                List<Address> addressList = null;
                if(location != null)
                {
                    Geocoder geocoder = new Geocoder(anotherLocation.this);
                    try{
                        addressList = geocoder.getFromLocationName(location,1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if(mm != null)
                    {
                        mm.remove();
                    }
                    Address address = addressList.get(0);
                    LatLng latLng = new LatLng(address.getLatitude(),address.getLongitude());
                    lat = address.getLatitude();
                    lan = address.getLongitude();
                    city = address.getLocality();
                    countryName = address.getCountryName();
                    adress = address.getAddressLine(0);
                    mm = map.addMarker(new MarkerOptions().position(latLng));
                    map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;
        String address="";

        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(@NonNull LatLng latLng) {
                if(mm != null)
                {
                    mm.remove();
                }
                mm = map.addMarker(new MarkerOptions().position(latLng));
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));
                Geocoder geocoder = new Geocoder(anotherLocation.this, Locale.getDefault());
                try{
                    List<Address> addresses = geocoder.getFromLocation(latLng.latitude,latLng.longitude,1);
                    if(address != null)
                    {
                        Address address1 = addresses.get(0);
                        lat = address1.getLatitude();
                        lan = address1.getLongitude();
                        city = address1.getLocality();
                        countryName = address1.getCountryName();
                        adress = address1.getAddressLine(0);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void location(View view) {
        if(mm != null)
        {
            Intent intent = new Intent(anotherLocation.this,DonorBlood.class);

            intent.putExtra("Country", countryName);
            intent.putExtra("Latitude", Double.toString(lat));
            intent.putExtra("Longitude", Double.toString(lan));
            intent.putExtra("Address", adress);
            intent.putExtra("City", city);
            intent.putExtra("email",email);
            intent.putExtra("password",password);
            intent.putExtra("phone",phone);
            intent.putExtra("name",name);
            intent.putExtra("gender",gender);

            startActivity(intent);
            finish();
        }
        else
        {
            Snackbar.make(createAccont , "Please Select Location" , Snackbar.LENGTH_SHORT).show();
        }

    }
}