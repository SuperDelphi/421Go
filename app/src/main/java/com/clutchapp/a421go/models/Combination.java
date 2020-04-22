package com.clutchapp.a421go.models;

import java.util.ArrayList;

/**
 * Représente une combinaison.
 */
public class Combination {

    // Properties

    private String name;
    private int points;
    private ArrayList<Dice> dicesList;

    // Constructors

    /**
     * Constructeur de Combinaison
     * @param dicesList
     */
    public Combination(ArrayList<Dice> dicesList) {
        this.dicesList = dicesList;
    }

    // Getters

    /**
     * Renvoie le nom de la combinaison
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Renvoie les points de la combinaison
     * @return
     */
    public int getPoints() {
        return points;
    }

    /**
     * Renvoie la liste des dés de la combinaison
     * @return
     */
    public ArrayList<Dice> getDicesList() {
        return dicesList;
    }

    //Setters

    /**
     * Met à jour les points de la combinaison
     * @param points
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * Met à jour le nom de la combinaison
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
}
