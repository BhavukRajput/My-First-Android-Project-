package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class MoreApp extends AppCompatActivity {
    Button btn_cameraCapture,btn_videoRecorder,btn_backFromMoreApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_more_app);
        btn_backFromMoreApp=findViewById(R.id.btn_backFromMoreApp);
        btn_videoRecorder=findViewById(R.id.btn_videorecorder);
        btn_cameraCapture=findViewById(R.id.btn_cameraCapture);

        btn_cameraCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MoreApp.this,CameraCapture.class);
                startActivity(i);
                finish();
            }
        });
        btn_videoRecorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(MoreApp.this,VideoRecorder.class);
                startActivity(j);
                finish();
            }
        });
        btn_backFromMoreApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k= new Intent(MoreApp.this,Home.class);
                startActivity(k);
                finish();
            }
        });
    }
}