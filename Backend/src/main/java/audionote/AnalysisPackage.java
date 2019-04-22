package audionote;

import java.io.File;
import java.io.IOException;
import java.util;
import org.json.*;



public class Analysis{
    public Analysis(){
        wordNum = 0;
    }
    public Analysis(String str){
        s = str;
        sf = format(str);
        addWords(sf);
    }

    public void incrementWord(String str) {
        //for(int i = 0; i < )
        return;
        //Adds a singular word or increases its count by 1.
    }
    public void addWords(String str){
        String currWord;
        for(int i = 0; i < str.length(); i++){
            if(str[i] == 32){
                if(currWord != ""){
                    incrementWord(currWord);
                }
                currWord = "";
            }
            else{
                currWord += str[i];
            }
        }
    }//Adds a String that has more than one word.

    public String format(String str){
        String ret;
        for(int i = 0; i < s.legnth; i++){
            if(str[i] == 32 || (str[i] >= 97 && str[i] <= 122)){
                ret += str[i];
            }
            else if(str[i] >= 65 && str[i] <= 90){
                ret += (str[i] + 32)
            }
        }
        return ret;
    }//Formats into all lowercase, no special characters.

    private Arraylist<Pair<String, Integer>> wordListNumSort;
    private ArrayList<Pair<String, Integer>> wordListAlphaSort;
    private int wordNum;
    private string s;
    private string sf;

}
