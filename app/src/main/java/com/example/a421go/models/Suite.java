package com.example.a421go.models;

import java.util.ArrayList;

/**
 * Représente la combinaison Suite étendu à {@link Combination}
 */
public class Suite extends Combination {

    /**
     * Constructeur de la classe.
     * @param dicesList les dés qui forment la combinaison.
     */
    public Suite(ArrayList<Dice> dicesList) {
        super(dicesList);
        super.setName("Suite");
        super.setPoints(2);
    }
}
