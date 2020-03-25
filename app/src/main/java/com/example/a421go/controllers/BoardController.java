package com.example.a421go.controllers;

/**
 * Contrôleur qui gère le cours d'une partie.
 */
public class BoardController {

    private static BoardController instance = null;

    /**
     * Constructeur protégé de la classe BoardController.
     */
    protected BoardController() {
        super();
    }

    /**
     * Si aucune instance n'existe, crée une instance de la classe. Sinon,
     * retourne l'instance existante.
     * @return L'unique instance de la classe.
     */
    public static BoardController getInstance() {
        if (BoardController.instance == null) {
            BoardController.instance = new BoardController();
        }
        return instance;
    }

}
