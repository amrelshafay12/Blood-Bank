package com.example.graduation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

public class receptor_code extends AppCompatActivity {


    EditText t1;
    String code;
    private Object receptor_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_receptor_code);

        t1 = findViewById(R.id.code_);
    }

    public void login(View view) {
        code = t1.getText().toString().trim();
        if(TextUtils.isEmpty(code))
        {
            Snackbar.make((View) receptor_code, "Please Enter Data " , Snackbar.LENGTH_SHORT).show();
        }
        else
        {
            Intent intent = new Intent(receptor_code.this,basket.class);
            intent.putExtra("id",code);
            //intent.putExtra("num","8");
            //intent.putExtra("blood","A+");
            //intent.putExtra("hos_name","Taiba Hospital");
            //intent.putExtra("name","Fatma Essam");
            //intent.putExtra("phone","01124323049");
            //intent.putExtra("payment","");
            startActivity(intent);
            finish();

        }
    }
}