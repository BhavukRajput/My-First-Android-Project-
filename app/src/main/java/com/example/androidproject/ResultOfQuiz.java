package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultOfQuiz extends AppCompatActivity {
    TextView t1;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_of_quiz);
        t1 = (TextView) findViewById(R.id.txtViewresult1);
        b1 = (Button) findViewById(R.id.backFromquizApp4);
        t1.setText("Your result is " + QuizApp.score);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ResultOfQuiz.this, Home.class);
                startActivity(i);
                finish();
            }
        });
    }
}