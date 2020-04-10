package com.example.a421go.models;

/**
 * Représente un joueur.
 */
public class Player implements Comparable<Player>{
    // Properties

    private String name;
    private int victories = 0;
    private int scoreFinal = 0;

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

    /**
     * @return le score final contenant la somme du score du joueur
     */
    public int getScoreFinal() {
        return scoreFinal;
    }

    /**
     * met à jour le score final d'un joueur
     * @param scoreFinal
     */
    public void setScoreFinal(int scoreFinal) {
        this.scoreFinal = scoreFinal;
    }

    @Override
    public int compareTo(Player p) {
        if (scoreFinal == p.scoreFinal) return 0;
        else if (scoreFinal < p.scoreFinal) return 1;
        else return -1;
    }
}
