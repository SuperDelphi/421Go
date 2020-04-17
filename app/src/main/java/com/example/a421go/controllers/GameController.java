package com.example.a421go.controllers;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.a421go.metier.PlayerComparator;
import com.example.a421go.metier.SimpleBoard;
import com.example.a421go.models.Game;
import com.example.a421go.models.Player;
import com.example.a421go.models.Round;
import com.example.a421go.models.RoundGroup;
import com.example.a421go.views.GameActivity;
import com.example.a421go.views.RankingActivity;

import java.util.ArrayList;
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
    protected GameController() {
        super();
    }

    //Méthodes publiques

    //Getters

    /**
     * Renvoie la propriété game
     * @return un objet Game
     */
    public Game getGame() {
        return game;
    }

    /**
     * Si aucune instance n'existe, crée une instance de la classe. Sinon,
     * retourne l'instance existante.
     * @return L'unique instance de la classe.
     */
    public static GameController getInstance() {
        if (GameController.instance == null) {
            GameController.instance = new GameController();
        }
        return instance;
    }

    /**
     * Renvoie le joueur en cours
     * @return un objet Player
     */
    public Player getCurrentPlayer() {
        return GameController.getInstance().getGame().getCurrentPlayer();
    }

    /**
     * Renvoie le tour en cours
     * @return un objet Round
     */
    public Round getCurrentRound() {
        return getGame().getCurrentRound();
    }

    /**
     * Renvoie le nombre maximum de lancer par tour
     * @return un nombre entier
     */
    public int getMaxThrowsPerRound() {
        return getGame().getMaxThrowsPerRound();
    }

    /**
     * Crée une nouvelle partie à partir des informations saisies par
     * l'utilisateur.
     */
    public void playGame(Context context, int targetScore, Date creationDate, ArrayList<Player> listPlayers, ArrayList<RoundGroup> roundsGroupList) {
        game = new Game(creationDate, targetScore, listPlayers, roundsGroupList);
        Intent intent = new Intent(context, GameActivity.class);
        context.startActivity(intent);
    }

    /**
     * Relance une partie dans les mêmes conditions de la dernière
     * partie lancée.
     */
    public void replayLastGame() {}


    /**
     * Calcul les résultats des joueurs et les classe en fonction de leurs scores finaux
     * @return une Arraylist de Player
     */
    public ArrayList<Player> playersRanking(){
        ArrayList<RoundGroup> roundsGroupsList = game.getRoundsGroupsList();
        ArrayList<Player> playersList = game.getPlayersList();
        for (RoundGroup RG : roundsGroupsList){
            for (int cpt = 0; cpt < playersList.size(); cpt++) {
                int newScoreFinal = playersList.get(cpt).getScoreFinal() + RG.getRoundsList().get(cpt).getGain();
                playersList.get(cpt).setScoreFinal(newScoreFinal);
            }
        }
        return playersList;
    }

    public Boolean endGameTest(){
        Boolean test = false;
        ArrayList<Player> currentPlayersList = playersRanking();
        game.setCurrentPlayersList(currentPlayersList);
        for(Player p : game.getCurrentPlayersList()){
            if (p.getScoreFinal() >= getGame().getTargetScore()){
                test = true;
            }
        }

        game.setPlayersList(reInitGlobalScore());
        return test;
    }

    public ArrayList<Player> reInitGlobalScore(){
        ArrayList<Player> playersList = game.getPlayersList();
        for (Player p : playersList){
            p.setScoreFinal(0);
        }
        return playersList;
    }

    /**
     * Renvoie le nombre de lancers restant
     * @return un nombre entier
     */
    public int getThrowsLeft(){
        return getCurrentRound().getState().getThrowsLeft();
    }

    /**
     * Soustrait un lancer aux lancers restants
     */
    public void throwsSubstract(){
        getCurrentRound().getState().decrease();
    }

    public void checkGameState(Context context){
        Round lastRound = getGame().getCurrentRoundGroup().getRoundsList().get(getGame().getCurrentRoundGroup().getRoundsList().size() - 1);
        if (lastRound.getCombination() != null) {
            if (endGameTest()){
                Intent intent = new Intent(context, RankingActivity.class);
                context.startActivity(intent);
            } else {
                getGame().addRoundGroupToGame();
            }
        }
    }
}
