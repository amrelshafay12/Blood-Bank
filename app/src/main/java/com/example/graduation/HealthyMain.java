package com.example.graduation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class HealthyMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_healthy_main);
    }

    public void blood_diseases(View view) {
        Intent intent = new Intent(HealthyMain.this,BloodDiseases.class);
        startActivity(intent);
    }

    public void keepBlood(View view) {
        Intent intent = new Intent(HealthyMain.this,keepBlood.class);
        startActivity(intent);
    }
}