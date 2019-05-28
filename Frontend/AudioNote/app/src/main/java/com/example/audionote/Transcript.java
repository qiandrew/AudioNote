package com.example.audionote;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;

public class Transcript implements Serializable {

    private String jobID;
    private String text;
    private Word[] words;
    private KeyWord[] keyWords;
    private URL fileLocation;
    private boolean isFinished = false;

    Transcript(String jobID) {
        this.jobID = jobID;
    }

    //
    public String getText() {return text;}
    public KeyWord[] getKeyWords() {return keyWords;}
    public void setText(String text) {this.text = text;}
    //

    public void update(String text, Word[] words, KeyWord[] keyWords, URL fileLocation) {
        this.text = text;
        this.words = words;
        this.keyWords = keyWords;
        this.fileLocation = fileLocation;
        isFinished = true;
    }

    public void jsonParser(JSONObject inputJSON) throws JSONException {
        JSONObject resultsObject = inputJSON.getJSONObject("result").getJSONObject("results");
        text = resultsObject.getJSONArray("transcripts").getJSONObject(0).getString("transcript");

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
