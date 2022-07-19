package com.example.graduation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class choose_donor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_choose_donor);
    }

    public void donor(View view) {
        Intent intent = new Intent(this, DonorList.class);
        startActivity(intent);
    }

    public void hospital(View view) {
        Intent intent = new Intent(this, receptor_Main.class);
        startActivity(intent);
    }
}