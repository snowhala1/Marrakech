package comp1110.ass2;

/**
 * Authorship
 * Heng Sun u7611510
 * Runyao Wang u6812566
 */
public class Player {
    private final Color color;
    private int dirhams;//the currency in use in this game
    private int rugAvailable; //Each player starts the game with 15 rugs
    private char inGame;

    public Player(Color color) {
        this.color = color;
        this.dirhams = 30;
        this.rugAvailable = 15;
        this.inGame = 'i';
    }
    public Player (String s){
        if (s.charAt(0) != 'P') {
            throw new RuntimeException("Invalid player string");
        }
        this.color = Color.valueOf(String.valueOf(s.charAt(1)));
        this.dirhams = Integer.parseInt(s.substring(2,5));
        this.rugAvailable = Integer.parseInt(s.substring(5,7));
        this.inGame = s.charAt(7);;
    }
    public Color getColor() {
        return color;
    }

    public int getDirhams() {
        return dirhams;
    }

    public char getInGame() {
        return inGame;
    }

    public void quitGame() {
        this.inGame = 'o';
    }

    public int getRugAvailable() {
        return rugAvailable;
    }

    public void placeRug() {
        rugAvailable--;
    }
    /**
     *
     * @param anotherPlayer the player you need to pay
     */
    public void payment(Player anotherPlayer,int amount){
        dirhams-=amount;
        anotherPlayer.dirhams+=amount;
    }

    @Override
    public String toString() {
        return  "P"+
                color+
                String.format("%03d", dirhams)+
                String.format("%02d", rugAvailable)+
                inGame;
    }
}
