package com.example.audionote;

import java.net.URL;
import java.util.ArrayList;

public class Transcript {

    private String text;
    private ArrayList<Word> words;
    private ArrayList<KeyWord> keyWords;
    private URL fileLocation;
    private Boolean isFinished = false;

    Transcript() {

    }

    public void update(String text, ArrayList<Word> words, ArrayList<KeyWord> keyWords, URL fileLocation) {
        this.text = text;
        this.words = words;
        this.keyWords = keyWords;
        this.fileLocation = fileLocation;
        isFinished = true;
    }
}
