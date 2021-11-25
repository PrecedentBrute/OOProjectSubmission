package monopoly;

public class StationSquare extends PropertySquare {

    public StationSquare(String squareName, int squareIndex, int x, int y, int price, int rent, int mortgage) {
        super(squareName, squareIndex, x, y, price, rent, mortgage);
        //TODO Auto-generated constructor stub
    }

    @Override
    public int getRent() {
        if (owner != null) {
            if (owner.stationsCount() > 1) {
                return rent * owner.stationsCount();
            } else {
                return rent;
            }
        }
        return rent;
    }

    @Override
    public void buySquare(Player p) {
        super.buySquare(p);
    }

}
