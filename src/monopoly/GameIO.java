package monopoly;

import java.util.Scanner;
import java.util.ArrayList;

public class GameIO {
    private static MonopolyFrame guiFrame = null;
    public static boolean inputFromGui = false;
    private static Scanner scanner = new Scanner(System.in);
    public static ArrayList<Piece> pieces = new ArrayList<Piece>();

    public static void setGuiFrame(MonopolyFrame guiFrame) {
        GameIO.guiFrame = guiFrame;
    }

    public static String readInput() {
        if (!inputFromGui) {
            return scanner.nextLine();
        } else {
            return guiFrame.syncInput.getInput();
        }

    }

    public static void logOutput(String out) {
        System.out.println(out);
        if(guiFrame != null) {
            guiFrame.logTextToGUI(out);
        }
    }
}
