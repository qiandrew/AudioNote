package audionote;
public class Word{

    private String word = "";
    private double confidence = 0;
    private double startTime = 0;
    private double endTime = 0;
    private double totalTime = 0;


    public Word(String str, double confid, double start, double end){
        word = str;
        confidence = confid;
        startTime = start;
        endTime = end;
        totalTime = endTime - startTime;
    }

    public String getWord() {
        return word;
    }

    public double getConfidence() {
        return confidence;
    }

    public double getStartTime() {
        return startTime;
    }

    public double getEndTime() {
        return endTime;
    }

    public double getTotalTime() {
        return totalTime;
    }

}