package com.clutchapp.a421go.models;

/**
 * Représente un tour d'un joueur
 */
public class Round {

    private Player player;
    private int gain;
    private Combination combination;
    private RoundState state;

    /**
     * Le constructeur de la classe.
     * @param player le joueur qui joue pendant ce tour.
     */
    public Round(Player player, int maxThrows) {
        this.player = player;
        this.state = new RoundState(maxThrows);
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
     * Permet de mettre à jour le joueur du tour
     * @param player
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Permet de récupérer la combinaison du tour
     * @return un objet Combination
     */
    public Combination getCombination() {
        return combination;
    }

    /**
     * Permet de mettre à jour la combinaison du tour
     * @param combination
     */
    public void setCombination(Combination combination) {
        this.combination = combination;
    }

    /**
     * Permet de récupérer l'objet State contenant l'état du tour
     * @return un objet State
     */
    public RoundState getState() {
        return state;
    }

    /**
     * Permet de mettre à jour l'objet State
     * @param state
     */
    public void setState(RoundState state) {
        this.state = state;
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

    /**
     * Permet de récupérer le joueur du tour
     * @return un objet Player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Permet l'affichage des caractériques du tour (Conseil : utilisable dans les logs)
     * @return une chaine de caractére
     */
    @Override
    public String toString() {
        return "Round{" +
                "player=" + player +
                ", gain=" + gain +
                ", combination=" + combination +
                '}';
    }
}
