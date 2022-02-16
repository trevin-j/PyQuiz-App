package com.numberfive.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.view.View;

public class MainActivity extends AppCompatActivity implements  View.OnClickListener{

    public static final String CATAGORY_KEY = "com.numberfive.quizapp.CATAGORY";

    Button btnSyn, btnBoo, btnVar, btnDat, btnLis, btnStr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSyn = findViewById(R.id.button_synonym);
        btnBoo= findViewById(R.id.button_boolean);
        btnVar= findViewById(R.id.button_variable);
        btnDat= findViewById(R.id.button_data);
        btnLis= findViewById(R.id.button_list);
        btnStr= findViewById(R.id.button_string);

        btnSyn.setOnClickListener(this);
        btnBoo.setOnClickListener(this);
        btnVar.setOnClickListener(this);
        btnDat.setOnClickListener(this);
        btnLis.setOnClickListener(this);
        btnStr.setOnClickListener(this);


    }

    public void runActivity(View v, String cat) {
        Intent intent = new Intent(this, QuizActivity.class);
        String catagory = cat;
        intent.putExtra(CATAGORY_KEY, catagory);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_synonym:
                runActivity(this, "syn");
                break;
            case R.id.button_boolean:
                runActivity(this, "boo");
                break;
            case R.id.button_variable:
                runActivity(this, "var");
                break;
            case R.id.button_data:
                runActivity(this, "dat");
                break;
            case R.id.button_list:
                runActivity(this, "lis");
                break;
            case R.id.button_string:
                runActivity(this, "str");
                break;
        }
    }
}