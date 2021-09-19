package com.example.androidproject;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class UserInfo extends AppCompatActivity {
    TextInputLayout fullName, userName, email, phoneno, password;
    Button registerationButton, nextButton;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_info);
        fullName = findViewById(R.id.fullname);
        userName = findViewById(R.id.username2);
        email = findViewById(R.id.Email);
        phoneno = findViewById(R.id.phonenumber);
        password = findViewById(R.id.password2);
        registerationButton = findViewById(R.id.button4);
        nextButton = findViewById(R.id.button6);
        nextButton.setOnClickListener(v -> {
            Intent intent=new Intent(UserInfo.this,User_Profile.class);
            startActivity(intent);
        });

        registerationButton.setOnClickListener(new View.OnClickListener() {
            private Boolean ValidateName() {
                String val = Objects.requireNonNull(fullName.getEditText()).getText().toString();
                if (val.trim().isEmpty()) {
                    fullName.setError("Field cannot be Empty");
                    return false;
                } else {
                    fullName.setError(null);
                    fullName.setErrorEnabled(false);
                    return true;
                }

            }

            private Boolean ValidateUserName() {
                String val = Objects.requireNonNull(userName.getEditText()).getText().toString();
                String noWhiteSpaces = "\\A\\w{4,20}\\z";
                if (val.trim().isEmpty()) {
                    userName.setError("Field cannot be Empty");
                    return false;
                } else if (val.length() >= 15) {
                    userName.setError("UserName too long");
                    return false;
                } else if (!val.matches(noWhiteSpaces)) {
                    userName.setError("White Spaces are not allowed");
                    return false;
                } else {
                    userName.setError(null);
                    userName.setErrorEnabled(false);
                    return true;
                }

            }

            private Boolean ValidateEmail() {
                String val = Objects.requireNonNull(email.getEditText()).getText().toString();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                if (val.trim().isEmpty()) {
                    email.setError("Field cannot be Empty");
                    return false;
                } else if (!val.matches(emailPattern)) {
                    email.setError("Invalid Email address");
                    return false;
                } else {
                    email.setError(null);
                    email.setErrorEnabled(false);
                    return true;
                }

            }

            private Boolean ValidatePhoneno() {
                String val = Objects.requireNonNull(phoneno.getEditText()).getText().toString();
                if (val.trim().isEmpty()) {
                    phoneno.setError("Field cannot be Empty");
                    return false;
                } else {
                    phoneno.setError(null);
                    return true;
                }

            }

            private Boolean ValidatePassword() {
                String val = Objects.requireNonNull(password.getEditText()).getText().toString();
                String passwordVal = "^" +
                        //"(?=.*[0-9])" +         //at least 1 digit
                        //"(?=.*[a-z])" +         //at least 1 lower case letter
                        //"(?=.*[A-Z])" +         //at least 1 upper case letter
                        "(?=.*[a-zA-Z])" +      //any letter
                        "(?=.*[@#$%^&+=])" +    //at least 1 special character
                        "(?=\\S+$)" +           //no white spaces
                        ".{4,}" +               //at least 4 characters
                        "$";
                if (val.trim().isEmpty()) {
                    password.setError("Field cannot be Empty");
                    return false;
                } else if (!val.matches(passwordVal)) {
                    password.setError("password is too weak");
                    return false;
                } else {
                    password.setError(null);
                    return true;
                }

            }

            @Override
            public void onClick(View v) {
                if (!ValidateName() | !ValidatePassword() | !ValidatePhoneno() | !ValidateEmail() | !ValidateUserName()) {
                    return;
                }
                firebaseDatabase = FirebaseDatabase.getInstance();
                databaseReference = firebaseDatabase.getReference("users");
                String Name = Objects.requireNonNull(fullName.getEditText()).getText().toString();
                String UserName = Objects.requireNonNull(userName.getEditText()).getText().toString();
                String Email = Objects.requireNonNull(email.getEditText()).getText().toString();
                String Phoneno = Objects.requireNonNull(phoneno.getEditText()).getText().toString();
                String Password = Objects.requireNonNull(password.getEditText()).getText().toString();


                //storing data in firebase
                users Users = new users(Name, UserName, Email, Phoneno, Password);
                databaseReference.child(UserName).setValue(Users, "hi");
                Toast.makeText(UserInfo.this, "Your Profile has been created", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(UserInfo.this,User_Profile.class);
                i.putExtra("email",Email);
                i.putExtra("userName",UserName);
                i.putExtra("phoneno",Phoneno);
                i.putExtra("name",Name);
                i.putExtra("password",Password);
                startActivity(i);

            }

        });

    }


    public void registerButton(View view) {

    }


}
