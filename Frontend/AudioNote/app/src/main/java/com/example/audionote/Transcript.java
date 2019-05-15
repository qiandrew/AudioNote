package com.example.audionote;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

public class Transcript {

    private String jobID;
    private String text;
    private Word[] words;
    private KeyWord[] keyWords;
    private URL fileLocation;
    private boolean isFinished = false;

    Transcript(String jobID) {
        this.jobID = jobID;
    }

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
}
