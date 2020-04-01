package com.example.a421go.models;

public class Dice implements Comparable<Dice>{
    private int faceCount = 6;
    private int face = 1;
    private Coordinates coords;
    private boolean selected = false;

    public Dice() {
        this.coords = new Coordinates(0, 0);
    }

    public Dice(int face) {
        this();
        if (face < 1) {
            this.face = 1;
        } else if (face > getFaceCount()) {
            this.face = getFaceCount();
        } else {
            this.face = face;
        }
    }

    public int getFace() {
        return this.face;
    }

    public boolean isSelected() {
        return selected;
    }

    public int getFaceCount() {
        return faceCount;
    }

    public void select() {
        this.selected = true;
    }

    public void desselect() {
        this.selected = false;
    }

    public boolean toggleSelection() {
        return this.selected = !isSelected();
    }

    @Override
    public int compareTo(Dice dice) {
        if (face == dice.face) return 0;
        else if (face > dice.face) return 1;
        else return -1;
    }
}
