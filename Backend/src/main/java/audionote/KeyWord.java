package audionote;
import java.util.*;
public class KeyWord{

    private String word;
    private int frequency;
    private ArrayList<Word> occurences;

    //Words should be added sequentially
    public KeyWord(String str){
        word = str;
    }

    public void addOccurence(Word w){
        occurences.add(w);
        increment();
    }

    public void addOccurence(double startTime, double endTime, double confidence){
        Word w  = new Word(word, confidence, startTime, endTime);
        addOccurence(w);
    }

    public String getWord(){
        return word;
    }

    public int getFrequency(){
        return frequency;
    }

    public double getFirstTime(){
        if(frequency == 0){
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