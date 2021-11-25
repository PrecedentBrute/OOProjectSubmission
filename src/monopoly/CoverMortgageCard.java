package monopoly;

public class CoverMortgageCard implements CommunityCard {
    public void doAction(Player p) {
        GameIO.logOutput("You have received a CoverMortgageCard.\nIf you have a mortgage, oldest ticket will be covered by the price money.");
        for (PropertySquare sq : p.getOwnedSquares()) {
            if (sq.isMortgaged) {
                p.depositMoney((int)(sq.getMortgagePrice() * 1.1));
            }
        }
    }
}
