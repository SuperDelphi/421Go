package com.example.a421go.models;

import java.util.Date;

/**
 * Représente une partie.
 */
public class Game {
    // Properties

    private Date creationDate = new Date();
    private int targetScore = 25;

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
    public Game(Date creationDate, int targetScore) {
        this(targetScore);
        this.creationDate = creationDate;
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
}
