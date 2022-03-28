package com.numberfive.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.view.View;
import android.widget.Button;

public class ScoreScreen extends AppCompatActivity {

    private int score;
    TextView scoreField;
    Button exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_screen);

        Intent intent = getIntent();

        score = intent.getIntExtra(QuizActivity.SCORE_KEY, -1);

        scoreField = findViewById(R.id.score);

        scoreField.setText(Integer.toString(score));

//        exit.findViewById(R.id.button);
//        exit.setOnClickListener(this);
    }

    public void returnMain(View view) {
        navigateUpTo(new Intent(getBaseContext(), MainActivity.class));
    }

}