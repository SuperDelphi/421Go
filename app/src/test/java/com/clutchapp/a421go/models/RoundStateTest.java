package com.clutchapp.a421go.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoundStateTest {
    private RoundState roundState = new RoundState(3);

    @Test
    void getThrowsLeft() {
        assertEquals(3, roundState.getThrowsLeft(), "Le nombre de lancers restants doit être égal à 3.");
    }

    @Test
    void decrease() {
        roundState.decrease();
        assertEquals(2, roundState.getThrowsLeft(), "Le nombre de lancers restants doit avoir diminué (à 2).");
    }
}