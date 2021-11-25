package monopoly;


public class CommunityChest {
    static CommunityCard[] cards = new CommunityCard[3];
    static
    {
        cards[0] = new ScholarshipCard();
        cards[1] = new CoverMortgageCard();
        cards[2] = new LoanInstallmentCard();
    }
}