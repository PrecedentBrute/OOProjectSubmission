package monopoly;

import java.util.ArrayList;

public class GameState {
    private GameBoard board;
    private ArrayList<Player> players;
    private Dice dice;
    static int turn = 1;

    public GameState(ArrayList<Player> players) {
        board = new GameBoard();
        this.players = players;
        ArrayList<Piece> updatedPieces = new ArrayList<Piece>();
        for (Player p : players) {
            updatedPieces.add(p.getPiece());
        }
        GameIO.pieces = updatedPieces;
        dice = new Dice();
    }

    public void logState() {
        GameIO.logOutput("Game state : ");
        for (Player p : players) {
            GameIO.logOutput(p.getName() + " is on " + board.squareAtIndex(
                    p.getCurrentSquareI()) + " and has " + p.getMoney() + " money.");

            if (p.getOwnedSquares().size() != 0) {
                GameIO.logOutput(p.getName() + " owns the following squares : ");
                for(PropertySquare s : p.getOwnedSquares()) {
                    GameIO.logOutput(s.toString());
                }
            }
        }
    }

    public void playTurn(Player p) {
        if (p.isDetention()) {
            GameIO.logOutput(p.getName() + " is in detention.");
        } else {
            dice.rollDice();
            p.move(dice.getDiceValue(), board);
            GameIO.logOutput(p.getName() + " rolled a " + dice.getDiceValue() + " and is now on "
                    + board.squareAtIndex(p.getCurrentSquareI()) + ".");
            //add options to mortgage, build house, list properties

            ArrayList<PropertySquare> mortgaged = new ArrayList<PropertySquare>();

            for (PropertySquare s : p.getOwnedSquares()) {
                if (s.isMortgaged()) {
                    mortgaged.add(s);
                }
            }


            GameIO.logOutput("Options : ");
            GameIO.logOutput("1.Build Houses\n2.Take a mortgage\n3.Cover a mortgage\n4.Exit");
            int choice = 4;

            try{
                choice = Integer.valueOf(GameIO.readInput());
            } catch (NumberFormatException e) {
                GameIO.logOutput("Invalid input. Not a number.");
            }

            while (choice != 4) {
                switch (choice) {
                    case 1:
                        ArrayList<CitySquare> houseable = new ArrayList<CitySquare>();
                        for (PropertySquare s : p.getOwnedSquares()) {
                            if (s instanceof CitySquare) {
                                if (((CitySquare) s).getHouses() < 3) {
                                    houseable.add((CitySquare) s);
                                }
                            }
                        }

                        if (houseable.size() == 0) {
                            GameIO.logOutput("You cannot build any new houses on your current properties.");
                        } else {
                            GameIO.logOutput("You can build houses on the following properties : ");
                            for (int i = 0; i < houseable.size(); i++) {
                                GameIO.logOutput(i + ". " + houseable.get(i) + " with " + houseable.get(i).getHouses()
                                        + " houses.\nCost of adding a new house is " + houseable.get(i).getHouseCost()
                                        + ".");
                            }
                            GameIO.logOutput("Enter the number of the property you want to build a house on.");
                            int index = Integer.valueOf(GameIO.readInput());
                            if (index >= houseable.size()) {
                                GameIO.logOutput("Invalid input.");
                            } else {
                                houseable.get(index).addHouse();
                                GameIO.logOutput(p + " built a house on " + houseable.get(index) + ".");
                            }
                        }
                        break;

                    case 2:
                        ArrayList<PropertySquare> mortgagable = new ArrayList<PropertySquare>();
                        for (PropertySquare s : p.getOwnedSquares()) {
                            if (!s.isMortgaged()) {
                                mortgagable.add(s);
                            }
                        }

                        if (mortgagable.size() == 0) {
                            GameIO.logOutput("You cannot take any new mortgages.");
                        } else {
                            GameIO.logOutput("You can mortgage the following properties : ");
                            for (int i = 0; i < mortgagable.size(); i++) {
                                GameIO.logOutput(i + ". " + mortgagable.get(i) + " with a mortgage price of "
                                        + mortgagable.get(i).getMortgagePrice() + ".");
                            }
                            GameIO.logOutput("Enter the number of the property you want to build a house on.");
                            int index = Integer.valueOf(GameIO.readInput());
                            if (index >= mortgagable.size()) {
                                GameIO.logOutput("Invalid input.");
                            } else {
                                mortgagable.get(index).setMortgaged(true);
                                GameIO.logOutput(p + " mortgaged " + mortgagable.get(index) + ".");
                            }
                        }
                        break;

                    case 3:
                        if (mortgaged.size() == 0) {
                            GameIO.logOutput("You don't have any mortgages to cover.");
                        } else {
                            GameIO.logOutput("You can cover the following properties : ");
                            for (int i = 0; i < mortgaged.size(); i++) {
                                GameIO.logOutput(i + ". " + mortgaged.get(i) + " by paying "
                                        + (int) (mortgaged.get(i).getMortgagePrice() * 1.1) + ".");
                            }
                            GameIO.logOutput("Enter the number of the property you want to build a house on.");
                            int index = Integer.valueOf(GameIO.readInput());
                            if (index >= mortgaged.size()) {
                                GameIO.logOutput("Invalid input.");
                            } else {
                                mortgaged.get(index).setMortgaged(false);
                                GameIO.logOutput(p + " covered " + mortgaged.get(index) + ".");
                            }
                        }
                        break;
                    default:
                        GameIO.logOutput("Invalid choice");
                        break;
                }
                GameIO.logOutput("Options : ");
                GameIO.logOutput("1.Build Houses\n2.Take a mortgage\n3.Cover a mortgage\n4.Exit");
                choice = 4;
                try {
                    choice = Integer.valueOf(GameIO.readInput());
                } catch (NumberFormatException e) {
                    GameIO.logOutput("Invalid input. Not a number.");
                }
            }

            int interestsToBank = 0;
            for (PropertySquare s : mortgaged) {
                interestsToBank += (int) (s.getMortgagePrice() * 0.1);
            }

            if(interestsToBank != 0) {
                GameIO.logOutput(p+ " paid " + interestsToBank + " in interests to the Bank.");
                p.withdrawMoney(interestsToBank);
            }

        }
    }

    private boolean checkBankruptcy() {
        for (Player p : players) {
            if (p.isBankrupt()) {
                return true;
            }
        }
        return false;
    }

    private void passTurn() {
        GameIO.logOutput("\nNew turn started - Turn " + turn + ".");
        turn++;
        logState();
        for (Player p : players) {
            playTurn(p);
        }
        Detention.passTurn();
        GameIO.logOutput("Turn Ended.\n");
    }

    public void startGame() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (!checkBankruptcy()) {
            passTurn();
        }
        GameIO.logOutput("Game Over.");
        for(Player p : players){
            if(p.isBankrupt()){
                GameIO.logOutput(p+" is bankrupt.");
            }
        }
    }
}
