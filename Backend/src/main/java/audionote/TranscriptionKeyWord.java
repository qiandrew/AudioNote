import TrancriptionWord.java;
public class TranscriptionKeyWord{
    //Words should be added sequentially
    public void TranscriptionKeyWord(){
        firstTime = -1;
    }

    public void TranscriptionKeyWord(String str){
        word = str;
        firstTime = -1;
    }

    public void addOccurence(TranscriptionWord tw){
        if(firstTime == -1){
            firstTime = tw.getStartTime();
        }
        occurences.add(tw);
        increment();
    }

    public void setCommonWord(boolean val){
        commonWord = val;
    }

    public static boolean getCommonWord(){
        return commonWord;
    }

    public static String getWord(){
        return word;
    }

    public static int getFrequency(){
        return frequency;
    }

    public static double getFirstTime(){
        return firstTime();
    }

    private void increment(){
        frequency++;
    }

    private boolean commonWord;
    private String word;
    private int frequency;
    private ArrayList<TranscriptionWord> occurences;
    private double firstTime;
}