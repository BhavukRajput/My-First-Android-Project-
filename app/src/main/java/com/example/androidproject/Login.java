package com.example.androidproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.jetbrains.annotations.NotNull;

public class Login extends AppCompatActivity {
    TextView createNewAccount, logoText, orText;
    EditText Email, Password;
    ImageView btnGoogle,btnfacebook,OTPbtn;
    Button loginButton;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String passwordPattern = "^" +
            //"(?=.*[0-9])" +         //at least 1 digit
            //"(?=.*[a-z])" +         //at least 1 lower case letter
            //"(?=.*[A-Z])" +         //at least 1 upper case letter
            "(?=.*[a-zA-Z])" +      //any letter
            "(?=.*[@#$%^&+=])" +    //at least 1 special character
            "(?=\\S+$)" +           //no white spaces
            ".{4,}" +               //at least 4 characters
            "$";
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        createNewAccount = findViewById(R.id.Create_New_Account);
        logoText = findViewById(R.id.logo_text);
        orText = findViewById(R.id.Or);
        Email = findViewById(R.id.input_Email);
        btnGoogle=findViewById(R.id.imageView3);
        Password = findViewById(R.id.input_Password);
        btnfacebook=findViewById(R.id.imageView5);
        loginButton = findViewById(R.id.loginbtn);
        OTPbtn=findViewById(R.id.imageView4);
        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);
        firebaseUser=firebaseAuth.getCurrentUser();

        OTPbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k=new Intent(Login.this,EnterPhoneNumber.class);
                startActivity(k);
            }
        });
        btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Login.this,GoogleSighIn.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(i);
            }
        });
        btnfacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j=new Intent(Login.this,FacebookLogin.class);
                j.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(j);

            }
        });

        createNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, EmailRegisteration.class);
                Pair<View, String> p1 = Pair.create((View) createNewAccount, "Create_New_Account");
                Pair<View, String> p2 = Pair.create((View) logoText, "logo_text");
                Pair<View, String> p3 = Pair.create((View) orText, "Or");
                Pair<View, String> p4 = Pair.create((View) Email, "inputEmail");
                Pair<View, String> p5 = Pair.create((View) Password, "password");
                Pair<View, String> p7 = Pair.create((View) loginButton, "login_button");
                Pair<View, String> p8 = Pair.create((View) OTPbtn, "Otp_logo");
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        Login.this, p1, p2, p3, p4, p5, p7,p8);
                startActivity(i, options.toBundle());
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performLogin();
            }
        });
    }

    private void performLogin() {
        String email = Email.getText().toString();
        String password = Password.getText().toString();

        if (!email.matches(emailPattern)) {
            Email.setError("Enter correct email");

        } else if (password.isEmpty() || !password.matches(passwordPattern)) {
            Password.setError("Wrong Password");
        } else {
            progressDialog.setMessage("Please wait while Login....");
            progressDialog.setTitle("Login");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        progressDialog.dismiss();
                        sendUserToNextActivity();
                        Toast.makeText(Login.this, "Login successful", Toast.LENGTH_SHORT).show();
                    }else {
                        progressDialog.dismiss();
                        Toast.makeText(Login.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                    }



                }
            });
        }
    }
    private void sendUserToNextActivity() {
        Intent intent=new Intent(Login.this,Home.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}