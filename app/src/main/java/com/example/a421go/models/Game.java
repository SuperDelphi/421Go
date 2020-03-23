package com.example.a421go.models;

import java.util.Date;

public class Game {
    // Properties

    private String name;
    private Date creationDate = new Date();
    private int targetScore = 25;

    // Constructors

    public Game() {}

    public Game(int targetScore) {
        if (targetScore > 0)
            this.targetScore = targetScore;
    }

    public Game(Date creationDate, int targetScore) {
        this(targetScore);
        this.creationDate = creationDate;
    }

    // Getters

    public String getName() {
        return name;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public int getTargetScore() {
        return targetScore;
    }
}
