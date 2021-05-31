package com.clutchapp.a421go.models;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AutreTest {
    private ArrayList<Dice> dices = new ArrayList<>();
    private Autre autre;

    public AutreTest() {
        dices.add(new Dice(2));
        dices.add(new Dice(3));
        dices.add(new Dice(8));

        autre = new Autre(dices);
    }

    @Test
    public void testGetPoints() {
        assertEquals(1, autre.getPoints());
    }
}