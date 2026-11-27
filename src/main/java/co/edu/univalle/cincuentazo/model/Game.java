package co.edu.univalle.cincuentazo.model;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public Player getcurrectPlayer() {
        return players.get(currentIndex);
    }

    private void advanceTurn() {
        do {
            currentIndex = (currentIndex + 1 ) % players.size();
        } while (players.get(currentIndex).isEliminated());
    }
    private Card drawCard() {
        if (deck.isEmpty()) {
            deck.refill(table.takeCardsForRecycling());
        }
        return deck.draw();
    }

    public void playMove(Move move) {
        if (!table.canPlay(move.value())) {
            throw new IllegalArgumentException("Move exceeds the maximum sum");
        }
        Player player = getcurrectPlayer();
        player.removeCard(move.card());
        table.place(move.card(), move.value());
        player.addCard(drawCard());
        advanceTurn();
    }

    public boolean eliminateCurrentIfStuck() {
        Player player = getcurrectPlayer();
        if (player.hasPlayableCard(table)) {
            return false;
        }
        deck.addToBottom(player.eliminate());
        advanceTurn();
        return true;
    }

    public boolean isGameOver() {
        int active = 0;
        for (Player player : players) {
            if (!player.isEliminated()) {
                active++;
            }
        }
        return active == 1;
    }

    public Optional<Player> getWinner() {
        if (!isGameOver()) {
            return Optional.empty();
        }
        for (Player player : players) {
            if (!player.isEliminated()) {
                return Optional.of(player);
            }
        }
        return Optional.empty();
    }

    public List<Player> getPlayers() {
        return List.copyOf(players);
    }

    public int getTableSum() {
        return table.getSum();
    }

    public Card getTableTopCard() {
        return table.getTopCard();
    }
}

