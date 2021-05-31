package com.clutchapp.a421go.models;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DiceTest {
    private Dice dice = new Dice(4);
    private int expFace = 4;
    List<Integer> possibleFaces = Arrays.asList(1, 2, 3, 4, 5, 6);

    @Test
    void testIsSelected() {
        assertEquals(false, dice.isSelected(), "La face ne doit pas être sélectionnée par défaut.");
    }

    @Test
    void testToggleSelection() {
        dice.toggleSelection();
        assertEquals(true, dice.isSelected(), "La face doit être sélectionnée.");
        dice.toggleSelection();
        assertEquals(false, dice.isSelected(), "La face ne doit pas être sélectionnée.");
    }

    @Test
    void testRoll() {
        for (int i=0; i<500; i++) {
            assertEquals(
                    true,
                    possibleFaces.contains(dice.roll()),
                    "La face doit être comprise entre 1 et 6."
            );
        }
    }

    @Test
    void toggleSelection() {
    }
}