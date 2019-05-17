package audionote;
import java.lang.*;
import java.util.*;
import org.json.*;
import com.google.gson.*;

public class Transcription{
    private String str;
    private ArrayList<Word> transcript = new ArrayList<Word>();
    private ArrayList<KeyWord> kw = new ArrayList<KeyWord>();

    public Transcription(String s){
        JsonElement jElement = new JsonParser().parse(s);
        JsonObject jObject = jElement.getAsJsonObject();
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
        /*
        JSONObject obj0 = new JSONObject(str);
        JSONObject obj = (JSONObject) obj0.get("results");
        JSONArray ja = (JSONArray) obj.get("items");
        //Iterator i = ja.iterator();
        while(i.hasNext()){
            JSONObject i2 = (JSONObject) i.next();
            JSONArray ja2 = (JSONArray) i2.get("alternatives");
            JSONObject prim = (JSONObject) ja2.getJSONObject(0);
            transcript.add(new Word(prim.getString("content"), Double.parseDouble(prim.getString("confidence")),Double.parseDouble(i2.getString("start_time")),Double.parseDouble(i2.getString("end_time"))));
        }
        for(int i = 0; i < ja.length(); i++){
            JSONObject i2 = ja.getJSONObject(i);
            }
            JSONArray ja2 = (JSONArray) i2.get("alternatives");
            JSONObject prim = (JSONObject) ja2.getJSONObject(0);
            transcript.add(new Word(prim.getString("content"), Double.parseDouble(prim.getString("confidence")),Double.parseDouble(i2.getString("start_time")),Double.parseDouble(i2.getString("end_time"))));
        }

        Analysis a = new Analysis(transcript);
        kw = a.getList();
        */
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