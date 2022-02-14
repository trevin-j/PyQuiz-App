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

    public void runActivity(View v) {
        Intent intent = new Intent(this, question.class);
        String catagory = onclick();
        intent.putExtra(CATAGORY_KEY, catagory)
    }
}