/**
 * Authorship
 * Heng Sun u7611510
 */
package test;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import comp1110.ass2.Color;
import comp1110.ass2.Player;


public class PlayerTest {
    private Player player;

    @BeforeEach
    public void setUp() {
        player = new Player(Color.c);
    }

    @Test
    public void testDefaultConstructor() {
        // Test the default constructor
        assertEquals(Color.c, player.getColor());
        assertEquals(30, player.getDirhams());
        assertEquals(15, player.getRugAvailable());
        assertEquals('i', player.getInGame());
    }

    @Test
    public void testStringConstructor() {
        // Test the constructor that takes a playerString
        String playerString = "Pr00803i";
        Player newPlayer = new Player(playerString);
        assertEquals(Color.r, newPlayer.getColor());
        assertEquals(8, newPlayer.getDirhams());
        assertEquals(3, newPlayer.getRugAvailable());
        assertEquals('i', newPlayer.getInGame());
    }

    @Test
    public void testQuitGame() {
        // Test the quitGame method
        player.quitGame();
        assertEquals('o', player.getInGame());
    }

    @Test
    public void testPlaceRug() {
        // Test the placeRug method
        player.placeRug();
        assertEquals(14, player.getRugAvailable());
    }


    @Test
    public void testInvalidStringConstructor() {
        assertThrows(RuntimeException.class, () -> {
            String invalidString = "InvalidString";
            Player newPlayer = new Player(invalidString);
        });
        // Test the constructor with an invalid playerString

    }

    @Test
    public void testInvalidPlayerStringColor() {
        // Test the constructor with an invalid color in playerString
        assertThrows(RuntimeException.class, () -> {
            String invalidString = "PZ003150i";
            Player newPlayer = new Player(invalidString);
        });
    }
}
