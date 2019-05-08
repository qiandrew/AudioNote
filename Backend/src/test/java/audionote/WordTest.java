package audionote;

import static org.junit.Assert.*;
import org.junit.Test;
import audionote.Word;

//comment to commit
public class WordTest{
    @Test
    public void testWord1() {
        Word w = new Word("apple", 1, 0, 1);
        assertEquals("error in word:", "apple", w.getWord());
        assertEquals(w.getConfidence(), 1, 0.0001);
        assertEquals(w.getStartTime(), 0, 0.0001);
        assertEquals(w.getEndTime(), 1, 0.0001);
        assertEquals(w.getTotalTime(), 1, 0.0001);
    }
    public void testWord2() {
        Word w = new Word("sTrAwbErRy", 0.333, 1.34, 2.21);
        assertEquals("error in word:", "sTrAwbErRy", w.getWord());
        assertEquals(w.getConfidence(), 0.333, 0.0001);
        assertEquals(w.getStartTime(), 1.34, 0.0001);
        assertEquals(w.getEndTime(), 2.21, 0.0001);
        assertEquals(w.getTotalTime(), 0.87, 0.0001);
    }
}
