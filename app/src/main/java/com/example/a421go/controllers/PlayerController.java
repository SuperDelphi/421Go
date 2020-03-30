package com.example.a421go.controllers;

import android.content.Context;

/**
 * Contrôleur qui gère les opérations relatives aux joueurs.
 */
public class PlayerController extends Controller {

    private static PlayerController instance = null;

    /**
     * Constructeur protégé de la classe PlayerController.
     */
    protected PlayerController(Context context) {
        super(context);
    }

    /**
     * Si aucune instance n'existe, crée une instance de la classe. Sinon,
     * retourne l'instance existante.
     * @return L'unique instance de la classe.
     */
    public static PlayerController getInstance(Context context) {
        if (PlayerController.instance == null) {
            PlayerController.instance = new PlayerController(context);
        }
        return instance;
    }

}
