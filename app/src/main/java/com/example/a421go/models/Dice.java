package com.example.a421go.models;

import androidx.annotation.NonNull;

/**
 * Représente un dé
 */
public class Dice implements Comparable<Dice>{

    //Prorpiétés
    private int faceCount = 6;
    private int face = 1;
    private Coordinates coords;
    private boolean selected = false;

    /**
     * Constructeur du dé contenant les coordonnées d'affichage
     */
    public Dice() {
        this.coords = new Coordinates(0, 0);
    }

    /**
     * Constructeur du dé appellant l'autre constructeur et nécessitant une face du dé
     * @param face
     */
    public Dice(int face) {
        this();
        if (face < 1) {
            this.face = 1;
        } else if (face > getFaceCount()) {
            this.face = getFaceCount();
        } else {
            this.face = face;
        }
    }

    /**
     * Permet de récupérer la face actuelle du dé
     * @return
     */
    public int getFace() {
        return this.face;
    }

    /**
     * Permet de savoir si le dé est sélectionné
     * @return
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * Renvoie le nombre de face possible du dé
     * @return
     */
    public int getFaceCount() {
        return faceCount;
    }

    /**
     * Méthode modifiant l'attribut Select à vrai
     */
    public void select() {
        this.selected = true;
    }

    /**
     * Méthode modifiant l'attribut Select à faux
     */
    public void deselect() {
        this.selected = false;
    }

    /**
     * Méthode permettant de sélectionner ou desélectionner un dé
     * @return
     */
    public boolean toggleSelection() {
        return this.selected = !isSelected();
    }

    /**
     * Méthode permettant de lancer un dé
     * @return
     */
    public int roll() {
        this.face = (int)Math.round(Math.random() * (getFaceCount() - 1)) + 1;
        return getFace();
    }

    /**
     * Méthode permettant la comparaison avec d'autre dé car la classe Dice implémente Comparable
     * @param dice
     * @return
     */
    @Override
    public int compareTo(Dice dice) {
        if (face == dice.face) return 0;
        else if (face > dice.face) return 1;
        else return -1;
    }

    /**
     * Méthode permettant d'afficher les informations du dé (Conseil : utilisable dans les logs)
     * @return
     */
    @NonNull
    @Override
    public String toString() {
        return "Dé à " + getFaceCount() + " faces => " + getFace();
    }
}
