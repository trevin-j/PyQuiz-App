package com.numberfive.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ScoreScreen extends AppCompatActivity {

    private int score;
    TextView scoreField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_screen);

        Intent intent = getIntent();

        score = intent.getIntExtra(QuizActivity.SCORE_KEY, -1);

        scoreField = findViewById(R.id.score);

        scoreField.setText(score);
    }
}