package com.example.audionote;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.net.URL;
import java.security.Key;
import java.util.ArrayList;

public class Transcript implements Serializable {

    private String jobID;
    private String text;
    private Word[] words = new Word[0];
    private KeyWord[] keyWords = new KeyWord[0];
    private URL fileLocation;
    private boolean isFinished = false;

    Transcript(String jobID) {
        this.jobID = jobID;
    }

    //
    public String getJobID() {return jobID;}
    public String getText() {return text;}
    public KeyWord[] getKeyWords() {return keyWords;}
    public void setText(String text) {this.text = text;}
    public Boolean getIsFinished() {return isFinished; }
    //

    public void update(String text, Word[] words, KeyWord[] keyWords, URL fileLocation) {
        this.text = text;
        this.words = words;
        this.keyWords = keyWords;
        this.fileLocation = fileLocation;
        isFinished = true;
    }

    public void jsonParser(JSONObject inputJSON) throws JSONException {
        JSONObject resultsObject = inputJSON.getJSONObject("results");
        text = resultsObject.getString("transcript");

        JSONArray words = resultsObject.getJSONArray("words");
        ArrayList<Word> allWords = new ArrayList<>();
        for (int i = 0; i < words.length(); i++) {
            JSONObject object = words.getJSONObject(i);
            Word word = new Word(object.getString("word"), 1.0, object.getDouble("start_time"), object.getDouble("end_time"));
            allWords.add(word);
        }
        Word[] converted = new Word[allWords.size()];
        converted = allWords.toArray(converted);
        this.words = converted;

        JSONArray keyWords = resultsObject.getJSONArray("key_words");
        ArrayList<KeyWord> allKeyWords = new ArrayList<>();
        for (int i = 0; i < keyWords.length(); i++) {
            JSONObject object = keyWords.getJSONObject(i);


            JSONArray keyWordOccurances = object.getJSONArray("occurences");
            ArrayList<Word> occurances = new ArrayList<>();
            for (int j = 0; j < keyWordOccurances.length(); j++) {
                JSONObject wordObject = keyWordOccurances.getJSONObject(i);
                Word occuranceWord = new Word(wordObject.getString("word"), 1.0, wordObject.getDouble("start_time"), wordObject.getDouble("end_time"));
                occurances.add(occuranceWord);
            }

            KeyWord keyWord = new KeyWord(object.getString("word"), occurances);
            allKeyWords.add(keyWord);
        }

        KeyWord[] convertedKey = new KeyWord[allKeyWords.size()];
        convertedKey = allKeyWords.toArray(convertedKey);
        this.keyWords = convertedKey;

        this.isFinished = true;

        // text = resultsObject.getJSONArray("transcripts").getJSONObject(0).getString("transcript");

    }

    @Override
    public boolean equals(Object obj) {

        // check for null
        if (obj == null) {
            return false;
        }

        // Check if it's a transcript
        if (!Transcript.class.isAssignableFrom(obj.getClass())) {
            return false;
        }

        // Check job ids
        final Transcript other = (Transcript) obj;
        return other.jobID.equals(jobID);
    }

}
