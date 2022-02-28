package com.numberfive.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.numberfive.quizapp.questions.Question;
import com.numberfive.quizapp.questions.QuestionManager;

import org.json.JSONException;


public class QuizActivity extends AppCompatActivity {
    private QuestionManager questionManager = new QuestionManager();
    Question question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
    }

    private void getQuestion(){
        Intent intent = getIntent();
        String category = intent.getStringExtra(MainActivity.CATEGORY_KEY);
        try{
            question = questionManager.getRandomQuestionByCategory(category);
        }

        catch (JSONException e){
            e.printStackTrace();
        }


    }
}