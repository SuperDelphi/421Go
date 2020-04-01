package com.example.a421go.controllers;

import android.content.Context;

import com.example.a421go.models.Game;
import com.example.a421go.models.GameDatabase;
import com.example.a421go.models.Player;
import com.example.a421go.models.Round;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

/**
 * Contrôleur qui gère, entre autres, le menu.
 */
public class GameController extends Controller {

    private static GameController instance = null;
    private Game newGame;

    /**
     * Constructeur protégé de la classe GameController.
     */
    protected GameController(Context context) {
        super(context);
    }


    //Getters
    public Game getNewGame() {
        return newGame;
    }

    /**
     * Si aucune instance n'existe, crée une instance de la classe. Sinon,
     * retourne l'instance existante.
     * @return L'unique instance de la classe.
     */
    public static GameController getInstance(Context context) {
        if (GameController.instance == null) {
            GameController.instance = new GameController(context);
        }
        return instance;
    }

    /**
     * Crée une nouvelle partie à partir des informations saisies par
     * l'utilisateur.
     */
    public void playGame(int targetScore, Date creationDate, ArrayList<Player> listPlayers, ArrayList<Round> roundsList) {
        newGame = new Game(creationDate, targetScore, listPlayers, roundsList);
    }

    /**
     * Relance une partie dans les mêmes conditions de la dernière
     * partie lancée.
     */
    public void replayLastGame() {}

}
