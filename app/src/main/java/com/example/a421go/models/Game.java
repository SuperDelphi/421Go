package com.example.a421go.models;

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
    private ArrayList<Round> roundsList;
    private Player currentPlayer;

    // Constructors

    /**
     * Le constructeur de la classe.
     */
    public Game() {}

    /**
     * Le constructeur de la classe.
     * La date de création de la partie équivaut alors
     * au timestamp de l'instanciation de la classe.
     * @param targetScore le score-cible à atteindre.
     */
    public Game(int targetScore) {
        if (targetScore > 0)
            this.targetScore = targetScore;
    }

    /**
     * Le constructeur de la classe.
     * @param creationDate la date de création de la partie.
     * @param targetScore le score-cible à atteindre.
     */
    public Game(Date creationDate, int targetScore, ArrayList<Player> playersList, ArrayList<Round> roundsList) {
        this(targetScore);
        this.creationDate = creationDate;
        this.playersList = playersList;
        this.roundsList = roundsList;
        this.currentPlayer = playersList.get(0);
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
    public ArrayList<Round> getRoundsList() {
        return roundsList;
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

    // Public methods

    public Player nextPlayer() {
        int cPlayerIndex = getPlayersList().indexOf(getCurrentPlayer());
        Player nextPlayer;

        if (cPlayerIndex >= getPlayersList().size() - 1) {
            nextPlayer = getPlayersList().get(0);
        } else {
            nextPlayer = getPlayersList().get(cPlayerIndex + 1);
        }

        this.currentPlayer = nextPlayer;
        return getCurrentPlayer();
    }
}
