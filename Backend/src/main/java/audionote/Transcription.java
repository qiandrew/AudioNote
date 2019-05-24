package audionote;
import java.lang.*;
import java.util.*;
import org.json.*;
import com.google.gson.*;

public class Transcription{
    private String status;
    private String jobID;
    private String str;//Transcript
    private ArrayList<Word> transcript = new ArrayList<Word>();
    private ArrayList<KeyWord> kw = new ArrayList<KeyWord>();

    public Transcription(String s){
        JsonElement jElement = new JsonParser().parse(s);
        JsonObject jObject = jElement.getAsJsonObject();
        jobID = jObject.get("jobName").getAsString();
        status = jObject.get("status").getAsString();
        JsonObject results = jObject.getAsJsonObject("results");
        JsonArray transcripts = results.getAsJsonArray("transcripts");
        str = transcripts.get(0).getAsJsonObject().get("transcript").getAsString();
        JsonArray items = results.getAsJsonArray("items");
        for(int i = 0; i < items.size(); i++){
            JsonObject iter = items.get(i).getAsJsonObject();
            if(iter.get("type").getAsString().equals("punctuation")){
                continue;
            }
            JsonArray alternatives = iter.getAsJsonArray("alternatives");
            JsonObject firstAlt = alternatives.get(0).getAsJsonObject();
            transcript.add(new Word(firstAlt.get("content").getAsString(), Double.parseDouble(firstAlt.get("confidence").getAsString()), Double.parseDouble(iter.get("start_time").getAsString()), Double.parseDouble(iter.get("end_time").getAsString())));
        }
        Analysis a = new Analysis(transcript);
        kw = a.getList();
    }

    public String toJSON(){
        JSONObject json = new JSONObject();
        json.put("status", status);
        json.put("jobID", jobID);

        JSONObject results = new JSONObject();
        results.put("transcript", str);
        JSONArray word = new JSONArray();
        for(int i = 0; i < transcript.size(); i++){
            JSONObject temp = new JSONObject();
            temp.put("word",transcript.get(i).getWord());
            temp.put("start_time",transcript.get(i).getStartTime());
            temp.put("end_time",transcript.get(i).getEndTime());
            word.put(temp);
        }
        results.put("words", word);
        JSONArray keyWord = new JSONArray();
        ArrayList<KeyWord> top = topWords();
        for(int i = 0; i < top.size(); i++){
            JSONObject temp1 = new JSONObject();
            temp1.put("word", top.get(i).getWord());
            temp1.put("frequency", top.get(i).getFrequency());
            JSONArray occur = new JSONArray();
            for(int iKey = 0; iKey < top.get(i).occurences.size(); iKey++){
                JSONObject temp2 = new JSONObject();
                temp2.put("word",top.get(i).getWord());
                temp2.put("start_time",top.get(i).occurences.get(iKey).getStartTime());
                temp2.put("end_time", top.get(i).occurences.get(iKey).getEndTime());
                occur.put(temp2);
            }
            temp1.put("occurences", occur);
            keyWord.put(temp1);
        }
        results.put("key_words", keyWord);
        json.put("results", results);
        return json.toString();

    }

    public ArrayList<KeyWord> kw(){
        return kw;
    }

    public String getStr(){
        return str;
    }

    public ArrayList<KeyWord> topWords(){
        if(kw.size() < 50){
            return kw;
        }
        ArrayList<KeyWord> testBunny = new ArrayList();
        for(int i = 0; i < 50; i++){
            testBunny.add(kw.get(i));
        }
        return testBunny;

    }
}