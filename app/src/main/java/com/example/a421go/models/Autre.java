package com.example.a421go.models;

import java.util.ArrayList;

/**
 * Représente la combinaison Autre
 */
public class Autre extends Combination {

    /**
     * Constructeur de la combinaison Autre
     * @param dicesList
     */
    public Autre(ArrayList<Dice> dicesList) {
        super(dicesList);
        super.setName("Autre");
        super.setPoints(1);
    }
}
