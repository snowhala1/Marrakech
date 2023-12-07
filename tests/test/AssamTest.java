/**
 * Authorship
 * Runyao Wang u6812566
 */
package test;
import comp1110.ass2.Assam;
import comp1110.ass2.Color;
import comp1110.ass2.Direction;
import comp1110.ass2.Player;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class AssamTest {
    private Assam a;
    private final String aStr = "A11N";

    @BeforeEach
    public void setUp() {
        a = new Assam(aStr);
    }

    @Test
    public void testDefaultConstructor() {
        // Test the default constructor
        assertEquals(1, a.getX());
        assertEquals(1, a.getY());
        assertEquals(Direction.N, a.getDirection());
    }

    @Test
    public void testInvalidMove() {
        assertThrows(RuntimeException.class, () -> {
            a.move(0);
        });
        assertThrows(RuntimeException.class, () -> {
            a.move(10);
        });
    }
    @Test
    public void testToString() {
        // Test the default constructor
        assertEquals(aStr, a.toString());
    }

    // Todo: add more tests
}
