package com.numberfive.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import java.util.Collections;
import android.view.View;
import android.widget.Button;

import android.widget.TextView;
import android.widget.Button;
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
        TextView TestQuestion = findViewById(R.id.textView);
        Button Answer1 = findViewById(R.id.q1);
        Button Answer2 = findViewById(R.id.q2);
        Button Answer3 = findViewById(R.id.button3);
        Button Answer4 = findViewById(R.id.button4);

        //set text
        TestQuestion.setText(question.getQuestion());
        Answer1.setText(answer1);
        Answer2.setText(answer2);
        Answer3.setText(answer3);
        Answer4.setText(answer4);
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
        Button button = (Button) view;
        String selectedAnswer = button.getText().toString();

        boolean correct = question.isCorrect(selectedAnswer);
        if (correct) {
            button.setBackgroundColor(Color.GREEN);
        } else {
            button.setBackgroundColor((Color.RED));
        }
    }
}