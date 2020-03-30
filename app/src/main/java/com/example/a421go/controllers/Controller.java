package com.example.a421go.controllers;

import android.content.Context;

import com.example.a421go.models.GameDatabase;

public class Controller {
    protected Context context;
    protected GameDatabase database;

    protected Controller(Context context) {
        super();
        this.database = new GameDatabase(context);
    }

    public Context getContext() {
        return context;
    }

    public GameDatabase getDatabase() {
        return database;
    }
}
