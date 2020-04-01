package com.example.a421go.models;

import java.util.ArrayList;

public class Autre extends Combination {

    public Autre(ArrayList<Dice> dicesList) {
        super(dicesList);
        super.setName("Autre");
        super.setPoints(1);
    }
}
