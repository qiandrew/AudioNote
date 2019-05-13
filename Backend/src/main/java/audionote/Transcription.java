package audionote;
import java.util.*;
import org.json.*;

public class Transcription{
    private String str;
    private ArrayList<Word> transcript = new ArrayList<Word>();
    private ArrayList<KeyWord> kw = new ArrayList<KeyWord>();

    public Transcription(String s){
        str = s;
        JSONObject obj = new JSONObject(str);
        JSONArray ja = (JSONArray) obj.get("items");
        Iterator i = ja.iterator();
        while(i.hasNext()){
            JSONObject i2 = (JSONObject) i.next();
            JSONArray ja2 = (JSONArray) i2.get("alternatives");
            JSONObject prim = (JSONObject) ja2.getJSONObject(0);
            transcript.add(new Word(prim.getString("content"), Double.parseDouble(prim.getString("confidence")),Double.parseDouble(i2.getString("start_time")),Double.parseDouble(i2.getString("end_time"))));
        }
        Analysis a = new Analysis(transcript);
        kw = a.getList();
    }

    public ArrayList<KeyWord> kw(){
        return kw;
    }
}