package com.example.a421go.controllers;

import com.example.a421go.metier.DiceComparator;
import com.example.a421go.metier.SimpleBoard;
import com.example.a421go.models.Autre;
import com.example.a421go.models.Baraque;
import com.example.a421go.models.Combination;
import com.example.a421go.models.Dice;
import com.example.a421go.models.Fiche;
import com.example.a421go.models.Game;
import com.example.a421go.models.Player;
import com.example.a421go.models.Round;
import com.example.a421go.models.Suite;
import com.example.a421go.models._421;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Contrôleur qui gère le cours d'une partie.
 */
public class BoardController extends Controller {

    private static BoardController instance = null;

    /**
     * Constructeur protégé de la classe BoardController.
     */
    protected BoardController() {
        super();
    }

    /**
     * Si aucune instance n'existe, crée une instance de la classe. Sinon,
     * retourne l'instance existante.
     *
     * @return L'unique instance de la classe.
     */
    public static BoardController getInstance() {
        if (BoardController.instance == null) {
            BoardController.instance = new BoardController();
        }
        return instance;
    }

    /**
     * Fonction qui récupère les trois dés du jeu, les trie dans l'ordre croissant et cherche la combinaison obtenu (s'il y en a une)
     *
     * @return renvoie un objet combination contenant le nom de la combinaison, les points obtenus et una Arraylist des 3 dés
     */
    public Combination searchDices(ArrayList<Dice> dices) {
        Collections.sort(dices, new DiceComparator());
        Combination combination = new Autre(dices);
        if (search421(dices)) {
            combination = new _421(dices);
        } else if (searchFiche(dices)) {
            combination = new Fiche(dices);
        } else if (searchBaraque(dices)) {
            combination = new Baraque(dices);
        } else if (searchSuite(dices)) {
            combination = new Suite(dices);
        }
        return combination;
    }


    /**
     * Cherche s'il y a la combianison 421
     *
     * @param dicesList liste des dés trié dans l'ordre croissant
     * @return vrai s'il y a la combinaison, sinon faux
     */
    public boolean search421(ArrayList<Dice> dicesList) {
        if (dicesList.get(0).getFace() == 1) {
            if (dicesList.get(1).getFace() == 2) {
                if (dicesList.get(2).getFace() == 4) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public void roll() {
        SimpleBoard.getInstance().rollDices();
    }

    public ArrayList<Dice> getDices() {
        return SimpleBoard.getInstance().getDices();
    }

    public void submitRound(Round currentRound, ArrayList<Dice> dices) {
        Combination combination = searchDices(dices);
        currentRound.addGain(combination.getPoints());
        currentRound.setCombination(combination);
    }

    /**
     * Recherche s'il y a la combinaison "Fiche" soit 2 dés 1 et un autre dés quelconque
     *
     * @param dicesList liste des dés trié dans l'ordre croissant
     * @return vrai s'il y a la combianison sinon faux
     */
    public boolean searchFiche(ArrayList<Dice> dicesList) {
        if (dicesList.get(0).getFace() == 1 && dicesList.get(1).getFace() == 1) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * Recherche la combinaison "Baraque" soit 3 dés de même face
     *
     * @param dicesList liste des dés trié dans l'ordre croissant
     * @return vrai s'il y a la combianison sinon faux
     */
    public boolean searchBaraque(ArrayList<Dice> dicesList) {
        if (dicesList.get(0).getFace() == dicesList.get(1).getFace()) {
            if (dicesList.get(1).getFace() == dicesList.get(2).getFace()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Recherche la combinaison "Suite" soit 3 dés dont les faces se suivent
     *
     * @param dicesList liste des dés trié dans l'ordre croissant
     * @return vrai s'il y a la combianison sinon faux
     */
    public boolean searchSuite(ArrayList<Dice> dicesList) {
        if (dicesList.get(0).getFace() == dicesList.get(1).getFace() - 1) {
            if (dicesList.get(1).getFace() == dicesList.get(2).getFace() - 1) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
