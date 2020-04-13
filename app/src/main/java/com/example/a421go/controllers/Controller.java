package com.example.a421go.controllers;

import android.content.Context;

import com.example.a421go.metier.GlobalApplication;
import com.example.a421go.models.GameDatabase;

public class Controller {
    protected GameDatabase database;

    protected Controller() {
        super();
        this.database = new GameDatabase(GlobalApplication.getAppContext());
    }

    public GameDatabase getDatabase() {
        return database;
    }
}
