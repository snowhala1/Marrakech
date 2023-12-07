/**
 * Authorship
 * Runyao Wang u6812566
 */
package test;

import comp1110.ass2.Marrakech;
import comp1110.ass2.gui.Game;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class GameTest {
    @Test
    public void testCreateGameFromString() {
        // Test the default constructor
        String expected = "Pc03410iPy02210iPp02610iPr03610iA65EBn00n00n00c09c09n00n00r07r04c01r08c02r00r00r07r04n00r08n00r02p01p05n00n00c07c07r02p01p05y08y08n00n00y00y00n00c08n00c00n00p08n00n00n00n00c00n00p08n00";
        Marrakech.createGame(expected);
        assertEquals(expected, Marrakech.getGameString());
    }

    @Test
    public void testGetActivePlayerNo() {
        // Test the default constructor
        String expected = "Pc03410iPy02210oPp02610iPr03610iA65EBn00n00n00c09c09n00n00r07r04c01r08c02r00r00r07r04n00r08n00r02p01p05n00n00c07c07r02p01p05y08y08n00n00y00y00n00c08n00c00n00p08n00n00n00n00c00n00p08n00";
        Marrakech.createGame(expected);
        assertEquals(3, Marrakech.getActivePlayerNo());
    }

    @Test
    public void testNextPlayer() {
        String expected = "Pc03410iPy02210iPp02610iPr03610iA65EBn00n00n00c09c09n00n00r07r04c01r08c02r00r00r07r04n00r08n00r02p01p05n00n00c07c07r02p01p05y08y08n00n00y00y00n00c08n00c00n00p08n00n00n00n00c00n00p08n00";
        Marrakech.createGame(expected);
        assertEquals(Marrakech.getNextPlayer('y'), 'p');
        assertEquals(Marrakech.getNextPlayer('r'), 'c');
    }

    // test isAIplayer
    @Test
    public void testIsAIplayer() {
        Game game = new Game();
        assertFalse(game.isAIplayer('c', 0, 2));
        assertFalse(game.isAIplayer('c', 1, 2));
        assertTrue(game.isAIplayer('y', 1, 2));
        assertFalse(game.isAIplayer('y', 1, 3));
        assertTrue(game.isAIplayer('p', 1, 3));
        assertTrue(game.isAIplayer('y', 2, 3));
    }

}

