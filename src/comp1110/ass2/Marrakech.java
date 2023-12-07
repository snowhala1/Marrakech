package comp1110.ass2;

import java.util.ArrayList;
import java.util.Random;

import static comp1110.ass2.Helper.*;

/**
 * Authorship
 * Heng Sun u7611510
 * Runyao Wang u6812566
 * Yanbai Jin u7706930
 */
public class Marrakech {
    public static Board board;
    public static Assam assam;
    public static ArrayList<Player> playerList = new ArrayList<>();
    public static ArrayList<Rug> rugList = new ArrayList<>();

    public Marrakech() {
        board = new Board();
        assam = new Assam(3, 3, Direction.N);
        playerList = new ArrayList<>();
        rugList = new ArrayList<>();
    }


    public static void createGame(String game) {
        createPlayers(game.split("A")[0]);
        assam = new Assam("A" + game.split("A")[1].split("B")[0]);
        String boardStr = game.split("B")[1];
        board = new Board("B" + game.split("B")[1]);
    }

    public static void updateBoard(String game) {
        board = new Board("B" + game.split("B")[1]);
    }

    public static void createPlayers(String players) {
        playerList.clear();
        for (int i = 0; i < players.length() / 8; i = i + 1) {
            Player p = new Player(players.substring(i * 8, (i + 1) * 8));
            playerList.add(p);
        }
    }

    public static Player getPlayerFromColor(char color) {
        for (Player p : playerList) {
            if (p.getColor().getColor() == color) {
                return p;
            }
        }
        return null;
    }

    // get no of players in game
    public static int getActivePlayerNo() {
        int noOfPlayers = 0;
        for (Player p : playerList) {
            if (p.getInGame() == 'i') {
                noOfPlayers++;
            }
        }
        return noOfPlayers;
    }
    /**
     * Determine whether a rug String is valid.
     * For this method, you need to determine whether the rug String is valid, but do not need to determine whether it
     * can be placed on the board (you will determine that in Task 10 ). A rug is valid if and only if all the following
     * conditions apply:
     *  - The String is 7 characters long
     *  - The first character in the String corresponds to the colour character of a player present in the game
     *  - The next two characters represent a 2-digit ID number
     *  - The next 4 characters represent coordinates that are on the board
     *  - The combination of that ID number and colour is unique
     * To clarify this last point, if a rug has the same ID as a rug on the board, but a different colour to that rug,
     * then it may still be valid. Obviously multiple rugs are allowed to have the same colour as well so long as they
     * do not share an ID. So, if we already have the rug c013343 on the board, then we can have the following rugs
     *  - c023343 (Shares the colour but not the ID)
     *  - y013343 (Shares the ID but not the colour)
     * But you cannot have c014445, because this has the same colour and ID as a rug on the board already.
     * @param gameString A String representing the current state of the game as per the README
     * @param rug A String representing the rug you are checking
     * @return true if the rug is valid, and false otherwise.
     */
    public static boolean isRugValid(String gameString, String rug) {
        // Can't create rug object here, we don't know whether the rug string is valid
        // check color is valid and the player is in game
        String pStr = gameString.substring(0, gameString.indexOf('A'));
        if (rug.length() != 7) {
            return false;
        }
        char color = rug.charAt(0);
        if (!pStr.contains(String.valueOf(color)) ||
                ((color != 'c') && (color != 'r') && (color != 'y') && (color != 'p'))){
            return false;
        }
        // check id and coordinates
        try{
            int id = Integer.parseInt(rug.substring(1,3));
            int x1 = Integer.parseInt(rug.substring(3,4));
            int y1 = Integer.parseInt(rug.substring(4,5));
            int x2 = Integer.parseInt(rug.substring(5,6));
            int y2 = Integer.parseInt(rug.substring(6,7));
            if ((x1 < 0 || x1 > 6) ||
                (x2 < 0 || x2 > 6) ||
                (y1 < 0 || y1 > 6) ||
                (y2 < 0 || y2 > 6)){
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        // Check color+id is unique
        String cid = rug.substring(0,3);
        String boardStr = gameString.substring(gameString.indexOf('B'));
        if (boardStr.contains(cid)) {
            return false;
        }
        return true;
    }

    /**
     * Roll the special Marrakech die and return the result.
     * Note that the die in Marrakech is not a regular 6-sided die, since there
     * are no faces that show 5 or 6, and instead 2 faces that show 2 and 3. That
     * is, of the 6 faces
     *  - One shows 1
     *  - Two show 2
     *  - Two show 3
     *  - One shows 4
     * As such, in order to get full marks for this task, you will need to implement
     * a die where the distribution of results from 1 to 4 is not even, with a 2 or 3
     * being twice as likely to be returned as a 1 or 4.
     * @return The result of the roll of the die meeting the criteria above
     */
    public static int rollDie() {
        Random rand = new Random();
        int randNum = rand.nextInt(6);
        int[] prob = {1, 2, 2, 3, 3, 4};
        return prob[randNum];
    }

    /**
     * Determine whether a game of Marrakech is over
     * Recall from the README that a game of Marrakech is over if a Player is about to enter the rotation phase of their
     * turn, but no longer has any rugs. Note that we do not encode in the game state String whose turn it is, so you
     * will have to think about how to use the information we do encode to determine whether a game is over or not.
     * @param currentGame A String representation of the current state of the game.
     * @return true if the game is over, or false otherwise.
     */
    public static boolean isGameOver(String currentGame) {
        createGame(currentGame);
        ArrayList<String> players = new ArrayList<>();
        for (int i = 0; i <= currentGame.length() - 8; i++) {
            if (currentGame.charAt(i) == 'P') {
                String substring = currentGame.substring(i, i + 8);
                players.add(substring);
            }
        }
        int quitGameNumber = 0;
        for (String player: players) {
            if (player.charAt(7) == 'o') {
                quitGameNumber++;
                continue;
            }
            if (player.charAt(5) == '0' && player.charAt(6) == '0' && player.charAt(7) == 'i') {
                quitGameNumber++;
            }
        }
        return quitGameNumber==playerList.size();


    }

    /**
     * Implement Assam's rotation.
     * Recall that Assam may only be rotated left or right, or left alone -- he cannot be rotated a full 180 degrees.
     * For example, if he is currently facing North (towards the top of the board), then he could be rotated to face
     * East or West, but not South. Assam can also only be rotated in 90 degree increments.
     * If the requested rotation is illegal, you should return Assam's current state unchanged.
     * @param currentAssam A String representing Assam's current state
     * @param rotation The requested rotation, in degrees. This degree reading is relative to the direction Assam
     *                 is currently facing, so a value of 0 for this argument will keep Assam facing in his
     *                 current orientation, 90 would be turning him to the right, etc.
     * @return A String representing Assam's state after the rotation, or the input currentAssam if the requested
     * rotation is illegal.
     */
    public static String rotateAssam(String currentAssam, int rotation) {
//         The only valid degrees are 90 and 270
        Assam newAssam = new Assam(currentAssam);
        newAssam.changeDirection(rotation);
        assam = newAssam;
        return assam.toString();
    }

    /**
     * Determine whether a potential new placement is valid (i.e that it describes a legal way to place a rug).
     * There are a number of rules which apply to potential new placements, which are detailed in the README but to
     * reiterate here:
     *   1. A new rug must have one edge adjacent to Assam (not counting diagonals)
     *   2. A new rug must not completely cover another rug. It is legal to partially cover an already placed rug, but
     *      the new rug must not cover the entirety of another rug that's already on the board.
     * @param gameState A game string representing the current state of the game
     * @param rug A rug string representing the candidate rug which you must check the validity of.
     * @return true if the placement is valid, and false otherwise.
     */
    public static boolean isPlacementValid(String gameState, String rug) {
        if (!isRugValid(gameState, rug)) {
            return false;
        }
        String assamAndBoard = gameState.split("A")[1];
        String assam = assamAndBoard.substring(0, 3);
        String boardState = assamAndBoard.substring(4);
        int assamPosX = Character.getNumericValue(assam.charAt(0));
        int assamPosY = Character.getNumericValue(assam.charAt(1));

        int x1 = Integer.parseInt(rug.substring(3, 4));
        int y1 = Integer.parseInt(rug.substring(4, 5));
        int x2 = Integer.parseInt(rug.substring(5, 6));
        int y2 = Integer.parseInt(rug.substring(6, 7));

        boolean defaultValidation = false;

        if (x1 < 0 || x1 > 6 || x2 < 0 || x2 > 6 || y1 < 0 || y1 > 6 || y2 < 0 || y2 > 6) {
            return false;
        }

        // Check whether the new rug must have one edge adjacent to Assam
        if (x1 == assamPosX && (Math.abs(assamPosY - y1) == 1)) {
            if (x2 != assamPosX || y2 != assamPosY) {
                    defaultValidation = true;
            }
        }
        else if (y1 == assamPosY && (Math.abs(assamPosX - x1) == 1)) {
            if (x2 != assamPosX || y2 != assamPosY) {
                defaultValidation = true;
            }
        }
        if (x2 == assamPosX && (Math.abs(assamPosY - y2) == 1)) {
            if (x1 != assamPosX || y1 != assamPosY) {
                defaultValidation = true;
            }
        }
        else if (y2 == assamPosY && (Math.abs(assamPosX - x2) == 1)) {
            if (x1 != assamPosX || y1 != assamPosY) {
                defaultValidation = true;
            }
        }

        char boardColor1 = boardState.charAt(3 * (x1 * 7 + y1));
        char boardColor2 = boardState.charAt(3 * (x2 * 7 + y2));
        String boardID1 = boardState.substring(3*(x1 * 7 + y1) + 1, 3*(x1 * 7 + y1) + 3);
        String boardID2 = boardState.substring(3*(x2 * 7 + y2) + 1, 3*(x2 * 7 + y2) + 3);

        if (boardColor1 != 'n' && boardColor2 != 'n') {
            if (boardColor1 == boardColor2 && boardID1.equals(boardID2)) {
                defaultValidation = false; // Rug placement completely covers another rug
            }
        }

        return defaultValidation;
    }

    /**
     * Determine the amount of payment required should another player land on a square.
     * For this method, you may assume that Assam has just landed on the square he is currently placed on, and that
     * the player who last moved Assam is not the player who owns the rug landed on (if there is a rug on his current
     * square). Recall that the payment owed to the owner of the rug is equal to the number of connected squares showing
     * on the board that are of that colour. Similarly to the placement rules, two squares are only connected if they
     * share an entire edge -- diagonals do not count.
     * @param gameString A String representation of the current state of the game.
     * @return The amount of payment due, as an integer.
     */

    public static int getPaymentAmount(String gameString) {
        int amount = 0;
        String assam = "";
        String board = "";
        String[][] boardArray = new String[7][7];
        int row, column;

        // Get row, column, and board string from game string
        int assamIndex = gameString.indexOf('A');
        int boardIndex = gameString.indexOf('B');
        assam = gameString.substring(assamIndex, assamIndex + 4);
        board = gameString.substring(boardIndex + 1);

        row = Integer.parseInt(String.valueOf(assam.charAt(1)));
        column = Integer.parseInt(String.valueOf(assam.charAt(2)));

        // Convert string to 2d array
        int index = 0;
        for (int boardRow = 0; boardRow < 7; boardRow++) {
            for (int boardColumn = 0; boardColumn < 7; boardColumn++) {
                boardArray[boardRow][boardColumn] = board.substring(index, index + 3);
                index += 3;
            }
        }

        if (boardArray[row][column].charAt(0) == 'n') {
            return 0;
        } else {
            char color = boardArray[row][column].charAt(0);
            boolean[][] visited = new boolean[7][7]; // To avoid infinite loop
            amount = traverse(boardArray, row, column, color, 0, visited);
        }
        return amount;
    }

    /**
     * specify the amount we need to pay
     * @param boardArray the 2d board abbr rug array
     * @param row current row of boardArray
     * @param column current column of boardArray
     * @param color the color Assam step on
     * @param amount the amount we need to pay
     * @param visited whether we count the current location or not
     * @return the amount we need to pay
     */
    public static int traverse(String[][] boardArray, int row, int column, char color, int amount, boolean[][] visited) {
        if (row < 0 || row >= 7 || column < 0 || column >= 7 || visited[row][column] || boardArray[row][column].charAt(0) != color) {
            return amount;
        }

        visited[row][column] = true;
        amount++;

        amount = traverse(boardArray, row + 1, column, color, amount, visited);
        amount = traverse(boardArray, row - 1, column, color, amount, visited);
        amount = traverse(boardArray, row, column + 1, color, amount, visited);
        amount = traverse(boardArray, row, column - 1, color, amount, visited);

        return amount;
    }


    /**
     * Determine the winner of a game of Marrakech.
     * For this task, you will be provided with a game state string and have to return a char representing the colour
     * of the winner of the game. So for example if the cyan player is the winner, then you return 'c', if the red
     * player is the winner return 'r', etc...
     * If the game is not yet over, then you should return 'n'.
     * If the game is over, but is a tie, then you should return 't'.
     * Recall that a player's total score is the sum of their number of dirhams and the number of squares showing on the
     * board that are of their colour, and that a player who is out of the game cannot win. If multiple players have the
     * same total score, the player with the largest number of dirhams wins. If multiple players have the same total
     * score and number of dirhams, then the game is a tie.
     * @param gameState A String representation of the current state of the game
     * @return A char representing the winner of the game as described above.
     */
    public static char getWinner(String gameState) {
        if (!isGameOver(gameState)) {return 'n';}

        String assamAndBoard = gameState.split("A")[1];
        String playerStr = gameState.split("A")[0];
        String board = assamAndBoard.substring(4);
        // Count the squares of each color
        int scoreC = 0;
        int scoreY = 0;
        int scoreR = 0;
        int scoreP = 0;
        for (int i = 0; i < board.length(); i += 3) {
            String currentSquare = board.substring(i, i + 3);
            if (!currentSquare.equals("n00")) {
                int row = i / 3 / 7;
                int col = i / 3 % 7;
                char squareColor = currentSquare.charAt(0);
                if (squareColor == 'c') {
                    scoreC += 1;
                }
                if (squareColor == 'y') {
                    scoreY += 1;
                }
                if (squareColor == 'r') {
                    scoreR += 1;
                }
                if (squareColor == 'p') {
                    scoreP += 1;
                }
            }
        }
        // find the dirhams of each player
        int lastC = playerStr.lastIndexOf("Pc");
        int lastY = playerStr.lastIndexOf("Py");
        int lastR = playerStr.lastIndexOf("Pr");
        int lastP = playerStr.lastIndexOf("Pp");

        // Check each player exist
        int dirhamC = lastC != -1 ? Integer.parseInt(playerStr.substring(2 + lastC, 5 + lastC)) : -100;
        int dirhamY = lastY != -1 ? Integer.parseInt(playerStr.substring(2 + lastY, 5 + lastY)) : -100;
        int dirhamR = lastR != -1 ? Integer.parseInt(playerStr.substring(2 + lastR, 5 + lastR)) : -100;
        int dirhamP = lastP != -1 ? Integer.parseInt(playerStr.substring(2 + lastP, 5 + lastP)) : -100;

        int finalScoreC = scoreC + dirhamC;
        int finalScoreY = scoreY + dirhamY;
        int finalScoreR = scoreR + dirhamR;
        int finalScoreP = scoreP + dirhamP;
        int winnerScore = Math.max(finalScoreC, Math.max(finalScoreY, Math.max(finalScoreR, finalScoreP)));

        // Counting number of players with the maximum score
        int tieCount = 0;
        int maxDirham = -1;
        char winner = ' ';

        if (winnerScore == finalScoreC) { tieCount++; maxDirham = Math.max(maxDirham, dirhamC); winner = 'c'; }
        if (winnerScore == finalScoreY) { tieCount++; maxDirham = Math.max(maxDirham, dirhamY); winner = 'y'; }
        if (winnerScore == finalScoreR) { tieCount++; maxDirham = Math.max(maxDirham, dirhamR); winner = 'r'; }
        if (winnerScore == finalScoreP) { tieCount++; maxDirham = Math.max(maxDirham, dirhamP); winner = 'p'; }

        // Tie-breaking logic
        if (tieCount > 1) {
            // If more than 1 player has the maximum score and dirhams, then it's a tie
            if (moreThanTwoEqualN(dirhamC, dirhamP, dirhamR, dirhamY, maxDirham)) return 't';
            // If more than one player has the maximum score, consider dirhams
            if (maxDirham == dirhamC && finalScoreC == winnerScore) return 'c';
            if (maxDirham == dirhamY && finalScoreY == winnerScore) return 'y';
            if (maxDirham == dirhamR && finalScoreR == winnerScore) return 'r';
            if (maxDirham == dirhamP && finalScoreP == winnerScore) return 'p';

        }
        // No tie, return the player has the maximum score
        return winner;
    }

    /**
     * Implement Assam's movement.
     * Assam moves a number of squares equal to the die result, provided to you by the argument dieResult. Assam moves
     * in the direction he is currently facing. If part of Assam's movement results in him leaving the board, he moves
     * according to the tracks diagrammed in the assignment README, which should be studied carefully before attempting
     * this task. For this task, you are not required to do any checking that the die result is sensible, nor whether
     * the current Assam string is sensible either -- you may assume that both of these are valid.
     * @param currentAssam A string representation of Assam's current state.
     * @param dieResult The result of the die, which determines the number of squares Assam will move.
     * @return A String representing Assam's state after the movement.
     */
    public static String moveAssam(String currentAssam, int dieResult){
        Assam newAssam = new Assam(currentAssam);
        newAssam.move(dieResult);
        assam = newAssam;
        return assam.toString();
    }

    /**
     * Place a rug on the board
     * This method can be assumed to be called after Assam has been rotated and moved, i.e in the placement phase of
     * a turn. A rug may only be placed if it meets the conditions listed in the isPlacementValid task. If the rug
     * placement is valid, then you should return a new game string representing the board after the placement has
     * been completed. If the placement is invalid, then you should return the existing game unchanged.
     * @param currentGame A String representation of the current state of the game.
     * @param rug A String representation of the rug that is to be placed.
     * @return A new game string representing the game following the successful placement of this rug if it is valid,
     * or the input currentGame unchanged otherwise.
     */
    public static String makePlacement(String currentGame, String rug) {
        if (isPlacementValid(currentGame, rug) && isRugValid(currentGame, rug)){
            createGame(currentGame);
            board.placeRug(rug);
            Rug r = new Rug(rug);
            for (Player p : playerList){
                if (r.getColor() == p.getColor()){
                    p.placeRug();
                }
            }
            return getGameString();
        } else {
            return currentGame;
        }
    }


    public static String getGameString() {
        StringBuilder gameStringBuilder = new StringBuilder();
        for (Player player : playerList) {
            gameStringBuilder.append(player.toString());
        }
        gameStringBuilder.append(assam.toString());
        gameStringBuilder.append(board.toString());
        return gameStringBuilder.toString();
    }

    public static char getNextPlayer(char c) {
        Color color = Color.valueOf(String.valueOf(c));
        return color.next().getColor();
    }
}
