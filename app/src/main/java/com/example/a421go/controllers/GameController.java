package com.example.a421go.controllers;

/**
 * Contrôleur qui gère, entre autres, le menu.
 */
public class GameController {

    private static GameController instance = null;

    /**
     * Constructeur protégé de la classe GameController.
     */
    protected GameController() {
        super();
    }

    /**
     * Si aucune instance n'existe, crée une instance de la classe. Sinon,
     * retourne l'instance existante.
     * @return L'unique instance de la classe.
     */
    public static GameController getInstance() {
        if (GameController.instance == null) {
            GameController.instance = new GameController();
        }
        return instance;
    }

    public void creerPartie() {}

}
