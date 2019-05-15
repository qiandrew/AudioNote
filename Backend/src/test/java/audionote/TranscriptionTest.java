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
        Transcription t = new Transcription("{\"jobName\":\"sdfsdfsd\",\"accountId\":\"581659636497\",\"results\":{\"transcripts\":[{\"transcript\":\"apple pear apple\"}],\"items\":[{\"start_time\":\"0.0\",\"end_time\":\"0.28\",\"alternatives\":[{\"confidence\":\"0.9997\",\"content\":\"apple\"}],\"type\":\"pronunciation\"},{\"start_time\":\"0.28\",\"end_time\":\"0.79\",\"alternatives\":[{\"confidence\":\"0.9484\",\"content\":\"pear\"}],\"type\":\"pronunciation\"},{\"start_time\":\"0.79\",\"end_time\":\"0.94\",\"alternatives\":[{\"confidence\":\"1.0000\",\"content\":\"apple\"}],\"type\":\"pronunciation\"},],\"status\":\"COMPLETED\"}}");
        ArrayList<KeyWord> k = t.kw();
        assertEquals("error in word stored in 0", "apple", k.get(0).getWord());
        assertEquals(k.get(0).getFrequency(), 2);
        assertEquals("error in word stored in 1", "pear", k.get(1).getWord());
        assertEquals(k.get(0).getFrequency(), 1);
    }
}