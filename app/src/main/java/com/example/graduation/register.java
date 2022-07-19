package com.example.graduation;

import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

public class register extends AppCompatActivity {

    EditText userName,Password,Email;
    RadioGroup radioGroup;
    RadioButton male,female,radioButton;
    TextView textLogin;
    String Name,password,email;
    Button createAccount;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);
        userName = findViewById(R.id.Username);
        Password = findViewById(R.id.Password);
        Email = findViewById(R.id.email);
        radioGroup = findViewById(R.id.radioGroup);
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);
        textLogin = findViewById(R.id.Login);
        createAccount = findViewById(R.id.CreateAccount);
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void CheckButton(View view)
    {
        int radioID = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioID);
    }

    public void Login(View view)
    {
        Intent intent = new Intent(this,login.class);
        startActivity(intent);
        finish();
    }

    public void registeration(View view)
    {
        Name = userName.getText().toString().trim();
        password = Password.getText().toString().trim();
        email = Email.getText().toString().trim();

        if(TextUtils.isEmpty(Name) || TextUtils.isEmpty(password) || TextUtils.isEmpty(email))
        {
            Snackbar.make(createAccount , "Please enter Data" , Snackbar.LENGTH_SHORT).show();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches() || TextUtils.isEmpty(email) || !validEmail(email)) {
            Snackbar.make(createAccount , "Please enter correct email" , Snackbar.LENGTH_SHORT).show();
            return;
        }
        if (password.length() < 6) {
            Snackbar.make(createAccount , "Password can not be less than 6 character" , Snackbar.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(radioButton.getText()))
        {
            Snackbar.make(createAccount , "Please Choose your gender" , Snackbar.LENGTH_SHORT).show();
        }
        else
        {
            ShowDialog(this);
            firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
                if(task.isSuccessful())
                {
                    Intent intent = new Intent(getApplicationContext(),VerficationCode.class);
                    //Intent intent = new Intent(getApplicationContext(),donorLocation.class);
                    intent.putExtra("email",email);
                    intent.putExtra("password",password);
                    intent.putExtra("name",Name);
                    intent.putExtra("gender",radioButton.getText().toString().trim());
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Snackbar.make(createAccount , "Something error" , Snackbar.LENGTH_SHORT).show();
                }
            });
        }
    }


    public void ShowDialog(register context) {
        //setting up progress dialog
        progressDialog = new ProgressDialog(context);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
    }

    private boolean validEmail(String email) {
        return (email.contains("@"));
    }
}