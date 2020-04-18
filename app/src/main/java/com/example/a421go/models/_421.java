package com.example.a421go.models;

import java.util.ArrayList;

/**
 * Repésente la combinaison 421
 */
public class _421 extends Combination {

    /**
     * Constructeur de la combinaison 421
     * @param dicesList
     */
    public _421(ArrayList<Dice> dicesList) {
        super(dicesList);
        super.setPoints(10);
        super.setName("421");
    }

}
