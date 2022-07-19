package com.example.graduation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class BloodDiseases extends AppCompatActivity {

    Button b1,b2,b3,b4,b5,b6,b7,b8;
    String blood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_blood_diseases);

        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);
        b3 = findViewById(R.id.b3);
        b4 = findViewById(R.id.b4);
        b5 = findViewById(R.id.b5);
        b6 = findViewById(R.id.b6);
        b7 = findViewById(R.id.b7);
        b8 = findViewById(R.id.b8);

    }

    public void b1(View view) {
        blood = (String) b1.getText();
        Intent intent = new Intent(BloodDiseases.this,DieasesDiscription.class);
        intent.putExtra("blood",blood);
        startActivity(intent);
    }

    public void b2(View view) {
        blood = (String) b2.getText();
        Intent intent = new Intent(BloodDiseases.this,DieasesDiscription.class);
        intent.putExtra("blood",blood);
        startActivity(intent);
    }

    public void b3(View view) {
        blood = (String) b3.getText();
        Intent intent = new Intent(BloodDiseases.this,DieasesDiscription.class);
        intent.putExtra("blood",blood);
        startActivity(intent);
    }

    public void b4(View view) {
        blood = (String) b4.getText();
        Intent intent = new Intent(BloodDiseases.this,DieasesDiscription.class);
        intent.putExtra("blood",blood);
        startActivity(intent);
    }

    public void b5(View view) {
        blood = (String) b5.getText();
        Intent intent = new Intent(BloodDiseases.this,DieasesDiscription.class);
        intent.putExtra("blood",blood);
        startActivity(intent);
    }

    public void b6(View view) {
        blood = (String) b6.getText();
        Intent intent = new Intent(BloodDiseases.this,DieasesDiscription.class);
        intent.putExtra("blood",blood);
        startActivity(intent);
    }

    public void b7(View view) {
        blood = (String) b7.getText();
        Intent intent = new Intent(BloodDiseases.this,DieasesDiscription.class);
        intent.putExtra("blood",blood);
        startActivity(intent);
    }

    public void b8(View view) {
        blood = (String) b8.getText();
        Intent intent = new Intent(BloodDiseases.this,DieasesDiscription.class);
        intent.putExtra("blood",blood);
        startActivity(intent);
    }
}