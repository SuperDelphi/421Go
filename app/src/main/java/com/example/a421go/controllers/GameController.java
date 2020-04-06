package com.example.a421go.controllers;

import android.content.Context;

import com.example.a421go.metier.SimpleBoard;
import com.example.a421go.models.Game;
import com.example.a421go.models.Player;
import com.example.a421go.models.Round;
import com.example.a421go.models.RoundGroup;

import java.util.ArrayList;
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

    //Méthodes privées

    /**
     * Créé et renvoie une Arraylist de Round en fonction des joueurs de la partie
     * @param playersList liste des joueurs de la partie
     * @return l'objet roundlist qui est la liste des tours de la manche
     */
    private ArrayList<Round> roundsCreation(ArrayList<Player> playersList){
        ArrayList<Round> roundsList = new ArrayList<Round>();
        for(Player p : playersList){
            roundsList.add(new Round(p));
        }
        return roundsList;
    }

    /**
     * Créé et renvoie un objet RoundGroup (ou appelé "manche") qui contient sa liste de tours
     * @param roundsList liste des tours de la manche
     * @return un objet RoundGroup
     */
    private RoundGroup roundGroupCreation(ArrayList<Round> roundsList){
        return new RoundGroup(roundsList);
    }

    /**
     * Ajout à la liste RoundsGroupsList de game, un nouvel objet RoundGroup qui prend un paramêtre
     * un nouvel objet RoundsList qui prend en paramêtre la liste des joueurs
     */
    private void addRoundGroupToGame(){
        game.getRoundsGroupsList().add(roundGroupCreation(roundsCreation(game.getPlayersList())));
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

}
