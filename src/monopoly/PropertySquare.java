package monopoly;

public class PropertySquare extends Square {
    protected int price;
    protected int rent;
    protected int mortgage;
    protected boolean isMortgaged;
    protected Player owner;

    public PropertySquare(String squareName, int squareIndex, int x, int y, int price, int rent, int mortgage) {
        super(squareName, squareIndex, x, y);
        this.price = price;
        this.mortgage = mortgage;
        this.rent = rent;
        this.owner = null;
        this.isMortgaged = false;
    }

    public boolean isMortgaged() {
        return isMortgaged;
    }

    public void setMortgaged(boolean mortgaged) {
        this.isMortgaged = mortgaged;
    }

    public void setOwner(Player p) {
        this.owner = p;
    }

    public Player getOwner() {
        return this.owner;
    }

    public int getRent() {
        return this.rent;
    }

    public int getPrice() {
        return this.price;
    }

    public int getMortgagePrice() {
        return this.mortgage;
    }

    public void buySquare(Player p) {
        GameIO.logOutput("Do you want to own " + squareName + " for " + price + "$?\nEnter 1 to buy, anything else to pass.");

        if (GameIO.readInput().equals("1")) {
            p.buyProperty(this);
            this.setOwner(p);
        }
    }

    @Override
    public void playerLanded(Player player) {
        if (owner == null) { // add hashToCompare
            buySquare(player);
        } else if (owner != player) {
            if (!isMortgaged) {
                GameIO.logOutput(player.getName() + " paid " + owner.getName() + " for rent due to landing on "
                        + this.squareName + ".");
                player.payTo(owner, this.getRent());
            }
        }
    }

}

