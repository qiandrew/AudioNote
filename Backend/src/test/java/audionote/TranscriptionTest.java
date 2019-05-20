package audionote;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.Test;
import audionote.Transcription;
import audionote.Analysis;
import audionote.KeyWord;
import audionote.Word;

public class TranscriptionTest{
    @Test
    public void testTranscription1() {
        Transcription t = new Transcription("{\"jobName\":\"sdfsdfsd\",\"accountId\":\"581659636497\",\"results\":{\"transcripts\":[{\"transcript\":\"apple pear apple\"}],\"items\":[{\"start_time\":\"0.0\",\"end_time\":\"0.28\",\"alternatives\":[{\"confidence\":\"0.9997\",\"content\":\"apple\"}],\"type\":\"pronunciation\"},{\"start_time\":\"0.28\",\"end_time\":\"0.79\",\"alternatives\":[{\"confidence\":\"0.9484\",\"content\":\"pear\"}],\"type\":\"pronunciation\"},{\"start_time\":\"0.79\",\"end_time\":\"0.94\",\"alternatives\":[{\"confidence\":\"1.0000\",\"content\":\"apple\"}],\"type\":\"pronunciation\"}],\"status\":\"COMPLETED\"}}");
        ArrayList<KeyWord> k = t.kw();
        assertEquals("error in word stored in 0", "pear", k.get(0).getWord());
        assertEquals(1, k.get(0).getFrequency());
    }

    public void testTranscription2(){
        Transcription t = new Transcription("{\"jobName\":\"sdfsdfsd\",\"accountId\":\"581659636497\",\"results\":{\"transcripts\":[{\"transcript\":\"pear pear apple apple\"}],\"items\":[{\"start_time\":\"0.0\",\"end_time\":\"0.28\",\"alternatives\":[{\"confidence\":\"0.9997\",\"content\":\"pear\"}],\"type\":\"pronunciation\"},{\"start_time\":\"0.28\",\"end_time\":\"0.79\",\"alternatives\":[{\"confidence\":\"0.9484\",\"content\":\"pear\"}],\"type\":\"pronunciation\"},{\"start_time\":\"0.79\",\"end_time\":\"0.94\",\"alternatives\":[{\"confidence\":\"1.0000\",\"content\":\"apple\"},{\"start_time\":\"0.94\",\"end_time\":\"1.24\",\"alternatives\":[{\"confidence\":\"1.0000\",\"content\":\"apple\"}],\"type\":\"pronunciation\"}],\"status\":\"COMPLETED\"}}");
        ArrayList<KeyWord> k = t.kw();
        assertEquals("error in word stored in 0", "pear", k.get(0).getWord());
        assertEquals(2, k.get(0).getFrequency());
    }

}