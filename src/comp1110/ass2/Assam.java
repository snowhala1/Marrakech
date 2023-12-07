package comp1110.ass2;

/**
 * Authorship
 * Heng Sun u7611510
 * Runyao Wang u6812566
 */
public class Assam {
    private int x;
    private int y;
    Direction dir;
    private static final int assamStringLength = 4;//the length of assam string
    public static final int assamDigit = 0;//the digit representing this string is a assam string
    public static final int xCoordinateDigit = 1;//the x coordinate digit of assam string
    public static final int yCoordinateDigit = 2;//the y coordinate digit of assam string
    public static final int orientationDigit = 3;//the orientation digit of assam string

    public Assam(int x, int y, Direction dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public Assam(String string){
        if (string.charAt(assamDigit)=='A'&&string.length()==assamStringLength)
        {
            this.x = Character.getNumericValue(string.charAt(xCoordinateDigit));
            this.y = Character.getNumericValue(string.charAt(yCoordinateDigit));
            this.dir = Direction.valueOf(String.valueOf(string.charAt(orientationDigit)));
        }
        else
            throw new RuntimeException("Invalid Assam String");
    }


    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public Direction getDirection() {
        return this.dir;
    }

    public void changeDirection(int rotation){
        if (rotation == 90) {
            switch (this.dir) {
                case N:
                    this.dir = Direction.E;
                    break;
                case E:
                    this.dir = Direction.S;
                    break;
                case S:
                    this.dir = Direction.W;
                    break;
                case W:
                    this.dir = Direction.N;
                    break;
            }
        } else if (rotation == 270) {
            switch (this.dir) {
                case N:
                    this.dir = Direction.W;
                    break;
                case E:
                    this.dir = Direction.N;
                    break;
                case S:
                    this.dir = Direction.E;
                    break;
                case W:
                    this.dir = Direction.S;
                    break;
            }
        }
    }

    public void move(int steps){
        if (steps < 1 || steps > 4) {
            throw new RuntimeException("Invalid steps");
        }
        for (; steps > 0; steps--) {
            switch (dir) {
                case N:
                    this.y--;
                    break;
                case E:
                    this.x++;
                    break;
                case S:
                    this.y++;
                    break;
                case W:
                    this.x--;
                    break;
            }

            if (this.x < 0) {
                if (this.y == Board.row - 1) {
                    dir = Direction.N;
                } else {
                    dir = Direction.E;
                    if (this.y % 2 == 0) {
                        this.y++;
                    } else {
                        this.y--;
                    }
                }
                this.x = 0;
            }

            if (this.x >= Board.column) {
                if (this.y == 0) {
                    dir = Direction.S;
                } else {
                    dir = Direction.W;
                    if (this.y % 2 == 0) {
                        this.y--;
                    } else {
                        this.y++;
                    }
                }
                this.x = Board.column - 1;
            }

            if (this.y < 0) {
                if (this.x == Board.column - 1) {
                    dir = Direction.W;
                } else {
                    dir = Direction.S;
                    if (this.x % 2 == 0) {
                        this.x++;
                    } else {
                        this.x--;
                    }
                }
                this.y = 0;
            }

            if (this.y >= Board.row) {
                if (this.x == 0) {
                    dir = Direction.E;
                } else {
                    dir = Direction.N;
                    if (this.x % 2 == 0) {
                        this.x--;
                    } else {
                        this.x++;
                    }
                }
                this.y = Board.row - 1;
            }
        }
    }

    public String toString(){
        return "A" +
                x +
                y +
                dir;
    }
}
