package com.numberfive.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
        getQuestion();
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

        }
    }
}