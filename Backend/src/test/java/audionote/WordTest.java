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
}
