import static org.junit.Assert.*;
import org.junit.Test;
import audionote.sum;

public class sumTest{
    @Test
    public void testSumPass() {
        assertEquals("error in sum()", 3, sum.sum(1,2));
    }
}