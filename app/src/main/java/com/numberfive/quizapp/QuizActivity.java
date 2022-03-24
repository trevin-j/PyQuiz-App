package com.numberfive.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import java.util.Collections;

import android.view.Gravity;
import android.view.View;
import android.widget.Button;

import android.widget.Chronometer;
import android.widget.LinearLayout;
import android.app.ActionBar;
import android.widget.TextView;

import java.util.ArrayList;

import com.numberfive.quizapp.questions.Question;
import com.numberfive.quizapp.questions.QuestionManager;

import org.json.JSONException;


public class QuizActivity extends AppCompatActivity {

    private Chronometer chronometer;
    private int counter = 10;
    private boolean isPlaying = true;
    private QuestionManager questionManager;
    private Question question;
    // Keep the category persistent
    private String category;

    private int score;

    private Button[] btns = new Button[4];
    private TextView[] answerTexts = new TextView[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Initialize the questionManager. This will be persistent while the activity is enabled.
        questionManager = new QuestionManager(this);

        score = 0;

        // Set the category to what the user picked
        Intent intent = getIntent();
        category = intent.getStringExtra(MainActivity.CATEGORY_KEY);

        // Get the buttons
        btns[0] = findViewById(R.id.btnA);
        btns[1] = findViewById(R.id.btnB);
        btns[2] = findViewById(R.id.btnC);
        btns[3] = findViewById(R.id.btnD);

        // Get the textview answers
        answerTexts[0] = findViewById(R.id.q1);
        answerTexts[1] = findViewById(R.id.q2);
        answerTexts[2] = findViewById(R.id.q3);
        answerTexts[3] = findViewById(R.id.q4);

        // Get chronometer ui reference
        chronometer = findViewById(R.id.chronometer);

        // We have already called the necessary stuff to set up for the first time,
        // now call the reset method to set up the rest of the stuff which will not be persistent the whole time
        resetQuestion();
    }

    private void resetQuestion() {
        setRandomQuestion();
        ChangeUI();

        // Update the score counter
        ((TextView) findViewById(R.id.scoreView)).setText("Score: " + score);

        setAnswersEnabled(true);

        // Hide next button
        findViewById(R.id.buttonNext).setVisibility(View.GONE);

        // run clock
        counter = 10;
        chronometer.setText("10");
        chronometer.start();
        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if (counter < 0 ){
                    Button nextButton = findViewById(R.id.buttonNext);
                    nextButton.setVisibility(View.VISIBLE);
                    chronometer.stop();
                    counter = 0;
                    setAnswersEnabled(false);
                }
                if(counter > 10){
                    chronometer.setText("10");
                }else{
                    chronometer.setText(counter + "");
                }
                counter--;
            }
        });
    }

    /** change button and text to reflect question selected to display */
    private void ChangeUI () {
        // Unhide all buttons in case some were invisible
        for (Button btn:
             btns) {
            btn.setVisibility(View.VISIBLE);
        }

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

        // Hide extra buttons
        for (int i = 3; i > answerList.size() - 1; i--) {
            btns[i].setVisibility(View.GONE);
        }
    }

    /**
     * Set the question to a new random question.
     * Should be called when resetting or when activity is first enabled
     */
    private void setRandomQuestion(){
        try{
            question = questionManager.getRandomQuestionByCategory(category);
        } catch (JSONException e) {
            // Something unexpected happened and no idea what to do if this happens yet
            e.printStackTrace();
        }
    }

    public void checkAnswer(View view) {
        // Disable buttons
        setAnswersEnabled(false);

        // Stop the timer
        chronometer.stop();

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

            // Score adds 10 if right, and 10 for each second left
            score += 10 + (counter + 1);
            // Update the score counter
            ((TextView) findViewById(R.id.scoreView)).setText("Score: " + score);
        } else {
            button.setBackgroundColor(Color.RED);
        }

        // Set the next button to be visible
        Button nextButton = findViewById(R.id.buttonNext);
        nextButton.setVisibility(View.VISIBLE);
    }

    private void setAnswersEnabled(boolean enabled){
        for (Button btn:
                btns) {
            btn.setEnabled(enabled);
            if (enabled)
                btn.setBackgroundColor(Color.rgb(98, 0, 238));
            else
                btn.setBackgroundColor(Color.LTGRAY);
        }
    }

    /**
     * Called by the Next button when clicked. Resets to new question while keeping score persistent.
     * @param view
     */
    public void onNext(View view) {
        resetQuestion();
    }

}