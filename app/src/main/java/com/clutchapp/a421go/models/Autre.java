package com.clutchapp.a421go.models;

import java.util.ArrayList;

/**
 * Représente la combinaison Autre étendu à {@link Combination}
 */
public class Autre extends Combination {

    /**
     * Constructeur de la combinaison Autre
     * @param dicesList les dés qui forment la combinaison.
     */
    public Autre(ArrayList<Dice> dicesList) {
        super(dicesList);
        super.setName("Autre");
        super.setPoints(1);
    }
}
