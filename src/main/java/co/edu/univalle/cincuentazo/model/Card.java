package co.edu.univalle.cincuentazo.model;

import java.util.List;

public class Card {
    private final Suit suit;
    private final Rank rank;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }

    public List<Integer> getPossibleValues() {
        if (rank == Rank.ACE) {
            return List.of(1, 10);
        }
        return List.of(rank.getValue());
    }
}
