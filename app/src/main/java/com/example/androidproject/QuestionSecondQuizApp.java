package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class QuestionSecondQuizApp extends AppCompatActivity {
    RadioButton r1,r2,r3;
    Button nextQuestion2,backFromQuizApp2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_second_quiz_app);
        r1=findViewById(R.id.btn_q2ans1);
        r2=findViewById(R.id.btn_q2ans2);
        r3=findViewById(R.id.btn_q2ans3);
        backFromQuizApp2=findViewById(R.id.backFromquizApp2);
        nextQuestion2=findViewById(R.id.bttonNextQues2);
        backFromQuizApp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuestionSecondQuizApp.this,Home.class);
                startActivity(intent);
                finish();
            }
        });
        nextQuestion2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (r1.isChecked()){
                    ++QuizApp.score;
                }else{
                    --QuizApp.score;
                }
                Intent i=new Intent(QuestionSecondQuizApp.this,QuestionThirdQuizApp.class);
                startActivity(i);
                finish();
            }

            });
        }
    }