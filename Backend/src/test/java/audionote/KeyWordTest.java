import static org.junit.Assert.*;
import org.junit.Test;
import audionote.KeyWord;
import audionote.Word;

//comment to commit
public class KeyWordTest{
    @Test
    public void testKeyWord1() {
        KeyWord tw = new KeyWord("apple");
        tw.addOccurence(0, 1.5, 0.7);
        tw.addOccurence(4.2, 6.3, 0.8);
        Word w1 = new Word("apple", 0.5, 7, 6.4);
        tw.addOccurence(w1);
        assertEquals("error in KeyWord:", "apple", tw.getWord());
        assertEquals(tw.getFrequency(), 3);
        assertEquals(tw.getFirstTime(), 0, 0.001);
    }
    @Test
    public void testKeyWord2() {
        KeyWord tw = new KeyWord("ooga");
        assertEquals("error in KeyWord:", "ooga", tw.getWord());
        assertEquals(tw.getFrequency(), 0);
        assertEquals(tw.getFirstTime(), 0, 0.001);
    }
}
