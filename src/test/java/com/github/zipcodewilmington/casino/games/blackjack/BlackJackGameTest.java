
package com.github.zipcodewilmington.casino.games.blackjack;

import com.github.zipcodewilmington.casino.cards.Cards;
import com.github.zipcodewilmington.casino.cards.Rank;
import com.github.zipcodewilmington.casino.cards.Suit;
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

    @Test
    void hit() {
        // Given
        BlackJackGame blackJackGame = new BlackJackGame();
        ArrayDeque<Cards> deck = blackJackGame.generateBlackJackDeck();
        Integer[] testSumPlayerHit = {0};
        int expectedSizePlayer = 3;
        int expectedSizeDealer = 2;
        int expectedSizeDeck = 47;
        // When
        blackJackGame.deal(deck);
        blackJackGame.hit(blackJackGame.handPlayer, deck, testSumPlayerHit);
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
        Assert.assertTrue(testSumPlayerHit[0] > 0);
    }

    @Test
    void addCardToSum() {
        // Given
        Integer[] testSumPlayer = {0};
        Integer[] testSumDealer = {0};
        BlackJackGame blackJackGame = new BlackJackGame();
        Cards cardP1 = new Cards(Rank.JACK, Suit.HEARTS, 10);
        Cards cardP2 = new Cards(Rank.KING, Suit.HEARTS, 12);
        Cards cardD1 = new Cards(Rank.ACE, Suit.HEARTS, 13);
        Cards cardD2 = new Cards(Rank.SEVEN, Suit.HEARTS, 6);
        int expectedSumPlayer1 = 0;
        int expectedSumPlayer2 = 10;
        int expectedSumPlayer3 = 20;
        int expectedSumDealer1 = 1;
        int expectedSumDealer2 = 8;
        // When
        Integer[] actualSumPlayer1Arr = testSumPlayer;
        int actualSumPlayer1 = actualSumPlayer1Arr[0];
        blackJackGame.addCardToSum(cardP1,testSumPlayer);
        Integer[] actualSumPlayer2Arr = testSumPlayer;
        int actualSumPlayer2 = actualSumPlayer1Arr[0];
        blackJackGame.addCardToSum(cardP2,testSumPlayer);
        Integer[] actualSumPlayer3Arr = testSumPlayer;
        int actualSumPlayer3 = actualSumPlayer1Arr[0];
        blackJackGame.addCardToSum(cardD1,testSumDealer);
        Integer[] actualSumDealer1Arr = testSumDealer;
        int actualSumDealer1 = actualSumDealer1Arr[0];
        blackJackGame.addCardToSum(cardD2,testSumDealer);
        Integer[] actualSumDealer2Arr = testSumDealer;
        int actualSumDealer2 = actualSumDealer1Arr[0];
        // Then
        Assert.assertEquals(expectedSumPlayer1, actualSumPlayer1);
        Assert.assertEquals(expectedSumPlayer2, actualSumPlayer2);
        Assert.assertEquals(expectedSumPlayer3, actualSumPlayer3);
        Assert.assertEquals(expectedSumDealer1, actualSumDealer1);
        Assert.assertEquals(expectedSumDealer2, actualSumDealer2);
    }

    @Test
    void sumStartingCards() {
        // Given
        BlackJackGame blackJackGame = new BlackJackGame();
        Cards cardP1 = new Cards(Rank.JACK, Suit.HEARTS, 10);
        Cards cardP2 = new Cards(Rank.KING, Suit.HEARTS, 12);
        Cards cardD1 = new Cards(Rank.ACE, Suit.HEARTS, 13);
        Cards cardD2 = new Cards(Rank.SEVEN, Suit.HEARTS, 6);
        int expectedSumPlayer = 20;
        int expectedSumDealer = 8;
        // When
        blackJackGame.handPlayer.addFirst(cardP1);
        blackJackGame.handPlayer.addFirst(cardP2);
        blackJackGame.handDealer.addFirst(cardD1);
        blackJackGame.handDealer.addFirst(cardD2);
        blackJackGame.sumStartingCards();
        int actualSumPlayer = blackJackGame.sumPlayer[0];
        int actualSumDealer = blackJackGame.sumDealer[0];
        blackJackGame.sumPlayer[0] = 0;
        blackJackGame.sumDealer[0] = 0;
        blackJackGame.handPlayer.clear();
        blackJackGame.handDealer.clear();
        // Then
        Assert.assertEquals(expectedSumPlayer, actualSumPlayer);
        Assert.assertEquals(expectedSumDealer, actualSumDealer);
    }

    @Test
    void buildOutputString() {
        // Given
        BlackJackGame blackJackGame = new BlackJackGame();
        ArrayDeque<Cards> handDealerTest = new ArrayDeque<Cards>();
        Integer[] sumDealerTest = {0};
        Cards cardP1 = new Cards(Rank.JACK, Suit.HEARTS, 10);
        Cards cardP2 = new Cards(Rank.KING, Suit.HEARTS, 12);
        Cards cardP3 = new Cards(Rank.ACE, Suit.HEARTS, 13);
        String expectedString = "DEALER HAS JACK HEARTS, KING HEARTS, ACE HEARTS, TOTAL: 21";
        // WHEN
        handDealerTest.add(cardP1);
        blackJackGame.addCardToSum(cardP1, sumDealerTest);
        handDealerTest.add(cardP2);
        blackJackGame.addCardToSum(cardP2, sumDealerTest);
        handDealerTest.add(cardP3);
        blackJackGame.addCardToSum(cardP3, sumDealerTest);
        String actualString = blackJackGame.buildOutputString(handDealerTest, sumDealerTest);
        // THEN
        Assert.assertEquals(expectedString, actualString);
    }

    @Test
    void checkForNatural() {
        // Given
        BlackJackGame blackJackGame = new BlackJackGame();
        ArrayDeque<Cards> handPlayerTestNatural1 = new ArrayDeque<Cards>();
        ArrayDeque<Cards> handPlayerTestNatural2 = new ArrayDeque<Cards>();
        Cards cardP11 = new Cards(Rank.JACK, Suit.HEARTS, 10);
        Cards cardP12 = new Cards(Rank.ACE, Suit.HEARTS, 13);
        Cards cardP21 = new Cards(Rank.SEVEN, Suit.HEARTS, 6);
        Cards cardP22 = new Cards(Rank.ACE, Suit.HEARTS, 13);
        int expected1 = 21;
        int expected2 = 18;
        // WHEN
        handPlayerTestNatural1.add(cardP11);
        handPlayerTestNatural1.add(cardP12);
        handPlayerTestNatural2.add(cardP21);
        handPlayerTestNatural2.add(cardP22);
        int actual1 = blackJackGame.checkForNatural(handPlayerTestNatural1);
        int actual2 = blackJackGame.checkForNatural(handPlayerTestNatural2);
        // THEN
        Assert.assertEquals(expected1, actual1);
        Assert.assertEquals(expected2, actual2);
    }

    @Test
    void checkForAceTest() {
        // Given
        BlackJackGame blackJackGame = new BlackJackGame();
        Cards card1 = new Cards(Rank.JACK, Suit.HEARTS, 10);
        Cards card2 = new Cards(Rank.ACE, Suit.HEARTS, 13);
        String expected1 = "NO";
        String expected2 = "YES";
        // WHEN
        String actual1 = blackJackGame.checkForAce(card1);
        String actual2 = blackJackGame.checkForAce(card2);
        // THEN
        Assert.assertEquals(expected1, actual1);
        Assert.assertEquals(expected2, actual2);
    }

    @Test
    void dealerAceDecision() {
        // Given
        BlackJackGame blackJackGame = new BlackJackGame();
        Cards card1 = new Cards(Rank.NINE, Suit.HEARTS, 8);
        Cards card2 = new Cards(Rank.ACE, Suit.HEARTS, 13);
        Cards card3 = new Cards(Rank.FOUR, Suit.HEARTS, 3);
        Cards card4 = new Cards(Rank.ACE, Suit.CLUBS, 13);
        int expected1 = 20;
        int expected2 = 5;
        // WHEN
        blackJackGame.handDealer.add(card1);
        blackJackGame.addCardToSum(card1, blackJackGame.sumDealer);
        blackJackGame.handDealer.add(card2);
        blackJackGame.addCardToSum(card2, blackJackGame.sumDealer);
        blackJackGame.dealerAceDecision();
        int actual1 = blackJackGame.sumDealer[0];
        blackJackGame.handDealer.clear();
        blackJackGame.sumDealer[0] = 0;
        blackJackGame.handDealer.add(card3);
        blackJackGame.addCardToSum(card3, blackJackGame.sumDealer);
        blackJackGame.handDealer.add(card4);
        blackJackGame.addCardToSum(card4, blackJackGame.sumDealer);
        blackJackGame.dealerAceDecision();
        int actual2 = blackJackGame.sumDealer[0];
        blackJackGame.handDealer.clear();
        blackJackGame.sumDealer[0] = 0;
        // THEN
        Assert.assertEquals(expected1, actual1);
        Assert.assertEquals(expected2, actual2);
    }
}
