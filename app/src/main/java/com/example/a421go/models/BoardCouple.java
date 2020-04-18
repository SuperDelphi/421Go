package com.example.a421go.models;

import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Représente un couple éphémère (Affichage/liste de dés)
 */
public class BoardCouple {
    private ViewGroup layout;
    private ArrayList<Dice> dices;

    /**
     * Constructeur de BoardCouple
     * @param layout
     * @param dices
     */
    public BoardCouple(ViewGroup layout, ArrayList<Dice> dices) {
        this.layout = layout;
        this.dices = dices;
    }

    /**
     * Renvoie l'affichage actuel
     * @return
     */
    public ViewGroup getLayout() {
        return layout;
    }

    /**
     * Met à jour l'affichage actuel
     * @param layout
     */
    public void setLayout(ViewGroup layout) {
        this.layout = layout;
    }

    /**
     * Renvoie la liste des dés actuelle
     * @return
     */
    public ArrayList<Dice> getDices() {
        return dices;
    }

    /**
     * Met à jour la liste des dés actuelle
     * @param dices
     */
    public void setDices(ArrayList<Dice> dices) {
        this.dices = dices;
    }
}
