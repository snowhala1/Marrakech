/**
 * Authorship
 * Runyao Wang u6812566
 */
package test;

import static comp1110.ass2.Helper.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import javafx.scene.paint.Color;

public class HelperTest {
    @Test
    public void testCharToColor(){
        assertEquals(charToColor('c'), Color.CYAN);
        assertEquals(charToColor('y'), Color.YELLOW);
        assertThrows(IllegalArgumentException.class, () -> {
            charToColor('s');
        });
    }
}
