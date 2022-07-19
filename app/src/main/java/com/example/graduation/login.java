package com.example.graduation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class login extends AppCompatActivity {

    EditText email,password;
    Button login;
    TextView register;
    FirebaseAuth firebaseAuth;
    String Email,Password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.Login);
        register = findViewById(R.id.Register);
        firebaseAuth = FirebaseAuth.getInstance();



        if(firebaseAuth.getCurrentUser() != null)
        {
            startActivity(new Intent(getApplicationContext(),com.example.graduation.DonorList.class));
            finish();
        }
    }

    public void register(View view) {
        Intent intent = new Intent(this, com.example.graduation.register.class);
        startActivity(intent);
        finish();
    }

    public void login(View view) {
        Email = email.getText().toString().trim();
        Password = password.getText().toString().trim();
        if(TextUtils.isEmpty(Email) || TextUtils.isEmpty(Password))
        {
            Snackbar.make(login , "Please Enter Data " , Snackbar.LENGTH_SHORT).show();
        }
        else
        {
            firebaseAuth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        Intent i = new Intent(com.example.graduation.login.this,tests.class);
                        startActivity(i);
                        finish();
                    }
                    else
                    {
                        Snackbar.make(login, "User name or Password is not correct ", Snackbar.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}