package com.example.a421go.models;

import java.util.ArrayList;

public class Baraque extends Combination {

    public Baraque(ArrayList<Dice> dicesList) {
        super(dicesList);
        super.setName("Baraque");
        super.setPoints(dicesList.get(0).getFace());
    }
}
