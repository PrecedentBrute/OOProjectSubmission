package monopoly;


import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.awt.Color;

public class Piece extends JPanel{
    private int xCor;
    private int yCor;
    private int index;

    public Piece(int x, int y, int playerIndex) {
        this.xCor = x;
        this.yCor = y;
        this.index = playerIndex;

        this.setBorder(BorderFactory.createLoweredBevelBorder());

        int pi = playerIndex % 4; //chooses between 0, 1, 2, 3
        System.out.println("Player index = " + pi);
        switch (pi) {
            case 0:
                this.setBackground(Color.RED);
                break;
            case 1:
                this.setBackground(Color.BLUE);
                break;
            case 2:
                this.setBackground(Color.GREEN);
                break;
            case 3:
                this.setBackground(Color.YELLOW);
                break;
        }
        int a = x + (int) (Math.random() * 10);
        int b = y + (int) (Math.random() * 10);
        this.setBounds(a, b, 15, 15);

    }

    public void move(int x, int y) {
        this.xCor = x;
        this.yCor = y;
        int a = x + (int) (Math.random() * 10);
        int b = y + (int) (Math.random() * 10);
        this.setBounds(a, b, 15, 15);
    }

    public int getIndex() {
        return index;
    }

    public int getX() {
        return xCor;
    }

    public int getY() {
        return yCor;
    }

    public JPanel getPiece() {
        return this;
    }
}
