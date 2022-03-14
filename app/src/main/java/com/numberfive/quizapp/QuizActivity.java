package com.numberfive.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import java.util.Collections;
import android.view.View;
import android.widget.Button;

import android.widget.TextView;

import java.util.ArrayList;

import com.numberfive.quizapp.questions.Question;
import com.numberfive.quizapp.questions.QuestionManager;

import org.json.JSONException;


public class QuizActivity extends AppCompatActivity {
    
    private QuestionManager questionManager;
    Question question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // call setup methods
        getQuestion();
        ChangeUI();
    }

    /** change button and text to reflect question selected to display */
    private void ChangeUI () {
        // shuffle answers
        ArrayList<String> ANSWERS = question.getAnswers();
        Collections.shuffle(ANSWERS);
        int length_answer = ANSWERS.size();

        // initialize values
        String answer1 = " ";
        String answer2 = " ";
        String answer3 = " ";
        String answer4 = " ";

        // set values due to size of array
        if(length_answer > 1) {
            answer1 = ANSWERS.get(0);
            answer2 = ANSWERS.get(1);
        }
        if(length_answer > 2) {
            answer3 = ANSWERS.get(2);
        }
        if(length_answer > 3) {
            answer4 = ANSWERS.get(3);
        }

        // initialize objects in UI
        // Get the question TextView
        TextView TestQuestion = findViewById(R.id.question);

        // Get the TextView answers
        TextView answer1Text = findViewById(R.id.q1);
        TextView answer2Text = findViewById(R.id.q2);
        TextView answer3Text = findViewById(R.id.q3);
        TextView answer4Text = findViewById(R.id.q4);

        // Set the text
        TestQuestion.setText(question.getQuestion());
        answer1Text.setText(answer1);
        answer2Text.setText(answer2);
        answer3Text.setText(answer3);
        answer4Text.setText(answer4);
    }

    private void getQuestion(){
        questionManager = new QuestionManager(this);
        Intent intent = getIntent();
        String category = intent.getStringExtra(MainActivity.CATEGORY_KEY);
        try{
            question = questionManager.getRandomQuestionByCategory(category);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void checkAnswer(View view) {
        // Get the buttons
        Button btn1 = findViewById(R.id.btnA);
        Button btn2 = findViewById(R.id.btnB);
        Button btn3 = findViewById(R.id.btnC);
        Button btn4 = findViewById(R.id.btnD);

        // Get the TextView answers
        TextView answer1Text = findViewById(R.id.q1);
        TextView answer2Text = findViewById(R.id.q2);
        TextView answer3Text = findViewById(R.id.q3);
        TextView answer4Text = findViewById(R.id.q4);

        Button button = (Button) view;

        String text = " ";
        if (button == btn1)
            text = answer1Text.getText().toString();
        if (button == btn2)
            text = answer2Text.getText().toString();
        if (button == btn3)
            text = answer3Text.getText().toString();
        if (button == btn4)
            text = answer4Text.getText().toString();

        boolean correct = question.isCorrect(text);
        if (correct) {
            button.setBackgroundColor(Color.GREEN);
        } else {
            button.setBackgroundColor((Color.RED));
        }
    }
}