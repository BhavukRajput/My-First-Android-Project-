package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class QuestionThirdQuizApp extends AppCompatActivity {
    RadioButton r1,r2,r3;
    Button nextQuestion3,backFromQuizApp3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_third_quiz_app);
        r1=findViewById(R.id.btn_q3ans1);
        r2=findViewById(R.id.btn_q3ans2);
        r3=findViewById(R.id.btn_q3ans3);
        backFromQuizApp3=findViewById(R.id.backFromquizApp3);
        nextQuestion3=findViewById(R.id.bttonNextQues3);
        backFromQuizApp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuestionThirdQuizApp.this,Home.class);
                startActivity(intent);
                finish();
            }
        });
        nextQuestion3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (r3.isChecked()){
                    ++QuizApp.score;

                }else{
                    --QuizApp.score;

                }
                Intent i=new Intent(QuestionThirdQuizApp.this,ResultOfQuiz.class);
                startActivity(i);
                finish();
            }
        });
    }
}