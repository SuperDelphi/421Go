package com.example.a421go.models;

import java.util.ArrayList;

/**
 * Représente une manche contenant des tours
 */
public class RoundGroup {

    //Propriété
    private ArrayList<Round> roundsList;

    /**
     * Constructeur de la classe RoundGroup
     * @param roundsList retourne l'objet roundList
     */
    public RoundGroup(ArrayList<Round> roundsList) {
        this.roundsList = roundsList;
    }

    /**
     * Retourne la liste des tours d'une manche
     * @return l'objet roundsList
     */
    public ArrayList<Round> getRoundsList() {
        return roundsList;
    }

    /**
     * Modifie la liste des tours d'une manche
     * @param roundsList prend la nouvelle liste des tours
     */
    public void setRoundsList(ArrayList<Round> roundsList) {
        this.roundsList = roundsList;
    }
}
