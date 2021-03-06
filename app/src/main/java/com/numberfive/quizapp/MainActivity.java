package com.numberfive.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.view.View;

public class MainActivity extends AppCompatActivity implements  View.OnClickListener{

    public static final String CATEGORY_KEY = "com.numberfive.quizapp.CATEGORY";

    // Setting the names of the buttons
    Button btnSyn, btnBoo, btnVar, btnDat, btnLis, btnStr, btnRan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setting the values of the buttons to the button id's in
        // in the xml file.
        btnSyn = findViewById(R.id.button_syntax);
        btnBoo= findViewById(R.id.button_boolean);
        btnVar= findViewById(R.id.button_variable);
        btnDat= findViewById(R.id.button_data);
        btnLis= findViewById(R.id.button_list);
        btnStr= findViewById(R.id.button_string);
        btnRan = findViewById(R.id.button_random);

        // calling the click listener so that  you can know when a button is
        // pressed.
        btnSyn.setOnClickListener(this);
        btnBoo.setOnClickListener(this);
        btnVar.setOnClickListener(this);
        btnDat.setOnClickListener(this);
        btnLis.setOnClickListener(this);
        btnStr.setOnClickListener(this);
        btnRan.setOnClickListener(this);


    }

    public void runActivity(String cat) {
        Intent intent = new Intent(this, QuizActivity.class);
        String category = cat;
        intent.putExtra(CATEGORY_KEY, category);
        startActivity(intent);
    }
    // will see which button is pressed and depending on which one is it will
    // call the runActivity class and will send it the category.
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_syntax:
                runActivity("syn");
                break;
            case R.id.button_boolean:
                runActivity("boo");
                break;
            case R.id.button_variable:
                runActivity("var");
                break;
            case R.id.button_data:
                runActivity("dat");
                break;
            case R.id.button_list:
                runActivity("lis");
                break;
            case R.id.button_string:
                runActivity("str");
                break;
            case R.id.button_random:
                runActivity("ran");
                break;
        }
    }
}