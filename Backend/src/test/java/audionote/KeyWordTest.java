import static org.junit.Assert.*;
import org.junit.Test;
import audionote.KeyWord;
import audionote.Word;

//comment to commit
public class KeyWordTest{
    @Test
    public void testKeyWord1() {
        KeyWord tw = new KeyWord("apple");
        Word w1 = new Word("apple", 0.7, 0, 1.5);
        Word w2 = new Word("apple", 0.8, 4.2, 6.3);
        Word w3 = new Word("apple", 0.5, 7, 6.4);
        tw.addOccurence(w1);
        tw.addOccurence(w2);
        tw.addOccurence(w3);
        assertEquals("error in KeyWord:", "apple", tw.getWord());
        assertEquals(tw.getFrequency(), 3);
        assertEquals(tw.getFirstTime(), 0, 0.001);
    }
    public void testKeyWord2() {
        KeyWord tw = new KeyWord("ooga");
        assertEquals("error in KeyWord:", "ooga", tw.getWord());
        assertEquals(tw.getFrequency(), 0);
        assertEquals(tw.getFirstTime(), 0);
    }
}
