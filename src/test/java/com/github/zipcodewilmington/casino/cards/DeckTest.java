package com.github.zipcodewilmington.casino.cards;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {

    @Test
    void createDeck() {
        int expected = 52;
        ArrayList<Cards> actualCards = Deck.createDeck();
        int actual = actualCards.size();

        Assert.assertEquals(expected, actual);
    }


    @Test
    void testShuffle() {
        ArrayList<Cards> actualCards = Deck.createDeck();
        Collections.shuffle(actualCards);
        System.out.println(actualCards);
    }

    @Test
    void testToString() {
        ArrayList<Cards> actualCards = Deck.createDeck();
        System.out.println(Arrays.deepToString(actualCards.toArray()));
    }
}