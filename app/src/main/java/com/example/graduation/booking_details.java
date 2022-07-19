package com.example.graduation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class booking_details extends AppCompatActivity {

    String id,num,blood,hos,name,phone,payment,time;
    TextView t1,t2,t3,t4,t5,t6,t7;
    DatabaseReference databaseReference,reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_booking_details);

        t1 = findViewById(R.id.n_delatiels);
        t2 = findViewById(R.id.q_deltails);
        t3 = findViewById(R.id.type_details);
        t4 = findViewById(R.id.phone_details);
        t5 = findViewById(R.id.hos_deitals);
        t6 = findViewById(R.id.payment_details);
        t7 = findViewById(R.id.time1);

        id = getIntent().getStringExtra("id");
        phone = getIntent().getStringExtra("phone");

        String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
        String[] separated = currentTime. split(":");
        int hour = Integer.parseInt(separated[0]);
        int minute = Integer.parseInt(separated[1]);

        databaseReference = FirebaseDatabase.getInstance().getReference(id);
        reference = databaseReference.child(phone);

        reference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                num = String.valueOf(task.getResult().child("num").getValue());
                blood = String.valueOf(task.getResult().child("blood").getValue());
                hos = String.valueOf(task.getResult().child("hos_name").getValue());
                name = String.valueOf(task.getResult().child("name").getValue());
                payment = String.valueOf(task.getResult().child("payment").getValue());
                time = String.valueOf(task.getResult().child("time").getValue());
                String[] separated_time = time. split(":");
                int hour1 = Integer.parseInt(separated_time[0]);
                int minute2 = Integer.parseInt(separated_time[1]);
                if(payment.equals("Free"))
                {
                    t6.setText("No payment");
                }
                else
                {
                    int c = 50;
                    int nu = Integer.parseInt(num);
                    int total = c * nu;
                    t6.setText(String.valueOf(total));
                }
                String final_hour = String.valueOf(1 - (Math.abs(hour1 - hour)));
                String final_minute = String.valueOf(60 - (Math.abs(minute2 - minute)));
                String timee = final_hour + ":" + final_minute;

                t1.setText(name);
                t2.setText(num);
                t3.setText(blood);
                t4.setText(phone);
                t5.setText(hos);
                t7.setText(timee);
            }
        });

    }

    public void dell(View view) {
        DatabaseReference dbref= FirebaseDatabase.getInstance().getReference().child(id);
        Query query=dbref.child(phone);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dataSnapshot.getRef().removeValue();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Intent intent = new Intent(booking_details.this,receptor_login.class);
        startActivity(intent);
        finish();
    }
}