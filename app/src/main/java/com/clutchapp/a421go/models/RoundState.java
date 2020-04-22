package com.clutchapp.a421go.models;

/**
 * Représente l'état d'un tour {@link Round}.
 */
public class RoundState {
    private int throwsLeft;

    /**
     * Constructeur de la classe.
     * @param throwsLeft le nombre de tour initiaux restants.
     */
    public RoundState(int throwsLeft) {
        this.throwsLeft = throwsLeft;
    }

    /**
     * Enlève un tour restant.
     * @return le nombre de tours restants.
     */
    public int decrease() {
        return --this.throwsLeft;
    }

    /**
     * @return le nombre de tours restants.
     */
    public int getThrowsLeft() {
        return this.throwsLeft;
    }
}