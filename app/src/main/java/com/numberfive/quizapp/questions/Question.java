package com.numberfive.quizapp.questions;

import androidx.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Question {
    /**
     * The String representation of the question.
     * Use getQuestion() to get this.
     */
    private String question;

    /**
     * The id of this question, stored as a String.
     * use getQuestionID() to get this.
     */
    private String id;

    /**
     * Every answer, stored as a JSONArray.
     * Use getAnswers() to retrieve this.
     * getAnswers() returns an ArrayList instead of JSONArray for ease of use.
     */
    private JSONArray answers;

    /**
     * Every index of the correct answers from the answers ArrayList.
     * Use getCorrectAnswers() to retrieve.
     * getCorrectAnswers() returns an ArrayList instead of JSONArray for ease of use.
     */
    private JSONArray correctAnswers;

    /**
     * Construct the Question. Questions should really only be created by QuestionManager.
     * @param questionData JSONObject containing the question data. Should be retrieved from questions.json.
     */
    public Question(JSONObject questionData) {
        try {
            question = questionData.getString("question");
            id = questionData.getString("id");
            answers = questionData.getJSONArray("answers");
            correctAnswers = questionData.getJSONArray("correct_answers");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the question.
     * @return The question as a String.
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Get the question's id.
     * @return The question's id as a String.
     */
    public String getQuestionID() {
        return id;
    }

    /**
     * Get all the answers, stored in an ArrayList.
     * @return And ArrayList of the Strings, each String is an answer.
     */
    public ArrayList<String> getAnswers() {
        ArrayList<String> answersList = new ArrayList<>();
        for (int i = 0; i < answers.length(); i++) {
            try {
                answersList.add(answers.getString(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return answersList;
    }

    /**
     * Get the indices of all the correct answers.
     * The Integers are all the indices of the answers from getAnswers() that are correct.
     * @return An ArrayList of Integers which holds the indices of the correct answers.
     */
    public ArrayList<Integer> getCorrectAnswers() {
        ArrayList<Integer> correctAnswersList = new ArrayList<>();
        for (int i = 0; i < correctAnswers.length(); i++) {
            try {
                correctAnswersList.add(correctAnswers.getInt(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return correctAnswersList;
    }

    /**
     * Get a String representation of the question data for debug purposes.
     * @return A String representation of the question data for debug purposes.
     */
    @NonNull
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Question: ");
        stringBuilder.append(question);
        stringBuilder.append("\nID: ");
        stringBuilder.append(id);
        stringBuilder.append("\nAnswers: ");

        ArrayList<String> answers = getAnswers();
        for (int i = 0; i < answers.size(); i++) {
            stringBuilder.append("\n").append(i).append(") ");
            stringBuilder.append(answers.get(i));
        }

        stringBuilder.append("\nCorrect answer indices: ");
        ArrayList<Integer> correctAnswers = getCorrectAnswers();
        for (int i = 0; i < correctAnswers.size(); i++) {
            stringBuilder.append(correctAnswers.get(i)).append(",");
        }

        return stringBuilder.toString();
    }

    /**
     * Check if the answer is correct, based on the answer's index.
     * @param answerIndex The index of the answer to check.
     * @return True if the index of the answer is correct, else false.
     */
    public boolean isCorrect(int answerIndex) {
        ArrayList<Integer> correctAnswers = getCorrectAnswers();
        return correctAnswers.contains(answerIndex);
    }

    /**
     * Check if the answer is correct, based on the answer as a String.
     * @param answer The answer as a String.
     * @return True if the answer is correct, else false.
     */
    public boolean isCorrect(String answer) {
        ArrayList<Integer> correctAnswerIndices = getCorrectAnswers();
        ArrayList<String> allAnswers = getAnswers();
        for (Integer index:
             correctAnswerIndices) {
            if (allAnswers.get(index).equals(answer))
                return true;
        }
        return false;
    }
}
