package com.example.a421go.metier;

import com.example.a421go.models.Dice;

import java.util.Comparator;

/**
 * Comparateur de dés ({@link Dice}).
 */
public class DiceComparator implements Comparator<Dice> {
    /**
     * Constructeur de la classe.
     */
    public DiceComparator() {
    }

    /**
     * Compare deux dés entre eux par rapport à la face qu'ils montrent.
     * @param dice1 le premier dé ({@link Dice}).
     * @param dice2 le second dé ({@link Dice}).
     * @return 1 si le premier dé > le second dé, 0 si le premier dé = le second dé,
     * -1 si le premier dé < le second dé.
     */
    @Override
    public int compare(Dice dice1, Dice dice2) {
        if (dice1.getFace() == dice2.getFace()) return 0;
        else if (dice1.getFace() > dice2.getFace()) return 1;
        else return -1;
    }
}
