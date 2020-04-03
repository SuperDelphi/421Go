package com.example.a421go.models;

/**
 * Représente
 */
public class Round {

    private Player player;
    private int gain;

    /**
     * Le constructeur de la classe.
     * @param player le joueur qui joue pendant ce tour.
     */
    public Round(Player player) {
        this.player = player;
    }

    /**
     * @return le gain obtenu pendant ce tour.
     */
    public int getGain() {
        return gain;
    }

    /**
     * Définit le gain obtenu pendant ce tour.
     * @param gain le gain obtenu pendant ce tour.
     */
    public void setGain(int gain) {
        this.gain = gain;
    }

    /**
     * Ajoute un montant défini au gain obtenu pendant ce tour.
     * @param amount le montant à ajouter au gain.
     * @return la nouvelle valeur du gain après l'ajout du montant.
     */
    public int addGain(int amount) {
        this.gain += amount;
        return getGain();
    }

    public Player getPlayer() {
        return player;
    }
}
