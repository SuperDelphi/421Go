package com.example.a421go.controllers;

/**
 * Contrôleur qui gère les opérations relatives aux joueurs.
 */
public class PlayerController {

    private static PlayerController instance = null;

    /**
     * Constructeur protégé de la classe PlayerController.
     */
    protected PlayerController() {
        super();
    }

    /**
     * Si aucune instance n'existe, crée une instance de la classe. Sinon,
     * retourne l'instance existante.
     * @return L'unique instance de la classe.
     */
    public static PlayerController getInstance() {
        if (PlayerController.instance == null) {
            PlayerController.instance = new PlayerController();
        }
        return instance;
    }

}
