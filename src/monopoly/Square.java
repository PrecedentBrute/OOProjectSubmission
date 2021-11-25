package monopoly;

public abstract class Square{
    protected final int squareIndex;
    protected final String squareName;
    protected final int x;
    protected final int y;

    public Square(String squareName,  int squareIndex, int x, int y) {
        this.squareIndex = squareIndex;
        this.squareName = squareName;

        this.x = x;
        this.y = y;
    }

    public abstract void playerLanded(Player player);

    public String toString() {
        return squareName;
    }

    public String getSquareName() {
        return squareName;
    }

}
