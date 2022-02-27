package com.numberfive.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.TextView;
import android.widget.Button;
import java.util.ArrayList;

import com.numberfive.quizapp.questions.Question;

public class QuizActivity extends AppCompatActivity {

    private Question QUIZ;
    private ArrayList<String> ANSWERS = QUIZ.getAnswers();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        //call function;
        ChangeUI();
    }

    /** change button and text to reflect question selected to display */
    private void ChangeUI () {

        // get quiz info
        String answer1 = ANSWERS.get(0);
        String answer2 = ANSWERS.get(1);
        String answer3 = ANSWERS.get(2);
        String answer4 = ANSWERS.get(3);

        // initialize objects in UI
        TextView TestQuestion = findViewById(R.id.textView);
        Button Answer1 = findViewById(R.id.q1);
        Button Answer2 = findViewById(R.id.q2);
        Button Answer3 = findViewById(R.id.button3);
        Button Answer4 = findViewById(R.id.button4);

        //set text
        TestQuestion.setText(QUIZ.getQuestion());
        Answer1.setText(answer1);
        Answer2.setText(answer2);
        Answer3.setText(answer3);
        Answer4.setText(answer4);
    }
}