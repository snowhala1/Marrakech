/**
 * Authorship
 * Heng Sun u7611510
 */
package test;

import comp1110.ass2.Board;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class BoardTest {
    private Board board;

    @BeforeEach
    public void setUp() {
        board = new Board();
    }

    @Test
    public void testDefaultConstructor() {
        // Test the default constructor
        String expected = "Bn00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00";
        assertEquals(expected, board.toString());
    }

    @Test
    public void testStringConstructor() {
        // Test the constructor that takes a boardString
        String boardString = "By02n00n00y07p11c07r06c20r18r18y12c17c17r06c20y05c16y16y15y17n00p18c10y13y16y06y17n00y00c00y08y04y06p06n00n00p04r03p21n00r20y03n00p04n00p21n00r20p17";
        Board newBoard = new Board(boardString);
        assertEquals(boardString, newBoard.toString());
    }

    @Test
    public void testSetBoard() {
        // Test the setBoard method
        board.setBoard(2, 3, "ABC");
        String expected = "Bn00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00ABCn00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00";
        assertEquals(expected, board.toString());
    }
}

