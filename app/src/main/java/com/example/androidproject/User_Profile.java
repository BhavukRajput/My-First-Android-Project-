package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.FirebaseDatabase;

public class User_Profile extends AppCompatActivity {
    TextInputEditText fullName,email,password,phoneno;
    TextView fullNameLabel,userNameLabel;
    Button enterTheApp,createProfile;
    FirebaseDatabase firebaseDatabase;
    String _username,_name,_password,_email,_phoneno;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_profile);
        fullName = findViewById(R.id.Full_Name_Profile);
        email = findViewById(R.id.Email_Profile);
        password = findViewById(R.id.Password_Profile);
        phoneno = findViewById(R.id.Phoneno_Profile);
        fullNameLabel = findViewById(R.id.full_name_label);
        userNameLabel = findViewById(R.id.userNameLabel);
        createProfile = findViewById(R.id.Update_Button);
        enterTheApp = findViewById(R.id.enterAppButton);
        firebaseDatabase = FirebaseDatabase.getInstance();
        createProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(User_Profile.this,UserInfo.class);
                startActivity(i);

            }
        });
        enterTheApp.setOnClickListener(v -> {
            Intent i=new Intent(User_Profile.this,Home.class);
            startActivity(i);

        });
        showAllUserData();
    }


    private void showAllUserData() {
        Intent intent=getIntent();
        _username=intent.getStringExtra("userName");
        _name=intent.getStringExtra("name");
        _password=intent.getStringExtra("password");
        _email=intent.getStringExtra("email");
        _phoneno=intent.getStringExtra("phoneno");

        fullNameLabel.setText(_username);
        userNameLabel.setText(_name);
        fullName.setText(_username);
        password.setText(_password);
        email.setText(_email);
        phoneno.setText(_phoneno);

    }




}
