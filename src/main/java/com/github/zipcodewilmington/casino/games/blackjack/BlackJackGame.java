package com.github.zipcodewilmington.casino.games.blackjack;


import com.github.zipcodewilmington.casino.GameInterface;
import com.github.zipcodewilmington.casino.cards.Cards;
import com.github.zipcodewilmington.casino.cards.Deck;

import java.util.*;

// ===== MVP
//--------- simple, playable game

// ===== ADDITIONAL FEATURES
//--------- splitting pairs
//--------- doubling down

public class BlackJackGame implements GameInterface<BlackJackPlayer> {
    Boolean isCardGame = true;

    public static void main(String[] args) {
        BlackJackGame blackJackGame = new BlackJackGame();
        // TODO assign players (and tests)
        // generate and deal deck
        blackJackGame.generateBlackJackDeck();
           // TODO deal (and tests)
    }

//    public static String getStringInput(String prompt) { // no test
//        Scanner scanner = new Scanner(System.in);
//        System.out.println(prompt);
//        String userInput = scanner.nextLine();
//        return userInput;
//    }

    public ArrayDeque<Cards> generateBlackJackDeck() { // no test
        ArrayList<Cards> unShuffled = createBlackJackDeck();
        ArrayList<Cards> shuffled = shuffle(unShuffled);
        ArrayDeque<Cards> deck = convertDeckToDeque(shuffled);
        return deck;
    }

    public ArrayList<Cards> createBlackJackDeck() { // tested
        Deck deck = new Deck();
        ArrayList<Cards> unShuffledDeck = deck.createDeck();
        return unShuffledDeck;
    }

    public ArrayList<Cards> shuffle(ArrayList<Cards> inputDeck) { // tested
        Collections.shuffle(inputDeck);
        return inputDeck;
    }

    public ArrayDeque<Cards> convertDeckToDeque(ArrayList<Cards> inputDeck) { // no test
        ArrayDeque<Cards> convertedDeck = new ArrayDeque<>(inputDeck);
        return convertedDeck;
    }

//    public void deal(ArrayDeque<Cards> deck) { // tested
//        for (int i = 1; i <= 26; i++) {
//            handPlayer1.addFirst(deck.removeFirst());
//            handPlayer2.addFirst(deck.removeFirst());
//        }
//    }

    @Override
    public Boolean isOver() {
        return null;
    }

    @Override
    public void addPlayer(BlackJackPlayer player) {

    }

    @Override
    public void addPlayers(List<? extends BlackJackPlayer> player) {

    }

    @Override
    public void evaluateTurn(BlackJackPlayer player) {

    }

    @Override
    public void remove(BlackJackPlayer player) {

    }

    @Override
    public void run() {

    }
}
