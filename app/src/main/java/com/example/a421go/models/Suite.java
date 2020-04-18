package com.example.a421go.models;

import java.util.ArrayList;

/**
 * Représente la suite (une combinaison possible).
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
