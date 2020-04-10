package com.example.a421go.models;

public class RoundState {
    private int throwsLeft;

    public RoundState(int throwsLeft) {
        this.throwsLeft = throwsLeft;
    }

    public int decrease() {
        return --this.throwsLeft;
    }

    public int getThrowsLeft() {
        return this.throwsLeft;
    }
}