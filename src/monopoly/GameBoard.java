package monopoly;


import java.util.Random;

public class GameBoard {
    private Square[] board;

    GameBoard() {
        this.board = new Square[24];

        board[0] = new SpecialSquare("Go", 0, 0, 0) {
            @Override
            public void playerLanded(Player p) {
                GameIO.logOutput(p.getName() + " landed on GO and collected 200.");
                p.depositMoney(200);
            }
        };
        // name, index, x, y, price, rent, mortgage
        board[1] = new CitySquare("Bhagirath", 1, 0, 0, 500, 50, 300, "Red");

        board[2] = new CitySquare("SR", 2, 0, 0, 400, 45, 200, "Blue");

        board[3] = new SpecialSquare("Grub Night", 3, 0, 0) {
            @Override
            public void playerLanded(Player p) {
                GameIO.logOutput(p + " went to Grub Night, paid 50.");
                p.withdrawMoney(50);
            }
        };

        board[4] = new CitySquare("Ashoka", 4, 0, 0, 400, 30, 150, "Green");

        board[5] = new SpecialSquare("Visiting Detention", 5, 0, 0) {
            @Override
            public void playerLanded(Player p) {
                GameIO.logOutput(p + " is visiting detention.");
            }
        };

        board[6] = new CitySquare("Clock Tower", 6, 0, 0, 600, 100, 200, "Yellow");

        board[7] = new CitySquare("Budh", 7, 0, 0, 350, 50, 150, "Red");

        board[8] = new StationSquare("Loharu", 8, 0, 0, 200, 50, 150);

        board[9] = new CitySquare("Shankar", 9, 0, 0, 400, 50, 200, "Blue");

        board[10] = new CitySquare("C'not", 10, 0, 0, 500, 150, 250, "Green");

        board[11] = new CitySquare("Gandhi", 11, 0, 0, 400, 75, 200, "Yellow");

        board[12] = new SpecialSquare("Free Parking", 12, 0, 0) {
            @Override
            public void playerLanded(Player p) {
                GameIO.logOutput(p + " landed on Free Parking.");
            }
        };

        board[13] = new CitySquare("N.A.B", 13, 0, 0, 700, 200, 350, "Red");

        board[14] = new CitySquare("Vishwakarma", 14, 0, 0, 400, 60, 225, "Blue");

        board[15] = new SpecialSquare("Community Chest", 15, 0, 0) {
            @Override
            public void playerLanded(Player p) {
                GameIO.logOutput(p + " landed on Community Chest.");
                int chance = new Random().nextInt(3) + 1;
                CommunityChest.cards[chance].doAction(p);
            }
        };

        board[16] = new CitySquare("Meera", 16, 0, 0, 500, 125, 300, "Green");

        board[17] = new SpecialSquare("Go To Detention", 17, 0, 0) {
            @Override
            public void playerLanded(Player p) {
                Detention.addToDetention(p);
                GameIO.logOutput(p + " is now in detention.");
            }
        };

        board[18] = new CitySquare("CV Raman", 18, 0, 0, 400, 70, 225, "Yellow");

        board[19] = new CitySquare("Ram", 19, 0, 0, 450, 50, 150, "Red");

        board[20] = new StationSquare("Chirava", 20, 0, 0, 300, 75, 150);

        board[21] = new CitySquare("Vyas", 21, 0, 0, 500, 100, 250, "Blue");

        board[22] = new CitySquare("Krishna", 22, 0, 0, 400, 50, 225, "Green");

        board[23] = new CitySquare("MSA", 23, 0, 0, 800, 250, 600, "Yellow");
    }

    public Square squareAtIndex(int i) {
        return board[i];
    }
}
