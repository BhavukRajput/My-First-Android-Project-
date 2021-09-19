package com.example.androidproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class EnterPhoneNumber extends AppCompatActivity {
    EditText enterNumber;
    Button getotpbutton;
    ProgressBar progressBar1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_enter_phone_number);
        enterNumber=findViewById(R.id.enter_mobile_number2);
        getotpbutton=findViewById(R.id.btn_getOTP);
        progressBar1=findViewById(R.id.progressbar_send_otp);


        getotpbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!enterNumber.getText().toString().trim().isEmpty()){
                    if ((enterNumber.getText().toString().trim()).length() == 10){

                        progressBar1.setVisibility(View.VISIBLE);
                        getotpbutton.setVisibility(View.INVISIBLE);
                        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                "+91" + enterNumber.getText().toString(),
                                60,
                                TimeUnit.SECONDS,
                                EnterPhoneNumber.this,
                                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                    @Override
                                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                        progressBar1.setVisibility(View.GONE);
                                        getotpbutton.setVisibility(View.VISIBLE);


                                    }

                                    @Override
                                    public void onVerificationFailed(@NonNull FirebaseException e) {
                                        progressBar1.setVisibility(View.GONE);
                                        getotpbutton.setVisibility(View.VISIBLE);
                                        Toast.makeText(EnterPhoneNumber.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                                    }

                                    @Override
                                    public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                        super.onCodeSent(s, forceResendingToken);
                                        progressBar1.setVisibility(View.GONE);
                                        getotpbutton.setVisibility(View.VISIBLE);
                                        Intent intent= new Intent(EnterPhoneNumber.this,VerifyOtp.class);
                                        intent.putExtra("mobile",enterNumber.getText().toString());
                                        intent.putExtra("s",s);
                                        startActivity(intent);
                                    }
                                }
                        );

                    }else {
                        Toast.makeText(EnterPhoneNumber.this, "Please enter correct number", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(EnterPhoneNumber.this, "Enter Phone Number", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}