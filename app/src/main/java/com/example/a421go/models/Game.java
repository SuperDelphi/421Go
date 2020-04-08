package com.example.a421go.models;

import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

/**
 * Représente une partie.
 */
public class Game {
    // Properties

    private Date creationDate = new Date();
    private int targetScore = 25;
    private ArrayList<Player> playersList;
    private ArrayList<RoundGroup> roundsGroupsList;
    private Player currentPlayer;
    private Round currentRound;

    // Constructors

    /**
     * Le constructeur de la classe.
     */

    /**
     * Le constructeur de la classe.
     * @param creationDate la date de création de la partie.
     * @param targetScore le score-cible à atteindre.
     */
    public Game(Date creationDate, int targetScore, ArrayList<Player> playersList, ArrayList<RoundGroup> roundsGroupsList) {
        if (targetScore > 0)
            this.targetScore = targetScore;
        setPlayersList(playersList);
        this.creationDate = creationDate;
        this.roundsGroupsList = roundsGroupsList;
        addRoundGroupToGame();
        this.currentPlayer = playersList.get(0);
        currentRound();
    }

    // Getters

    /**
     * @return La date de création de la partie.
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * @return Le score-cible à atteindre.
     */
    public int getTargetScore() {
        return targetScore;
    }

    /**
     * @return La liste des tours d'une manche
     */
    public ArrayList<RoundGroup> getRoundsGroupsList() {
        return roundsGroupsList;
    }

    /**
     * @return La liste des joueurs de la partie.
     */
    public ArrayList<Player> getPlayersList() {
        return playersList;
    }

    /**
     * @return Le joueur dont c'est au tour de jouer.
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Renvoie le dernier élément de la liste RoudsGroupslist
     * @return
     */
    public RoundGroup getCurrentRoundGroup() {
        return roundsGroupsList.get(roundsGroupsList.size()-1);
    }

    /**
     * Renvoie le tour en cours en fonction de la manche et du joueur
     * @return
     */
    public void currentRound(){
        for (Round r : getCurrentRoundGroup().getRoundsList()){
            if (r.getPlayer() == currentPlayer){
                currentRound = r;
            }
        }
    }

    public void setPlayersList(ArrayList<Player> playersList) {
        this.playersList = playersList;
    }

    public Player nextPlayer() {
        int cPlayerIndex = getPlayersList().indexOf(getCurrentPlayer());
        Player nextPlayer;

        if (cPlayerIndex >= getPlayersList().size() - 1) {
            nextPlayer = getPlayersList().get(0);
        } else {
            nextPlayer = getPlayersList().get(cPlayerIndex + 1);
        }

        this.currentPlayer = nextPlayer;
        currentRound();
        return getCurrentPlayer();
    }

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
        this.roundsGroupsList.add(roundGroupCreation(roundsCreation(getPlayersList())));
    }

    public Round getCurrentRound() {
        return currentRound;
    }
}
