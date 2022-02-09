package com.numberfive.quizapp.questions;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class QuestionManager {

    /**
     * qjson holds the json data for all the questions.
     * It is automatically loaded in when QuestionManager is initialized.
     */
    private JSONObject questionsJSON;

    /**
     * The context for the application. Used for collecting data from assets.
     */
    private final Context appContext;

    /**
     * Constructor
     * @param context Application context. Used to get data from the assets folder.
     */
    public QuestionManager(Context context) {
        appContext = context;
        loadJson();
    }

    /**
     * Get a Question from the questions.json
     * @param id The question ID
     * @return A Question object containing details about the question
     */
    public Question getQuestion(String id) {
        try {
            JSONObject questionJSON = questionsJSON.getJSONObject(id);
            return new Question(questionJSON);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Get a random question from the given category.
     * @param category A category to retrieve from.
     * @return A Question object that is from the given category.
     * @throws JSONException If the specified category does not exist in the json file.
     */
    public Question getRandomQuestionByCategory(String category) throws JSONException {
        String categoryCode = category.substring(0, 2).toUpperCase();
        int amountQuestions = questionsJSON.getJSONObject("question_data").getJSONObject(categoryCode).getInt("amount");

        Random rand = new Random();
        String questionID = categoryCode + rand.nextInt(amountQuestions);

        return getQuestion(questionID);
    }

    /**
     * Load the questions json into the private questionsJSON field.
     */
    private void loadJson() {
        // Source of some of this code: https://stackoverflow.com/questions/13814503/reading-a-json-file-in-android
        String json;
        try {
            InputStream is = appContext.getAssets().open("file_name.json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, StandardCharsets.UTF_8);

            questionsJSON = new JSONObject(json);

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

    }
}
