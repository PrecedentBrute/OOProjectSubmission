package monopoly;

public class ScholarshipCard implements CommunityCard {
    public void doAction(Player p) {
        GameIO.logOutput("You received a ScholarshipCard. Congratulations!\nYou just got 500 deposited in your account.");
        p.depositMoney(500);
    }
}
