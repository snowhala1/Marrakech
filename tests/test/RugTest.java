/**
 * Authorship
 * Yanbai Jin u7706930
 */
package test;

import comp1110.ass2.Color;
import comp1110.ass2.Rug;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
public class RugTest {
    private Rug rug;

    @BeforeEach
    public void setUp() {
        rug = new Rug(0, 0, 0, 1, Color.r);
    }

    @Test
    public void testGetAbbrRugString() {
        assertEquals("r03", rug.getAbbrRugString());
    }

    @Test
    public void testConstructorAndGetters() {
        assertEquals(0, rug.getX1());
        assertEquals(0, rug.getY1());
        assertEquals(0, rug.getX2());
        assertEquals(1, rug.getY2());
        assertEquals(Color.r, rug.getColor());
    }

    @Test
    public void testToString() {
        assertEquals("r010001", rug.toString());
    }



}
