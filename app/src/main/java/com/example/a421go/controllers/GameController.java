package com.example.a421go.controllers;

import android.content.Context;
import android.util.Log;

import com.example.a421go.metier.PlayerComparator;
import com.example.a421go.metier.SimpleBoard;
import com.example.a421go.models.Game;
import com.example.a421go.models.Player;
import com.example.a421go.models.Round;
import com.example.a421go.models.RoundGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

/**
 * Contrôleur qui gère, entre autres, le menu.
 */
public class GameController extends Controller {

    private static GameController instance = null;
    private Game game;

    /**
     * Constructeur protégé de la classe GameController.
     */
    protected GameController(Context context) {
        super(context);
    }

    //Méthodes publiques

    //Getters
    public Game getGame() {
        return game;
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

    public Player getCurrentPlayer() {
        return GameController.getInstance(getContext()).getGame().getCurrentPlayer();
    }

    public Round getCurrentRound() {
        return getGame().getCurrentRound();
    }

    /**
     * Crée une nouvelle partie à partir des informations saisies par
     * l'utilisateur.
     */
    public void playGame(int targetScore, Date creationDate, ArrayList<Player> listPlayers, ArrayList<RoundGroup> roundsGroupList) {
        game = new Game(creationDate, targetScore, listPlayers, roundsGroupList);
    }

    /**
     * Relance une partie dans les mêmes conditions de la dernière
     * partie lancée.
     */
    public void replayLastGame() {}


    public ArrayList<Player> playersRanking(){
        ArrayList<RoundGroup> roundsGroupsList = game.getRoundsGroupsList();
        ArrayList<Player> playersList = game.getPlayersList();
        for (RoundGroup RG : roundsGroupsList){
            for (int cpt = 0; cpt < playersList.size(); cpt++) {
                int newScoreFinal = playersList.get(cpt).getScoreFinal() + RG.getRoundsList().get(cpt).getGain();
                playersList.get(cpt).setScoreFinal(newScoreFinal);
            }
        }
        Collections.sort(playersList, new PlayerComparator());
        return playersList;
    }
}
