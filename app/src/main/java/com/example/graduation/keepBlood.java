package com.example.graduation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class keepBlood extends AppCompatActivity {

    Button k1,k2,k3,k4;
    String keep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_keep_blood);

        k1 = findViewById(R.id.k1);
        k2 = findViewById(R.id.k2);
        k3 = findViewById(R.id.k3);
        k4 = findViewById(R.id.k4);

    }

    public void k1(View view) {
        keep = (String) k1.getText();
        Intent intent = new Intent(keepBlood.this,keepBloodDescription.class);
        intent.putExtra("keep",keep);
        startActivity(intent);
    }

    public void k2(View view) {
        keep = (String) k2.getText();
        Intent intent = new Intent(keepBlood.this,keepBloodDescription.class);
        intent.putExtra("keep",keep);
        startActivity(intent);
    }

    public void k3(View view) {
        keep = (String) k3.getText();
        Intent intent = new Intent(keepBlood.this,keepBloodDescription.class);
        intent.putExtra("keep",keep);
        startActivity(intent);
    }

    public void k4(View view) {
        keep = (String) k4.getText();
        Intent intent = new Intent(keepBlood.this,keepBloodDescription.class);
        intent.putExtra("keep",keep);
        startActivity(intent);
    }
}