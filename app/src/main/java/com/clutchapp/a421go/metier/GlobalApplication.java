package com.clutchapp.a421go.metier;

import android.app.Application;
import android.content.Context;

/**
 * Représente l'application ({@link Application}), avec une accessibilité globale.
 * Permet à n'importe quelle classe Java d'accéder à l'instance de l'application,
 * créée au démarrage de celle-ci.
 */
public class GlobalApplication extends Application {
    private static Context appContext;

    /**
     * Méthode appelée au démarrage de l'application.
     */
    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();
    }

    /**
     * @return le contexte de l'application.
     */
    public static Context getAppContext() {
        return appContext;
    }
}
