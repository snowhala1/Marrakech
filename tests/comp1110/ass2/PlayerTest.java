package comp1110.ass2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
public class PlayerTest {
    @Test
    public void playerStringTest() {
        String s1 = "Pc01010i";
        Player p1 = new Player(s1);
        Assertions.assertEquals(s1, p1.toString());
    }
}
