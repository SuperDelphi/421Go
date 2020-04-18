package com.example.a421go.models;

import java.util.ArrayList;

/**
 * Représente la combinaison Fiche étendu à {@link Combination}
 */
public class Fiche extends Combination {

    /**
     * Constructeur de la combinaison Fiche
     * @param dicesList les dés qui forment la combinaison.
     */
    public Fiche(ArrayList<Dice> dicesList) {
        super(dicesList);
        super.setName("Fiche");
        super.setPoints(dicesList.get(2).getFace());
    }
}
