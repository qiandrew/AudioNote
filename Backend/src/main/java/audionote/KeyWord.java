package audionote;
import java.util.*;
public class KeyWord{

    private String word = "";
    private int frequency = 0;
    public ArrayList<Word> occurences = new ArrayList<Word>();

    //Words should be added sequentially
    public KeyWord(String str){
        word = str;
    }

    public KeyWord(Word w){
        this(w.getWord());
        addOccurence(w);
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