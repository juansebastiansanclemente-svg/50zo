package co.edu.univalle.cincuentazo.model;

import java.util.ArrayList;
import java.util.List;

public class Table {
    private static final int MAX_SUM = 50;
    private final List<Card> playedCards = new ArrayList<>();
    private int sum;

    public void place(Card card, int value) {
        playedCards.add(card);
        sum += value;
    }

    public int getSum() {
        return sum;
    }

    public boolean canPlay(int value) {
        return sum + value <= MAX_SUM;
    }

    public Card getTopCard() {
        return playedCards.get(playedCards.size() - 1);
    }

    public List<Card> takeCardsForRecycling() {
        List<Card> recycled = new ArrayList<>(playedCards.subList(0, playedCards.size() - 1));
        Card top = getTopCard();
        playedCards.clear();
        playedCards.add(top);
        return recycled;
    }

}
