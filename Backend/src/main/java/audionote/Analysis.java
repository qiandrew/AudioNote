package audionote;

import java.io.File;
import java.io.IOException;
import java.util;
import org.json.*;
public class Parser{

}

public class AnalysisPackage{
    public AnalysisPackage(){
        wordNum = 0;
    }
    public AnalysisPackage(String s){
        String sf = format(s);
    }
    public incrementWord(String s) {
        //Adds a singular word or increases its count by 1.
    }
    public void addWords(String s);//Adds a String that has more than one word.
    public String format(String s);//Formats into all lowercase, no special characters.

    private Arraylist<Pair<String, Integer>> wordListNumSort;
    private ArrayList<Pair<String, Integer>> wordListAlphaSort;
    private int wordNum;
}
