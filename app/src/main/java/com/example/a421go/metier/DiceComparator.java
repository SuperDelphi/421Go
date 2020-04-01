package com.example.a421go.metier;

import com.example.a421go.models.Dice;

import java.util.Comparator;

public class DiceComparator implements Comparator<Dice> {

    public DiceComparator(){}

    @Override
    public int compare(Dice dice1, Dice dice2) {
        if (dice1.getFace() == dice2.getFace()) return 0;
        else if (dice1.getFace() > dice2.getFace()) return 1;
        else return -1;
    }
}
