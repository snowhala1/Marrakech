package comp1110.ass2;

/**
 * Authorship
 * Heng Sun u7611510
 * Runyao Wang u6812566
 * Yanbai Jin u7706930
 */
public class Rug {
    private final Color color;
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    public static int rugId =0;//representing rug id
    public static final int rugStringLength = 7;//the length of rug string
    public static final int colorDigit = 0;//the color digit of rug string representing the color of the player placing this rug
    public static final int idTensDigit = 1;//the tens digit of id in rug string
    public static final int idOnesDigit = 2;//the ones digit of id in rug string
    public static final int x1CoordinateDigit = 3;//the x1 coordinate digit of rug string
    public static final int y1CoordinateDigit = 4;//the y1 coordinate digit of rug string
    public static final int x2CoordinateDigit = 5;//the x2 coordinate digit of rug string
    public static final int y2CoordinateDigit = 6;//the y2 coordinate digit of rug string

    // Constructor
    public Rug(int x1, int y1, int x2, int y2, Color color){
            this.x1 = x1;
            this.x2 = x2;
            this.y1 = y1;
            this.y2 = y2;
            this.color = color;
            rugId++;
    }

    // Create rug from string
    public Rug(String rugString) {
        if (rugString.length()!=rugStringLength)
            throw new RuntimeException("Invalid Rug String");
        else{
            this.x1 = Character.getNumericValue(rugString.charAt(x1CoordinateDigit));
            this.y1 = Character.getNumericValue(rugString.charAt(y1CoordinateDigit));
            this.x2 = Character.getNumericValue(rugString.charAt(x2CoordinateDigit));
            this.y2 = Character.getNumericValue(rugString.charAt(y2CoordinateDigit));
            this.color = Color.valueOf(String.valueOf(rugString.charAt(colorDigit)));
            rugId = Integer.parseInt(rugString.substring(1,3));
        }
    }

    //getters
    public int getX1() {
        return x1;
    }

    public int getX2() {
        return x2;
    }

    public int getY1() {
        return y1;
    }

    public int getY2() {
        return y2;
    }

    public Color getColor() {return color;}

    @Override
    public String toString() {
        return color+
                String.format("%02d", rugId)+
                x1+
                y1+
                x2+
                y2;
    }

    /**
     *
     * @return Abbreviated rug string
     */
    public String getAbbrRugString(){
        return color+
                String.format("%02d", rugId);
    }
}
