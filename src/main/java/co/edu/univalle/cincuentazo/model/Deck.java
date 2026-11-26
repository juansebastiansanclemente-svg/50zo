package co.edu.univalle.cincuentazo.model;

import javafx.scene.control.skin.SliderSkin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private final List<Card> cards = new ArrayList<>();

    public Deck() {
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(suit, rank));
            }
        }
        Collections.shuffle(cards);
    }
        public Card draw() {
            return cards.remove(0);
        }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public void addToBottom(List<Card> returned) {
        cards.addAll(returned);
    }

    public void refill(List<Card> recycled) {
        List<Card> shuffled = new ArrayList<>(recycled);
        Collections.shuffle(shuffled);
        cards.addAll(shuffled);

    }
}

