package com.example.a421go.controllers;

import android.content.Context;

/**
 * Contrôleur qui gère le cours d'une partie.
 */
public class BoardController extends Controller {

    private static BoardController instance = null;

    /**
     * Constructeur protégé de la classe BoardController.
     */
    protected BoardController(Context context) {
        super(context);
    }

    /**
     * Si aucune instance n'existe, crée une instance de la classe. Sinon,
     * retourne l'instance existante.
     * @return L'unique instance de la classe.
     */
    public static BoardController getInstance(Context context) {
        if (BoardController.instance == null) {
            BoardController.instance = new BoardController(context);
        }
        return instance;
    }

}
