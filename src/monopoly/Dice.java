package monopoly;
import java.util.Random;

public class Dice {
    private int diceValue;
    public final int MAX = 6;
    Random randomGenerator;

    Dice() {
        randomGenerator = new Random();
        this.diceValue = randomGenerator.nextInt(6) + 1;
    }

    void rollDice() {
        this.diceValue = randomGenerator.nextInt(6) + 1;
    }

    public int getDiceValue() {
        return this.diceValue;
    }
}