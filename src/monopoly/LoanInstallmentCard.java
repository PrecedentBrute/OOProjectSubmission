package monopoly;

public class LoanInstallmentCard implements CommunityCard {
    public void doAction(Player p) {
        GameIO.logOutput("You have received a loan installment card.\nYou have to pay 200.");
        p.withdrawMoney(200);
    }
}