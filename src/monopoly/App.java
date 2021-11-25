package monopoly;

import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        ArrayList<Player> players = new ArrayList<>();
        System.out.println("Enter number of players : ");
        int n = Integer.valueOf(GameIO.readInput());

        System.out.println("Enter player names, each on a new line.");
        for (int i = 0; i < n; i++) {
            String s = GameIO.readInput();
            players.add(new Player(i + 1, s));
        }

        System.out.println("Do you want to input from GUI, or use the console? Enter 1 for GUI, anything else for console.");
        String input = GameIO.readInput();
        if (input.equals("1")) {
            GameIO.inputFromGui = true;
        }

        SyncInput syncInput = new SyncInput();
        Thread guiThread = new Thread(new MonopolyFrame(syncInput));
        guiThread.start();

        GameState g = new GameState(players);
        g.startGame();
    }
}
