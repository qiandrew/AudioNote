package audionote;
public class TranscriptionWord{

    private String word;
    private double confidence;
    private double startTime;
    private double endTime;
    private double totalTime;

    public void TranscriptionWord(String str, double confid, double start, double end){
        word = str;
        confidence = confid;
        startTime = start;
        endTime = end;
        totalTime = startTime - endTime;
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