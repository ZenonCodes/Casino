package com.github.zipcodewilmington.casino.games.war;

import com.github.zipcodewilmington.casino.cards.Cards;
import com.github.zipcodewilmington.casino.cards.Rank;
import com.github.zipcodewilmington.casino.cards.Suit;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
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
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < arrayListOfCards.size(); i++) {
//            sb.append(arrayListOfCards.get(i).getRank() + " " + arrayListOfCards.get(i).getSuit() + " "
//                    + arrayListOfCards.get(i).getTier() + "\n");
//        }
//        String output = sb.toString();
//        System.out.println(output);
    }

    @Test
    void deal() {
        // Given
        WarGame warGame = new WarGame();
        ArrayDeque<Cards> deck = warGame.generateWarDeck();
        int expected1Size = 26;
        int expected2Size = 26;
        // When
        warGame.deal(deck);
        int actual1Size = warGame.handPlayer1.size();
        int actual2Size = warGame.handPlayer2.size();
        // Then
        Assert.assertTrue(expected1Size == actual1Size);
        Assert.assertTrue(expected2Size == actual2Size);
    }

    @Test
    void distributeTemporaryCards() {
        // Given
        WarGame warGame = new WarGame();
        Cards card1 = new Cards(Rank.TWO, Suit.HEARTS, 1);
        Cards card2 = new Cards(Rank.TWO, Suit.SPADES, 1);
        Cards card3 = new Cards(Rank.TWO, Suit.DIAMONDS, 1);
        Cards card4 = new Cards(Rank.TWO, Suit.CLUBS, 1);
        warGame.temporary.clear();
        ArrayDeque<Cards> recipient = new ArrayDeque<Cards>();
        int expectedSize = 4;
        // When
        warGame.temporary.addFirst(card1);
        warGame.temporary.addFirst(card2);
        warGame.temporary.addFirst(card3);
        warGame.temporary.addFirst(card4);
        warGame.distributeTemporaryCards(recipient);
        int actualSize = recipient.size();
        warGame.temporary.clear();
        // Then
        Assert.assertEquals(expectedSize, actualSize);
    }

    @Test
    void addThreeCardsToTemporary() {
        // Given
        WarGame warGame = new WarGame();
        Cards card1 = new Cards(Rank.TWO, Suit.HEARTS, 1);
        Cards card2 = new Cards(Rank.TWO, Suit.SPADES, 1);
        Cards card3 = new Cards(Rank.TWO, Suit.DIAMONDS, 1);
        Cards card4 = new Cards(Rank.TWO, Suit.CLUBS, 1);
        ArrayDeque<Cards> playerHand = new ArrayDeque<Cards>();
        warGame.temporary.clear();
        int expectedSizePlayer = 1;
        int expectedSizeTemporary = 3;
        // When
        playerHand.addFirst(card1);
        playerHand.addFirst(card2);
        playerHand.addFirst(card3);
        playerHand.addFirst(card4);
        warGame.addThreeCardsToTemporary(playerHand);
        int actualSizePlayer = playerHand.size();
        int actualSizeTemporary = warGame.temporary.size();
        warGame.temporary.clear();
        // Then
        Assert.assertEquals(expectedSizePlayer, actualSizePlayer);
        Assert.assertEquals(expectedSizeTemporary, actualSizeTemporary);
    }
}