package com.example.graduation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class NearsetHospital extends AppCompatActivity {

    ArrayList<hospital> myArrayList = new ArrayList<>();
    DatabaseReference databaseReference;
    String blood;
    double lat,lan;

    RecyclerView recyclerView;
    MyAdapter2 myAdapter2;
    ArrayList<hospital> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_nearset_hospital);

        recyclerView = findViewById(R.id.MyRecyclerView);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();

        blood = getIntent().getStringExtra("blood");

        myAdapter2 = new MyAdapter2(this,myArrayList,blood);

        recyclerView.setAdapter(myAdapter2);


        lat = Double.parseDouble(getIntent().getStringExtra("lat"));
        lan = Double.parseDouble(getIntent().getStringExtra("lan"));


        List<String> bloodtype = new ArrayList<>();
        bloodtype.add("Swiss Hospital");
        bloodtype.add("Taiba Hospital");
        bloodtype.add("Nile Blood Bank");
        bloodtype.add("Mansheyet El Bakry General Hospital");
        bloodtype.add("Cairo Specialist Hospital");
        bloodtype.add("Coptic Hospital");
        bloodtype.add("Demerdash Hospital");
        bloodtype.add("Italian Hospital Umberto Primo");
        bloodtype.add("Misr International Hospital");
        bloodtype.add("Al-Zawiya Hospital");
        bloodtype.add("Al-Shorouk Hospital");
        bloodtype.add("Al-Salam International Hospital");
        bloodtype.add("Al-Amal Hospital");
        bloodtype.add("Regional Center for Blood Transfusion Bdaralsalam");
        bloodtype.add("The main blood-Bank of Ain Shams");
        bloodtype.add("Egyptian Red Crescent-ERC");


        List<Double> lann = new ArrayList<>();
        lann.add(-100.3701991);
        lann.add(48.0813217);
        lann.add(32.5298397);
        lann.add(31.306084400000003);
        lann.add(31.317630700000002);
        lann.add(36.7976586);
        lann.add(31.276054300000002);
        lann.add(12.514377500000002);
        lann.add(31.2161726);
        lann.add(12.7426291);
        lann.add(31.1996708);
        lann.add(48.008099099999995);
        lann.add(50.4940079);
        lann.add(31.228289);
        lann.add(31.2753074);
        lann.add(32.8986555);


        List<Double> latt = new ArrayList<>();
        latt.add(25.672516299999998);
        latt.add(29.250795799999995);
        latt.add(15.5971339);
        latt.add(30.0911932);
        latt.add(30.0943786);
        latt.add(-1.2978588);
        latt.add(30.073521299999996);
        latt.add(41.9058239);
        latt.add(30.0432562);
        latt.add(32.760856);
        latt.add(30.048003999999995);
        latt.add(29.369450200000003);
        latt.add(26.1448848);
        latt.add(30.0123419);
        latt.add(30.068113200000003);
        latt.add(24.0867781);


        HashMap<String, Double> dis = new HashMap<>();

        for (int i = 0; i < bloodtype.size(); i++)
        {
            double theta = lan - lann.get(i);
            double dist = Math.sin(deg2rad(lat))
                    * Math.sin(deg2rad(latt.get(i)))
                    + Math.cos(deg2rad(lat))
                    * Math.cos(deg2rad(latt.get(i)))
                    * Math.cos(deg2rad(theta));
            dist = Math.acos(dist);
            dist = rad2deg(dist);
            dist = dist * 60 * 1.1515;
            dis.put(bloodtype.get(i),dist);
        }

        HashMap map = sortByValues(dis);

        ArrayList<String> arr = new ArrayList<>();
        Set<String> keys = map.keySet();
        for(String key : keys) {
            databaseReference = FirebaseDatabase.getInstance().getReference().child("Blood").child(key);
            databaseReference.addChildEventListener(new ChildEventListener() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    String value = snapshot.getValue(String.class);
                    assert value != null;
                    String[] b = value.split("=");
                    hospital h = new hospital();
                    switch (blood) {
                        case "A+":
                            if (b[0].equals(blood) || b[0].equals("A-") || b[0].equals("O+") || b[0].equals("O-")) {
                                if (!b[1].equals("0")) {
                                    if(!arr.contains(key)) {
                                        arr.add(key);//+ "  " + b[0] + " " + b[1]);
                                        h.setHospital_name(key);
                                        myArrayList.add(h);
                                        myAdapter2.notifyDataSetChanged();
                                    }
                                }
                            }
                            break;
                        case "A-":
                        case "B-":
                        case "O+":
                            if (b[0].equals(blood) || b[0].equals("O-")) {
                                if (!b[1].equals("0")) {
                                    if(!arr.contains(key)) {
                                        arr.add(key);//+ "  " + b[0] + " " + b[1]);
                                        h.setHospital_name(key);
                                        myArrayList.add(h);
                                        myAdapter2.notifyDataSetChanged();
                                    }
                                }

                            }
                            break;
                        case "B+":
                            if (b[0].equals(blood) || b[0].equals("B-") || b[0].equals("O-") || b[0].equals("O+")) {
                                if (!b[1].equals("0")) {
                                    if(!arr.contains(key)) {
                                        arr.add(key);//+ "  " + b[0] + " " + b[1]);
                                        h.setHospital_name(key);
                                        myArrayList.add(h);
                                        myAdapter2.notifyDataSetChanged();
                                    }
                                }

                            }
                            break;
                        case "AB+":
                            if (b[0].equals(blood) || b[0].equals("B-") || b[0].equals("O-") || b[0].equals("O+")
                                    || b[0].equals("B+") || b[0].equals("A-") || b[0].equals("A+")
                                    || b[0].equals("AB-")) {
                                if (!b[1].equals("0")) {
                                    if(!arr.contains(key)) {
                                        arr.add(key);//+ "  " + b[0] + " " + b[1]);
                                        h.setHospital_name(key);
                                        myArrayList.add(h);
                                        myAdapter2.notifyDataSetChanged();
                                    }
                                }

                            }
                            break;
                        case "AB-":
                            if (b[0].equals(blood) || b[0].equals("B-") || b[0].equals("O-") || b[0].equals("A-")) {
                                if (!b[1].equals("0")) {
                                    if(!arr.contains(key)) {
                                        arr.add(key);//+ "  " + b[0] + " " + b[1]);
                                        h.setHospital_name(key);
                                        myArrayList.add(h);
                                        myAdapter2.notifyDataSetChanged();
                                    }
                                }

                            }
                            break;
                        case "O-":
                            if (b[0].equals(blood)) {
                                if (!b[1].equals("0")) {
                                    if(!arr.contains(key)) {
                                        arr.add(key);//+ "  " + b[0] + " " + b[1]);
                                        h.setHospital_name(key);
                                        myArrayList.add(h);
                                        myAdapter2.notifyDataSetChanged();
                                    }
                                }

                            }
                            break;
                    }

                }

                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    myAdapter2.notifyDataSetChanged();
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
    }


    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }

    private static HashMap sortByValues(HashMap map) {
        List list = new LinkedList(map.entrySet());
        // Defined Custom Comparator here
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry) (o1)).getValue())
                        .compareTo(((Map.Entry) (o2)).getValue());
            }
        });

        // Here I am copying the sorted list in HashMap
        // using LinkedHashMap to preserve the insertion order
        HashMap sortedHashMap = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext(); ) {
            Map.Entry entry = (Map.Entry) it.next();
            sortedHashMap.put(entry.getKey(), entry.getValue());
        }
        return sortedHashMap;
    }
}