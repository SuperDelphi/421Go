package com.example.a421go.models;

import java.util.ArrayList;

public class Fiche extends Combination {

    public Fiche(ArrayList<Dice> dicesList) {
        super(dicesList);
        super.setName("Fiche");
        super.setPoints(dicesList.get(2).getFace());
    }
}
