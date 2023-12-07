package comp1110.ass2;

/**
 * Authorship
 * Heng Sun u7611510
 * Runyao Wang u6812566
 */
public enum Color {
    c('c'),//cyan
    y('y'),//yellow
    p('p'),
    r('r');//purple
    private static final Color[] VALUES = values();
    private char color;

    Color(char color) {
        this.color = color;
    }

    public char getColor() {
        return color;
    }

    public Color next() {
        int nextIndex = (this.ordinal() + 1) % VALUES.length;
        return VALUES[nextIndex];
    }
}
