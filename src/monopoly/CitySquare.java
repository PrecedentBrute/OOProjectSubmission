package monopoly;

public class CitySquare extends PropertySquare {

    private int houses;
    private String color;
    private final int houseCost = 50;

    public CitySquare(String squareName, int squareIndex, int x, int y, int price, int rent, int mortgage, String color) {
        super(squareName, squareIndex, x, y, price, rent, mortgage);
        this.houses = 0;
        this.color = color;
    }

    public int getHouses() {
        return houses;
    }

    public String getColor() {
        return color;
    }

    public int getHouseCost() {
        return houseCost;
    }

    public void addHouse() {
        if (houses == 3) {
            GameIO.logOutput("You have already built maximum(3) number of houses on " + this.squareName + ".");
            return;
        }
        houses++;

        if(owner.hasEnoughMoney(houseCost))owner.withdrawMoney(houseCost);
    }

    @Override
    public int getRent() {
        int calculatedRent = rent;
        if (owner != null) {
            if (houses == 0) {

            } else if (houses == 1) {
                calculatedRent = (int) (calculatedRent * 1.5);
            } else if (houses == 2) {
                calculatedRent = (int) (calculatedRent * 2);
            } else if (houses == 3) {
                calculatedRent = (int) (calculatedRent * 2.5);
            }
        }

        if (owner.completeColor(this.color))
            return calculatedRent * 2;

        return calculatedRent;
    }
}
