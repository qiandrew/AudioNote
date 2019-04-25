import static org.junit.Assert.*;
import org.junit.Test;
import audionote.Word;

//comment to commit
public class WordTest{
    @Test
    public void testWord1() {
        Word w = new Word("apple", 1, 0, 1);
        assertEquals("error in word:", "apple", w.getWord());
        assertEquals("error in confidence:", 1, w.getConfidence());
        assertEquals("error in start:", 0, w.getStartTime());
        assertEquals("error in end:", 1, w.getEndTime());
        assertEquals("error in total:", 1, w.getTotalTime());
    }
}
