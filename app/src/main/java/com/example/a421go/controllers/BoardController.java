package com.example.a421go.controllers;

import android.content.Context;
import android.util.Log;

import com.example.a421go.metier.DiceComparator;
import com.example.a421go.models.Autre;
import com.example.a421go.models.Baraque;
import com.example.a421go.models.Combination;
import com.example.a421go.models.Dice;
import com.example.a421go.models.Fiche;
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
    protected BoardController(Context context) {
        super(context);
    }

    /**
     * Si aucune instance n'existe, crée une instance de la classe. Sinon,
     * retourne l'instance existante.
     * @return L'unique instance de la classe.
     */
    public static BoardController getInstance(Context context) {
        if (BoardController.instance == null) {
            BoardController.instance = new BoardController(context);
        }
        return instance;
    }

    /**
     * Fonction qui récupère les trois dés du jeu, les trie dans l'ordre croissant et cherche la combinaison obtenu (s'il y en a une)
     * @return renvoie un objet combination contenant le nom de la combinaison, les points obtenus et una Arraylist des 3 dés
     */
    public Combination searchDices(Dice dice1, Dice dice2, Dice dice3){
        ArrayList<Dice> dicesList = new ArrayList<Dice>();
        dicesList.add(dice1);dicesList.add(dice2);dicesList.add(dice3);
        Collections.sort(dicesList, new DiceComparator());
        Combination combination = new Autre(dicesList);
        if (search421(dicesList)){
            combination = new _421(dicesList);
        } else if (searchFiche(dicesList)){
            combination = new Fiche(dicesList);
        } else if (searchBaraque(dicesList)){
            combination = new Baraque(dicesList);
        } else if (searchSuite(dicesList)) {
            combination = new Suite(dicesList);
        }
        return combination;
    }


    /**
     * Cherche s'il y a la combianison 421
     * @param dicesList liste des dés trié dans l'ordre croissant
     * @return vrai s'il y a la combinaison, sinon faux
     */
    public boolean search421(ArrayList<Dice> dicesList){
        if (dicesList.get(0).getFace() == 1){
            if (dicesList.get(1).getFace() == 2){
                if (dicesList.get(2).getFace() == 4){
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

    /**
     * Recherche s'il y a la combinaison "Fiche" soit 2 dés 1 et un autre dés quelconque
     * @param dicesList liste des dés trié dans l'ordre croissant
     * @return vrai s'il y a la combianison sinon faux
     */
    public boolean searchFiche(ArrayList<Dice> dicesList){
        if (dicesList.get(0).getFace() == 1 && dicesList.get(1).getFace() == 1){
            return true;
        } else {
            return false;
        }
    }


    /**
     * Recherche la combinaison "Baraque" soit 3 dés de même face
     * @param dicesList liste des dés trié dans l'ordre croissant
     * @return vrai s'il y a la combianison sinon faux
     */
    public boolean searchBaraque(ArrayList<Dice> dicesList){
        if (dicesList.get(0).getFace() == dicesList.get(1).getFace()){
            if (dicesList.get(1).getFace() == dicesList.get(2).getFace()){
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
     * @param dicesList liste des dés trié dans l'ordre croissant
     * @return vrai s'il y a la combianison sinon faux
     */
    public boolean searchSuite(ArrayList<Dice> dicesList){
        if (dicesList.get(0).getFace() == dicesList.get(1).getFace()-1){
            if (dicesList.get(1).getFace() == dicesList.get(2).getFace()-1){
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
