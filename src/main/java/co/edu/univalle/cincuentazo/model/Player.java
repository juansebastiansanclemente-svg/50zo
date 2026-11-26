package co.edu.univalle.cincuentazo.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {
    private final String name;
    private final List<Card> hand = new ArrayList<>();
    private boolean eliminated;

    public Player(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public boolean isEliminated() {
        return eliminated;
    }
    public List<Card> getHand(){
        return List.copyOf(hand);
    }

    public void addCard(Card card) {
        hand.add(card);
    }
    public void removeCard(Card card) {
        hand.remove(card);
    }

    public List<Card> eliminated() {
        eliminated = true;
        List<Card> returned = new ArrayList<>(hand);
        hand.clear();
        return returned;
    }
    public boolean hasPlayableCard(Table table) {
        for (Card card : hand) {
            for (int value : card.getPossibleValues()) {
                if (table.canPlay(value)) {
                    return true;
                }
            }
        }
        return false;
    }
}
