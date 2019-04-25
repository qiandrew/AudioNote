package audionote;
import java.util.*;
public class TranscriptionKeyWord{

    private String word;
    private int frequency;
    private ArrayList<TranscriptionWord> occurences;

    //Words should be added sequentially
    public void TranscriptionKeyWord(String str){
        word = str;
    }

    public void addOccurence(TranscriptionWord tw){
        occurences.add(tw);
        increment();
    }

    public void addOccurence(double startTime, double endTime, double confidence){
        TranscriptionWord tw  = new TranscriptionWord(word, confidence, startTime, endTime);
        addOccurence(tw);
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