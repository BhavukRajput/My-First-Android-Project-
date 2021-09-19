package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.telecom.Call;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.security.AllPermission;

public class Home extends AppCompatActivity {
    CardView UserProfile,MoreApp,CallApp,ChatApp,QuizApp,Calculator,BrowserApp,TextToSpeechApp;
    FloatingActionButton Bluetooth,Wifi,Torch;
    Button logoutBtn;
    private boolean bluetooth= false;
    BluetoothAdapter ba;
    WifiManager wm;
    private boolean wifi=false;
    private boolean camera=false;
    CameraManager cameraManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);
        UserProfile=findViewById(R.id.cardView);
        MoreApp=findViewById(R.id.cardView2);
        CallApp=findViewById(R.id.cardView3);
        ChatApp=findViewById(R.id.cardView4);
        QuizApp=findViewById(R.id.cardView5);
        Calculator=findViewById(R.id.cardView6);
        BrowserApp=findViewById(R.id.cardView7);
        logoutBtn=findViewById(R.id.logoutBtn1);
        TextToSpeechApp=findViewById(R.id.cardView8);
        Bluetooth=findViewById(R.id.floatingActionButton2);
        Wifi=findViewById(R.id.floatingActionButton3);
        Torch=findViewById(R.id.floatingActionButton);
        wm=(WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        ba=BluetoothAdapter.getDefaultAdapter();
        cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);


        UserProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Home.this,User_Profile.class);
                startActivity(intent);
            }
        });
        MoreApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Home.this, com.example.androidproject.MoreApp.class);
                startActivity(intent);
            }
        });
        CallApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Home.this, com.example.androidproject.CallApp.class);
                startActivity(intent);
            }
        });
        ChatApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Home.this, com.example.androidproject.ChatApp.class);
                startActivity(intent);
            }
        });
        QuizApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Home.this, com.example.androidproject.QuizApp.class);
                startActivity(intent);
            }
        });
        Calculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Home.this, Calculator.class);
                startActivity(intent);
            }
        });
        BrowserApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Home.this, com.example.androidproject.BrowserApp.class);
                startActivity(intent);
            }
        });
        TextToSpeechApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Home.this, SpeechApp.class);
                startActivity(intent);
            }
        });
        Bluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!bluetooth){
                    ba.enable();
                    bluetooth=true;
                    Bluetooth.setImageResource(R.drawable.ic_baseline_bluetooth_connected_24);
                }else{
                    ba.disable();
                    bluetooth=false;
                    Bluetooth.setImageResource(R.drawable.ic_baseline_bluetooth_disabled_24);

                }
            }
        });
        Wifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!wm.isWifiEnabled())
                {
                    Wifi.setImageResource(R.drawable.ic_baseline_wifi_24);
                    wm.setWifiEnabled(true);
                    wifi=true;
                } else if (wm.isWifiEnabled())
                    {
                        Wifi.setImageResource(R.drawable.ic_baseline_wifi_off_24);
                        wm.setWifiEnabled(false);
                        wifi=false;
                    }

            }
        });
        Torch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!camera){
                    try {
                        String s2 = cameraManager.getCameraIdList()[0];
                        cameraManager.setTorchMode(s2, true);
                        camera = true;

                    }catch (CameraAccessException e){}
                    Torch.setImageResource(R.drawable.ic_baseline_bolt_24);
                }else {
                    try {
                        String s2 = cameraManager.getCameraIdList()[0];
                        cameraManager.setTorchMode(s2, false);
                        camera = false;

                    }catch (CameraAccessException e){}
                    Torch.setImageResource(R.drawable.ic_baseline_offline_bolt_24);
                }

            }
        });
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent u = new Intent(Home.this,Login.class);
                startActivity(u);
                finish();
            }
        });

    }
 }

