package com.github.zipcodewilmington.casino.games.blackjack;

import com.github.zipcodewilmington.casino.cards.Cards;
import com.github.zipcodewilmington.casino.games.war.WarGame;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BlackJackGameTest {

    @Test
    void createBlackJackDeck() {
        // Given
        int expected = 52;
        BlackJackGame blackJackGame = new BlackJackGame();
        // When
        ArrayList<Cards> arrayListOfCards = blackJackGame.createBlackJackDeck();
        int actual = arrayListOfCards.size();
        // Then
        Assert.assertTrue(expected == actual);
    }

    @Test
    void shuffle() {
        // Given
        BlackJackGame blackJackGame = new BlackJackGame();
        // When
        ArrayList<Cards> arrayListOfCards = blackJackGame.createBlackJackDeck();
        ArrayList<Cards> shuffledArrayListOfCards = blackJackGame.shuffle(arrayListOfCards);
        // Then
        Assert.assertFalse(shuffledArrayListOfCards.get(0).getRank() ==
                shuffledArrayListOfCards.get(1).getRank() && shuffledArrayListOfCards.get(1).getRank() ==
                shuffledArrayListOfCards.get(2).getRank() && shuffledArrayListOfCards.get(2).getRank() ==
                shuffledArrayListOfCards.get(3).getRank() && shuffledArrayListOfCards.get(3).getRank() ==
                shuffledArrayListOfCards.get(4).getRank() && shuffledArrayListOfCards.get(4).getRank() ==
                shuffledArrayListOfCards.get(5).getRank() && shuffledArrayListOfCards.get(5).getRank() ==
                shuffledArrayListOfCards.get(6).getRank());
    }

    @Test
    void deal() {
        // Given
        BlackJackGame blackJackGame = new BlackJackGame();
        ArrayDeque<Cards> deck = blackJackGame.generateBlackJackDeck();
        int expectedSizePlayer = 2;
        int expectedSizeDealer = 2;
        int expectedSizeDeck = 48;
        // When
        blackJackGame.deal(deck);
        int actualSizePlayer = blackJackGame.handPlayer.size();
        blackJackGame.handPlayer.clear();
        int actualSizeDealer = blackJackGame.handDealer.size();
        blackJackGame.handDealer.clear();
        int actualSizeDeck = deck.size();
        deck.clear();
        // Then
        Assert.assertEquals(expectedSizePlayer, actualSizePlayer);
        Assert.assertEquals(expectedSizeDealer, actualSizeDealer);
        Assert.assertEquals(expectedSizeDeck, actualSizeDeck);
    }
}