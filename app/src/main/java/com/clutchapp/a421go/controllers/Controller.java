package com.clutchapp.a421go.controllers;

import com.clutchapp.a421go.metier.GlobalApplication;
import com.clutchapp.a421go.models.GameDatabase;

/**
 * Représente la classe mère de tous les contrôleurs de l'application.
 */
public class Controller {
    protected GameDatabase database;

    /**
     * Constructeur de la classe.
     */
    protected Controller() {
        super();
        this.database = new GameDatabase(GlobalApplication.getAppContext());
    }

    /**
     * @return une instance de la classe {@link GameDatabase}.
     */
    public GameDatabase getDatabase() {
        return database;
    }
}
