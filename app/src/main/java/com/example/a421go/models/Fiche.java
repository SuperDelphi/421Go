package com.example.a421go.models;

import java.util.ArrayList;

/**
 * Représente la combinaison Fiche
 */
public class Fiche extends Combination {

    /**
     * Constructeur de la combinaison Fiche
     * @param dicesList
     */
    public Fiche(ArrayList<Dice> dicesList) {
        super(dicesList);
        super.setName("Fiche");
        super.setPoints(dicesList.get(2).getFace());
    }
}
