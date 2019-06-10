package com.example.audionote;
import java.io.Serializable;
import java.util.*;
public class KeyWord implements Serializable {

    private String word = "";
    private int frequency = 0;
    private ArrayList<Word> occurences = new ArrayList<Word>();

    //Words should be added sequentially
    public KeyWord(String str, ArrayList<Word> occurences){
        word = str;
        this.occurences = occurences;
        this.frequency = this.occurences.size();
    }

    public void addOccurence(Word w){
        occurences.add(w);
        increment();
    }

    public void addOccurence(double startTime, double endTime, double confidence){
        addOccurence(new Word(word, confidence, startTime, endTime));
    }

    public String getWord(){
        return word;
    }

    public int getFrequency(){
        return frequency;
    }

    public double getFirstTime(){
        if(frequency != 0){
            return occurences.get(0).getStartTime();
        }
        else{
            return 0;
        }
    }

    private void increment(){
        frequency++;
    }

}