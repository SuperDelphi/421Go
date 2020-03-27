package com.example.a421go.models;

/**
 * Représente un joueur.
 */
public class Player {
    // Properties

    private String name;
    private int victories = 0;

    // Constructors

    /**
     * Le constructeur de la classe.
     * Si le nom du joueur est une chaîne de caractères vide, alors
     * un nom sera automatiquement généré.
     * @param name le nom du joueur.
     */
    public Player(String name) {
        String finalName = name;

        if (finalName == "") {
            finalName = "Ninja" + Math.floor(Math.random() * 9999);
        }

        this.name = finalName;
    }

    // Methods

    /**
     * Ajoute une victoire supplémentaire au nombre de victoires du joueur.
     */
    public void addVictory() {
        this.victories++;
    }

    // Getters

    /**
     * @return le nom du joueur.
     */
    public String getName() {
        return name;
    }

    /**
     * @return le nombre de victoires obtenues par le joueur.
     */
    public int getVictories() {
        return victories;
    }
}
