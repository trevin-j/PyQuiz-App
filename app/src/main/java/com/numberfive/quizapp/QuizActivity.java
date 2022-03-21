package com.numberfive.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import java.util.Collections;

import android.view.Gravity;
import android.view.View;
import android.widget.Button;

import android.widget.LinearLayout;
import android.app.ActionBar;
import android.widget.TextView;

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
        ArrayList<String> answerList = question.getAnswers();
        Collections.shuffle(answerList);

        // initialize values
        String[] answers = new String[4];

        // Set all answers in array to " "
        for (String answer:
             answers) {
            answer = " ";
        }

        // Set all answers that aren't blank to the proper answer
        for (int i = 0; i < answerList.size(); i++){
            answers[i] = answerList.get(i);
        }

        // initialize objects in UI
        // Get the question TextView
        TextView TestQuestion = findViewById(R.id.question);

        // Get the TextView answers
        TextView[] answerTexts = new TextView[4];
        answerTexts[0] = findViewById(R.id.q1);
        answerTexts[1] = findViewById(R.id.q2);
        answerTexts[2] = findViewById(R.id.q3);
        answerTexts[3] = findViewById(R.id.q4);

        // Set the text
        TestQuestion.setText(question.getQuestion());

        // Set answers
        for (int i = 0; i < answers.length; i++) {
            answerTexts[i].setText(answers[i]);
        }
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
        // Get the buttons
        Button[] btns = new Button[4];
        btns[0] = findViewById(R.id.btnA);
        btns[1] = findViewById(R.id.btnB);
        btns[2] = findViewById(R.id.btnC);
        btns[3] = findViewById(R.id.btnD);

        // Get the TextView answers
        TextView[] answerTexts = new TextView[4];
        answerTexts[0] = findViewById(R.id.q1);
        answerTexts[1] = findViewById(R.id.q2);
        answerTexts[2] = findViewById(R.id.q3);
        answerTexts[3] = findViewById(R.id.q4);

        // Get the pressed button
        Button button = (Button) view;

        String text = " ";
        for (int i = 0; i < btns.length; i++) {
            if (button == btns[i])
                text = answerTexts[i].getText().toString();
        }

        boolean correct = question.isCorrect(text);
        if (correct) {
            button.setBackgroundColor(Color.GREEN);
        } else {
            button.setBackgroundColor((Color.RED));
        }

        for (Button btn:
             btns) {
            btn.setEnabled(false);
        }

        createNextButton();
    }

    void createNextButton() {
        LinearLayout parentLayout = (LinearLayout) findViewById(R.id.nextButton);

        Button buttonNext = new Button(this);
        int id = 1;
        buttonNext.setId(id);
        buttonNext.setText("NEXT");

        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        buttonParams.setMargins(10,10,10,10);
        buttonParams.gravity = Gravity.CENTER;

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("It Works");
            }
        });
        parentLayout.addView(buttonNext,buttonParams);
    }


}