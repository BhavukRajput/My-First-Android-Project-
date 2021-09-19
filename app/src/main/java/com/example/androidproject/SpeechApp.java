package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import java.util.Locale;

public class SpeechApp extends AppCompatActivity implements TextToSpeech.OnInitListener {

    EditText input;
    Button spk_button,backtoHome;
    TextToSpeech TTS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_speech_app);
        TTS=new TextToSpeech(this,this);
        input=findViewById(R.id.input_field);
        spk_button=findViewById(R.id.speak_button);
        backtoHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent h = new Intent(SpeechApp.this,Home.class);
                startActivity(h);
                finish();
            }
        });
        spk_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak();
            }
        });
    }

    @Override
    public void onInit(int status) {
        if (status==TextToSpeech.SUCCESS){
            int result=TTS.setLanguage(Locale.getDefault());
            TTS.setSpeechRate(1);
            TTS.setPitch(1);
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                Log.e("TTS","Language not supported");
            }
            else {
                spk_button.setEnabled(true);
                speak();
            }
        }else {
            Log.e("TextToSpeech","Initialization Failed");
        }
    }
    private void speak() {
        String message = input.getText().toString();
        TTS.speak(message,TextToSpeech.QUEUE_FLUSH,null,null);
    }
}