package com.numberfive.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.view.View;

public class MainActivity extends AppCompatActivity implements  View.OnClickListener{

    public static final String CATAGORY_KEY = "com.numberfive.quizapp.CATAGORY";

    Button syn, boo, var, dat, lis, str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        syn= findViewById(R.id.button);
        boo= findViewById(R.id.button5);
        var= findViewById(R.id.button7);
        dat= findViewById(R.id.button10);
        lis= findViewById(R.id.button6);
        str= findViewById(R.id.button11);

        syn.setOnClickListener(this);
        boo.setOnClickListener(this);
        var.setOnClickListener(this);
        dat.setOnClickListener(this);
        lis.setOnClickListener(this);
        str.setOnClickListener(this);


    }

    public void runActivity(View v, String cat) {
        Intent intent = new Intent(this, QuizActivity.class);
        String catagory = cat;
        intent.putExtra(CATAGORY_KEY, catagory);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                runActivity(this, "syn");
                break;
            case R.id.button5:
                runActivity(this, "boo");
                break;
            case R.id.button7:
                runActivity(this, "var");
                break;
            case R.id.button10:
                runActivity(this, "dat");
                break;
            case R.id.button10:
                runActivity(this, "lis");
                break;
            case R.id.button11:
                runActivity(this, "str");
                break;
        }
    }
}