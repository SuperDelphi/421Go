package com.example.a421go.metier;

import android.view.ViewGroup;

import com.example.a421go.models.Dice;

import java.util.ArrayList;

/**
 * Représente la réserve du plateau de jeu.
 */
public class ReserveBoard {
    private ViewGroup layout;
    private ArrayList<Dice> dices = new ArrayList<>();

    /**
     * Constructeur de la classe.
     *
     * @param layout le layout ({@link ViewGroup}) utilisé pour accueillir les dés dans la réserve
     */
    public ReserveBoard(ViewGroup layout) {
        this.layout = layout;
    }

    /**
     * @param index la position du dé dans la liste
     * @return le dé situé à la position spécifiée
     */
    public Dice getDice(int index) {
        return getDices().get(index);
    }

    /**
     * @return la liste des dés contenus dans la réserve
     */
    public ArrayList<Dice> getDices() {
        return dices;
    }

    /**
     * Ajoute un dé à la réserve.
     *
     * @param dice le dé à ajouter
     */
    public void addDice(Dice dice) {
        getDices().add(dice);
    }

    /**
     * Ajoute des dés à la réserve. Si un dé est déjà présent dans la réserve, il ne sera pas ajouté.
     *
     * @param dices la liste des dés à ajouter
     * @return la liste des dés qui ont été réellement ajoutés.
     */
    public ArrayList<Dice> addDices(ArrayList<Dice> dices) {
        ArrayList<Dice> addedDices = new ArrayList<>();
        for (Dice dice :
                dices) {
            if (!getDices().contains(dice)) {
                getDices().add(dice);
                addedDices.add(dice);
            }
        }

        return addedDices;
    }

    /**
     * Définit la liste des dés contenus dans la réserve à partir d'une nouvelle liste.
     *
     * @param dices la nouvelle liste des dés
     */
    public void setDices(ArrayList<Dice> dices) {
        this.dices = dices;
    }

    /**
     * Supprime un dé de la réserve.
     *
     * @param dice le dé à supprimer de la réserve
     * @return TRUE si le dé a été supprimé, sinon FALSE
     */
    public boolean removeDice(Dice dice) {
        return getDices().remove(dice);
    }

    /**
     * Supprime des dés de la réserve.
     *
     * @param dices la liste des dés à supprimer de la réserve
     * @return TRUE si la suppression s'est effectuée correctement, sinon FALSE
     */
    public boolean removeDices(ArrayList<Dice> dices) {
        return getDices().removeAll(dices);
    }

    /**
     * Supprime tous les dés contenus dans la réserve.
     */
    public void clear() {
        getDices().clear();
    }

    /**
     * @return le layout ({@link ViewGroup}) utilisé pour accueillir les dés dans la réserve
     */
    public ViewGroup getLayout() {
        return this.layout;
    }
}
