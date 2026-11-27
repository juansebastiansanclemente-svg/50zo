package co.edu.univalle.cincuentazo.model;


import java.util.ArrayList;
import java.util.List;

public class Game {
    private final Deck deck = new Deck();
    private final Table table = new Table();
    private final List<Player> players = new ArrayList<>();
    private int currentIndex;

    public Game(String humanName, int machineCount) {
        players.add(new HumanPlayer(humanName));
        for (int i = 1; i <= machineCount; i++) {
            players.add(new MachinePlayer("Maquina " + i));
        }
        for (Player player : players) {
            for (int i = 0; i < 4; i++) {
                player.addCard(deck.draw());
            }
        }
        Card first = deck.draw();
        table.place(first, first.getRank().getValue());
    }
}

