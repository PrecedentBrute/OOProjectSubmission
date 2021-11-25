package monopoly;

import java.util.*;

public class Player {
    private int playerIndex;
    private int currentSquareI;
    private ArrayList<PropertySquare> ownedSquares;
    private int money;
    private boolean bankrupt;
    private String name;
    private int stationsCount;
    private Piece piece;

    public boolean detention;

    public Player(int playerIndex, String name) {
        this.playerIndex = playerIndex;
        this.currentSquareI = 0;
        this.ownedSquares = new ArrayList<PropertySquare>();
        this.money = 1500;
        this.bankrupt = false;
        this.name = name;
        this.stationsCount = 0;
        this.detention = false;
        piece = new Piece(30, 30, playerIndex);
    }

    public Piece getPiece() {
        return this.piece;
    }

    public void setDetention(boolean b) {
        this.detention = b;
    }

    public boolean isDetention() {
        return this.detention;
    }

    public int getPositionIndex() {
        return currentSquareI;
    }

    public String getName() {
        return name;
    }

    public int getMoney() {
        return this.money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public boolean hasEnoughMoney(int amount) {
        if (this.money < amount) {
            GameIO.logOutput(this.name + " does not have enough money to pay.");
            return false;
        }
        return true;
    }

    public void withdrawMoney(int amount) {
        if (amount > this.money) {
            System.out.println(
                    "Player " + this.playerIndex + " doesn't have enough money to withdraw " + amount + ". Bankrupt.");
            this.bankrupt = true;
        } else {
            this.money -= amount;
        }
    }

    public String toString() {
        return this.name;
    }

    public void depositMoney(int amount) {
        this.money += amount;
    }

    public int stationsCount() {
        return this.stationsCount;
    }

    public void buyProperty(PropertySquare sq) {
        if (hasEnoughMoney(sq.getPrice()))
            this.withdrawMoney(sq.getPrice());
        ownedSquares.add(sq);
        if (sq instanceof StationSquare) {
            stationsCount++;
        }
    }

    public void mortgage(PropertySquare p) {
        depositMoney((int) (p.getMortgagePrice()));
        p.setMortgaged(true);
    }

    public void coverMortgage(PropertySquare p) {
        if(hasEnoughMoney((int)(1.1*p.getMortgagePrice()))) withdrawMoney((int)(1.1*p.getMortgagePrice()));
        p.setMortgaged(false);
    }

    public void payTo(Player p, int amount) {
        p.depositMoney(amount);
        this.withdrawMoney(amount);
    }

    public int getPlayerIndex() {
        return this.playerIndex;
    }

    public int getCurrentSquareI() {
        return this.currentSquareI;
    }

    public void move(int steps, GameBoard gb) {
        this.currentSquareI += steps;
        if (this.currentSquareI >= 24) {
            this.currentSquareI -= 24;
        }

        GameIO.logOutput(this + " reached " + gb.squareAtIndex(this.currentSquareI) + ".");
        gb.squareAtIndex(currentSquareI).playerLanded(this);
        // @@move the piece in gui
    }

    public void move(Square sq, GameBoard gb) {
        this.currentSquareI = sq.squareIndex;
        GameIO.logOutput(this + " reached " + sq + ".");
        sq.playerLanded(this);
        // @@move in gui
    }

    public ArrayList<PropertySquare> getOwnedSquares() {
        return this.ownedSquares;
    }

    public boolean isBankrupt() {
        return this.bankrupt;
    }

    public boolean completeColor(String color) {
        int sameOwned = 0;
        for (PropertySquare p : ownedSquares) {
            if (p instanceof CitySquare) {
                if ((((CitySquare) p).getColor()).equals(color)) {
                    sameOwned++;
                }
            }
        }
        if (sameOwned == 4)
            return true;

        return false;
    }

}