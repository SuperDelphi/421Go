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
}
