package com.clutchapp.a421go.models;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Date;

/**
 * Représente une partie.
 */
public class Game {
    // Properties

    private Date creationDate = new Date();
    private int targetScore = 25;
    private int maxThrowsPerRound = 3;
    private ArrayList<Player> playersList;
    private ArrayList<Player> currentPlayersList;
    private ArrayList<RoundGroup> roundsGroupsList;
    private Player currentPlayer;
    private Round currentRound;

    // Constructor
    /**
     * Le constructeur de la classe.
     * @param creationDate la date de création de la partie.
     * @param targetScore le score-cible à atteindre.
     */
    public Game(Date creationDate, int targetScore, ArrayList<Player> playersList) {
        if (targetScore > 0)
            this.targetScore = targetScore;
        setPlayersList(playersList);
        this.creationDate = creationDate;
        this.roundsGroupsList = new ArrayList<>();
        addRoundGroupToGame();
        this.currentPlayer = playersList.get(0);
        currentRound();
    }

    // Getters

    /**
     * Renvoie la date de création de la partie
     * @return un objet Date
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * Renvoie le score-cible à atteindre
     * @return un entier
     */
    public int getTargetScore() {
        return targetScore;
    }

    /**
     * Renvoie la liste des tours d'une manche
     * @return une Arraylist de RoundGroup
     */
    public ArrayList<RoundGroup> getRoundsGroupsList() {
        return roundsGroupsList;
    }

    /**
     * Renvoie la liste des joueurs de la partie
     * @return une ArrayList de Player
     */
    public ArrayList<Player> getPlayersList() {
        return playersList;
    }

    /**
     * Renvoie le classement en cours de la partie
     * @return une ArrayList de Player
     */
    public ArrayList<Player> getCurrentPlayersList() {
        return currentPlayersList;
    }

    /**
     * Met à jour le classement en cours de la partie
     * @param currentPlayersList Arraylist de Player
     */
    public void setCurrentPlayersList(ArrayList<Player> currentPlayersList) {
        this.currentPlayersList = currentPlayersList;
    }

    /**
     * Renvoie le joueur dont c'est au tour de jouer.
     * @return un objet Player
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Met à jour la date de création de la partie
     * @param creationDate
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Met à jour le score cible de la partie
     * @param targetScore
     */
    public void setTargetScore(int targetScore) {
        this.targetScore = targetScore;
    }

    /**
     * Renvoie le nombre maximum de lancer par tour
     * @return un entier
     */
    public int getMaxThrowsPerRound() {
        return maxThrowsPerRound;
    }

    /**
     * Met à jour le nombre de lancer par tour
     * @param maxThrowsPerRound
     */
    public void setMaxThrowsPerRound(int maxThrowsPerRound) {
        this.maxThrowsPerRound = maxThrowsPerRound;
    }

    /**
     * Met à jour la liste contenant les manches
     * @param roundsGroupsList
     */
    public void setRoundsGroupsList(ArrayList<RoundGroup> roundsGroupsList) {
        this.roundsGroupsList = roundsGroupsList;
    }

    /**
     * Met à jour le joueur actuel de la partie
     * @param currentPlayer
     */
    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    /**
     * Met à jour le tour actuel de la partie
     * @param currentRound
     */
    public void setCurrentRound(Round currentRound) {
        this.currentRound = currentRound;
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

    /**
     * Met à jour la liste des joueurs de la partie
     * @param playersList
     */
    public void setPlayersList(ArrayList<Player> playersList) {
        this.playersList = playersList;
    }

    /**
     * Méthode permettant de passer au joueur suivant ce qui passe aussi au tour suivant
     * @return
     */
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
            roundsList.add(new Round(p, getMaxThrowsPerRound()));
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
    public void addRoundGroupToGame(){
        this.roundsGroupsList.add(roundGroupCreation(roundsCreation(getPlayersList())));
        currentRound();
    }

    /**
     * Renvoie le tour actuel
     * @return
     */
    public Round getCurrentRound() {
        return currentRound;
    }

    /**
     * Méthode permettant l'affichage des informations de la partie (Conseil : utilisable dans les logs)
     * @return
     */
    @NonNull
    @Override
    public String toString() {
        String playersListString = "";

        for (Player player :
                getPlayersList()) {
            playersListString += player.getName() + " ";
        }

        return "Game :\n" +
                "Créée le : " + getCreationDate() + "\n" +
                "Score cible : " + getTargetScore() + "\n" +
                "Nombre maximum de lancers : " + getMaxThrowsPerRound() + "\n" +
                "Joueurs : " + playersListString + "\n" +
                "C'est au tour de " + getCurrentPlayer().getName() + " de jouer.";
    }
}
