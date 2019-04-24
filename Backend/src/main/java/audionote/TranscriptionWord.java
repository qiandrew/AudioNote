
public class TranscriptionWord{
    public void TranscriptionWord(){
        word = "";
        confidence = 1;
        startTime = 0;
        endTime = 0;
        totalTime = 0;
    }
    public void TrasncriptionWord(){

    }
    public void TrascriptionWord(String str, double confid, double start, double end){
        word = str;
        confidence = confid;
        startTime = start;
        endTime = end;
        totalTime = startTime - endTime;
    }

    public static String getWord(){
        return word;
    }

    public static double getConfidence(){
        return confidence;
    }

    public static double getStartTime(){
        return startTime;
    }

    public static double getEndTime(){
        return endTime;
    }

    public static double getTotalTime(){
        return totalTime;
    }

    private String word;
    private double confidence;
    private double startTime;
    private double endTime;
    private double totalTime;
}