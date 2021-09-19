package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;

public class QuizApp extends AppCompatActivity {
    RadioButton r1,r2,r3;
    Button nextQuestion1,backFromQuizApp;
    static int score=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_quiz_app);
        r1=findViewById(R.id.btn_ans1);
        r2=findViewById(R.id.btn_ans2);
        r3=findViewById(R.id.btn_ans3);
        backFromQuizApp=findViewById(R.id.backFromQuizApp1);
        nextQuestion1=findViewById(R.id.bttonNextQues1);
        backFromQuizApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizApp.this,Home.class);
                startActivity(intent);
                finish();
            }
        });
        nextQuestion1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score=0;
                if (r3.isChecked()){
                    ++score;
                }else {
                    --score;
                }
                Intent i=new Intent(QuizApp.this,QuestionSecondQuizApp.class);
                startActivity(i);
                finish();

            }
        });
    }
}