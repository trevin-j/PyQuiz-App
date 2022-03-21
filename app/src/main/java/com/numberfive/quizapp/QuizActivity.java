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
        ArrayList<String> answerList = question.getAnswers();
        Collections.shuffle(answerList);

        // initialize values
        String[] answers = new String[4];

        // Set all answers in array to " "
        for (String answer:
             answers) {
            answer = " ";
        }

        // Set all answers that aren't blank to the proper answer
        for (int i = 0; i < answerList.size(); i++){
            answers[i] = answerList.get(i);
        }

        // initialize objects in UI
        // Get the question TextView
        TextView TestQuestion = findViewById(R.id.question);

        // Get the TextView answers
        TextView[] answerTexts = new TextView[4];
        answerTexts[0] = findViewById(R.id.q1);
        answerTexts[1] = findViewById(R.id.q2);
        answerTexts[2] = findViewById(R.id.q3);
        answerTexts[3] = findViewById(R.id.q4);

        // Set the text
        TestQuestion.setText(question.getQuestion());

        // Set answers
        for (int i = 0; i < answers.length; i++) {
            answerTexts[i].setText(answers[i]);
        }
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