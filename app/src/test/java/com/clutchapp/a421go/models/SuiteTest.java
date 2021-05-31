package com.clutchapp.a421go.models;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SuiteTest {
    private ArrayList<Dice> dices = new ArrayList<>();
    private Suite suite;

    public SuiteTest() {
        dices.add(new Dice(1));
        dices.add(new Dice(2));
        dices.add(new Dice(3));

        suite = new Suite(dices);
    }

    @Test
    public void testGetPoints() {
        assertEquals(2, suite.getPoints());
    }
}