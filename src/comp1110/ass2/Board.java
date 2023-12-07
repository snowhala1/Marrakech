package comp1110.ass2;

/**
 * Authorship
 * Heng Sun u7611510
 * Runyao Wang u6812566
 */
public class Board {
    public static final int row = 7;
    public static final int column = 7;
    public static String[][] board = new String[row][column];
    public Board() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                board[i][j] = "n00";
            }
        }
    }
    //initial board with board string
    public Board(String boardString){
        if (boardString.charAt(0)!='B'||boardString.length()!=148)
            throw new RuntimeException("Invalid board string");
        else {
            boardString = boardString.substring(1);
            int count = 0;
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    board[i][j]= boardString.substring(count*3,(count+1)*3);
                    count+=1;
                }
            }
        }
    }

    /**
     *
     * @param row the coordinate of row
     * @param column the coordinate of column
     * @param abbrRugString the abbreviation of rug string
     */
    public void setBoard(int row,int column,String abbrRugString){
        board[row][column] = abbrRugString;
    }

    // Add a rug to the board from rug string
    public void placeRug(String rug){
        String abbrRug = rug.substring(0,3);
        board[Character.getNumericValue(rug.charAt(3))][Character.getNumericValue(rug.charAt(4))] = abbrRug;
        board[Character.getNumericValue(rug.charAt(5))][Character.getNumericValue(rug.charAt(6))] = abbrRug;
    }

    public char getColor(int i, int j) {
        return board[i][j].charAt(0);
    }

    @Override
    public String toString() {
        StringBuilder boardString= new StringBuilder("B");
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                boardString.append(board[i][j]);
            }
        }
        return boardString.toString();
    }
}
