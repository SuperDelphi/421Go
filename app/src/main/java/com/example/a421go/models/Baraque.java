package com.example.a421go.models;

import java.util.ArrayList;

/**
 * Représente la combinaison Baraque
 */
public class Baraque extends Combination {

    /**
     * Constructeur de la combinaison Baraque
     * @param dicesList
     */
    public Baraque(ArrayList<Dice> dicesList) {
        super(dicesList);
        super.setName("Baraque");
        super.setPoints(dicesList.get(0).getFace());
    }
}
