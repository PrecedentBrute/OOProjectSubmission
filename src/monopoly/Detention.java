package monopoly;

import java.util.HashMap;
public class Detention {
    private static HashMap<Player, Integer> playersInDetention = new HashMap<Player, Integer>();

    public static void addToDetention(Player player) {
        if (playersInDetention.containsKey(player)) {
            GameIO.logOutput(player + " is already in detention.");
        } else {
            playersInDetention.put(player, 1);
            player.setDetention(true);
        }
    }

    public int turnsInDetention(Player player) {
        if (playersInDetention.containsKey(player)) {
            return playersInDetention.get(player);
        } else {
            return 0;
        }
    }

    private static void removeFromDetention(Player player) {
        if (playersInDetention.containsKey(player)) {
            playersInDetention.remove(player);
            player.setDetention(false);
        }
    }

    public static void passTurn() {
        for(Player p : playersInDetention.keySet()) {
            playersInDetention.put(p, playersInDetention.get(p) + 1);
            if(playersInDetention.get(p) == 3) {
                removeFromDetention(p);
                GameIO.logOutput(p + " has been released from detention.");
            }
        }
    }
}
