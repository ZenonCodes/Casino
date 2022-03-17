package com.github.zipcodewilmington.casino.games.war;

import com.github.zipcodewilmington.casino.cards.Cards;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class WarGameTest {

    @Test
    void createWarDeck() {
        // Given
        int expected = 52;
        WarGame warGame = new WarGame();
        // When
        ArrayList<Cards> arrayListOfCards = warGame.createWarDeck();
        int actual = arrayListOfCards.size();
        // Then
        Assert.assertTrue(expected == actual);
        }

    @Test
    void shuffle() {
        // Given
        WarGame warGame = new WarGame();
        // When
        ArrayList<Cards> arrayListOfCards = warGame.createWarDeck();
        ArrayList<Cards> shuffledArrayListOfCards = warGame.shuffle(arrayListOfCards);
        // Then
        Assert.assertFalse(arrayListOfCards.get(0).getRank() ==
                arrayListOfCards.get(1).getRank() && arrayListOfCards.get(1).getRank() ==
                arrayListOfCards.get(2).getRank() && arrayListOfCards.get(2).getRank() ==
                arrayListOfCards.get(3).getRank() && arrayListOfCards.get(3).getRank() ==
                arrayListOfCards.get(4).getRank() && arrayListOfCards.get(4).getRank() ==
                arrayListOfCards.get(5).getRank() && arrayListOfCards.get(5).getRank() ==
                arrayListOfCards.get(6).getRank());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arrayListOfCards.size(); i++) {
            sb.append(arrayListOfCards.get(i).getRank() + " " + arrayListOfCards.get(i).getSuit() + "\n");
        }
        String output = sb.toString();
        System.out.println(output);
    }
}