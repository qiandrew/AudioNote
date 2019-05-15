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
        str = s;
        JsonElement jElement = new JsonParser().parse(str);
        JsonObject jObject = jElement.getAsJsonObject();
        jObject = jObject.getAsJsonObject("results");
        JsonArray j1 = jObject.getAsJsonArray("items");
        for(int i = 0; i < j1.size(); i++){
            jObject = j1.get(i).getAsJsonObject();
            JsonArray j2 = jObject.getAsJsonArray("alternatives");
            jObject = j2.get(0).getAsJsonObject();
            transcript.add(new Word(jObject.get("content").getAsString(), jObject.get("confience").getAsDouble(), jObject.get("start_time").getAsDouble(), jObject.get("end_time").getAsDouble()));
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
}