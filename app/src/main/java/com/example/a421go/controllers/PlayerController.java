package com.example.a421go.controllers;

import android.content.Context;

/**
 * Contrôleur qui gère les opérations relatives aux joueurs.
 */
public class PlayerController extends Controller {

    private static PlayerController instance = null;

    /**
     * Constructeur protégé de la classe {@link PlayerController}.
     */
    protected PlayerController() {
        super();
    }

    /**
     * Si aucune instance n'existe, crée une instance de la classe. Sinon,
     * retourne l'instance existante.
     *
     * @return L'unique instance de la classe.
     */
    public static PlayerController getInstance() {
        if (PlayerController.instance == null) {
            PlayerController.instance = new PlayerController();
        }
        return instance;
    }

}
