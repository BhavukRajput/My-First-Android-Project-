package com.example.androidproject;

import androidx.activity.result.ActivityResult;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.hbb20.CountryCodePicker;

public class ChatApp extends AppCompatActivity {
    CountryCodePicker countryCodePicker;
    EditText phoneNumber,SMS;
    Button send,cancel,back;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_chat_app);
        back=findViewById(R.id.backFromSmsAppbutton);
        send=findViewById(R.id.sendButton);
        cancel=findViewById(R.id.cancelButton);
        phoneNumber=findViewById(R.id.txtMobile);
        SMS=findViewById(R.id.txtMessage);
        progressBar=findViewById(R.id.smsProgressbar);
        cancel=findViewById(R.id.cancelButton);
        countryCodePicker=findViewById(R.id.ccp2);

        ActivityCompat.requestPermissions(ChatApp.this,new String[]{Manifest.permission.SEND_SMS,
                Manifest.permission.READ_SMS}, PackageManager.PERMISSION_GRANTED);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = SMS.getText().toString();
                String phonenos = phoneNumber.getText().toString().trim();
                progressBar.setVisibility(View.VISIBLE);

                if (phonenos!=null){
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phonenos,null,msg,null,null);
                    progressBar.setVisibility(View.INVISIBLE);

                    Toast.makeText(ChatApp.this, "Message Sent Successfully.... ", Toast.LENGTH_SHORT).show();

                }else {
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(ChatApp.this, "Message sending failed.... ", Toast.LENGTH_SHORT).show();
                }

            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ChatApp.this, "Message Canceled....", Toast.LENGTH_SHORT).show();
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ChatApp.this,Home.class);
                startActivity(intent);
                finish();
            }
        });

    }
}