package com.numberfive.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    public static final String CATAGORY_KEY = "com.numberfive.quizapp.CATAGORY"

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void runActivity(View v, String cat) {
        Intent intent = new Intent(this, QuizActivity.class);
        String catagory = cat;
        intent.putExtra(CATAGORY_KEY, catagory)
    }
}