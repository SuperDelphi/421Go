package com.example.a421go.controllers;

import android.content.Context;
import android.util.Log;

import com.example.a421go.metier.DiceComparator;
import com.example.a421go.models.Dice;

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


    public ArrayList<Dice> searchDices(){//Dice dice1, Dice dice2, Dice dice3){
        ArrayList<Dice> dicesList = new ArrayList<Dice>();
        Dice dice1 = new Dice(6);Dice dice2 = new Dice(2);Dice dice3 = new Dice(4);
        dicesList.add(dice1);dicesList.add(dice2);dicesList.add(dice3);
        Collections.sort(dicesList, new DiceComparator());
        return dicesList;
    }


    public boolean search421(ArrayList<Dice> dicesList){
        if (Collections.min(dicesList).getFace() == 1){
            dicesList.remove(0);
            if (Collections.min(dicesList).getFace() == 2){
                dicesList.remove(0);
                if (Collections.min(dicesList).getFace() == 4){
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




}
