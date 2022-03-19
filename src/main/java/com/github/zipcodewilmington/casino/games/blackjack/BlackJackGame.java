
package com.github.zipcodewilmington.casino.games.blackjack;


import com.github.zipcodewilmington.casino.GameInterface;
import com.github.zipcodewilmington.casino.WagingGame;

import com.github.zipcodewilmington.casino.cards.Cards;
import com.github.zipcodewilmington.casino.cards.Deck;
import com.github.zipcodewilmington.casino.cards.Rank;
import com.github.zipcodewilmington.casino.cards.Suit;

import java.util.*;

// ===== MVP
//--------- simple, playable game
//--------- no betting

// ===== ADDITIONAL FEATURES
//--------- bet at beginning
//--------- additional wagers
//--------- splitting pairs
//--------- doubling down

public class BlackJackGame extends WagingGame implements GameInterface<BlackJackPlayer> {
    Boolean isCardGame = true;
    ArrayDeque<Cards> handPlayer = new ArrayDeque<Cards>();
    ArrayDeque<Cards> handDealer = new ArrayDeque<Cards>();
    Integer[] sumPlayer = {0}; // TODO - clear at end???
    Integer[] sumDealer = {0}; // TODO - clear at end???
    Rank rankD2;
    Suit suitD2;

    public static void main(String[] args) {
        System.out.println("\n" + "WELCOME TO BLACKJACK" + "\n");
        BlackJackGame blackJackGame = new BlackJackGame();

        // generate and deal deck
        ArrayDeque<Cards> deck = blackJackGame.generateBlackJackDeck();
        blackJackGame.deal(deck);

        // sum starting hands
        blackJackGame.sumStartingCards();

        // gameplay
        // TODO - write code for if starting player hand is 21
        // if 1 person has 21 vs both vs neither
        // TODO - how to handle ACE??? -- maybe option to add 10 later???
        // ----- player's turn
        System.out.println("YOUR TURN" + "\n");
        while (blackJackGame.sumPlayer[0] < 21) {
            String playerInput = getStringInput("HIT or STAND");
            if (playerInput.equals("HIT")) {
                blackJackGame.hit(blackJackGame.handPlayer, deck, blackJackGame.sumPlayer);
            } else if (playerInput.equals("STAND")) {
            break;
            }
        }
        // ----- dealer's turn
        System.out.println("\n" + "DEALER'S TURN" + "\n");
        System.out.println("DEALER'S BOTTOM CARD: " + blackJackGame.rankD2 + " " +
                blackJackGame.suitD2 + "\n");
        if (blackJackGame.sumDealer[0] < 17) {
            while (blackJackGame.sumDealer[0] < 17) {
                System.out.println("DEALER HITS" + "\n");
                blackJackGame.hit(blackJackGame.handDealer, deck, blackJackGame.sumDealer);
            }
            System.out.println("DEALER STANDS" + "\n");
        } else {
            System.out.println("DEALER STANDS" + "\n");
        }

        // declare winner
        // ----- print hands and totals
        String playerOutput = blackJackGame.buildOutputString(blackJackGame.handPlayer,
                blackJackGame.sumPlayer);
        System.out.println(playerOutput);
        String dealerOutput = blackJackGame.buildOutputString(blackJackGame.handDealer,
                blackJackGame.sumDealer);
        System.out.println(dealerOutput);
        // print winner
        // print what happens to chips

        // It is up to each individual player if an ace is worth 1 or 11. Face cards are 10 and any
        // other card is its pip value.
    }

    // =============== SUB-METHODS ===============
    public static String getStringInput(String prompt) { // no test
        Scanner scanner = new Scanner(System.in);
        System.out.println(prompt);
        String userInput = scanner.nextLine();
        return userInput;
    }

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

    public void deal(ArrayDeque<Cards> deck) { // tested
        handPlayer.addFirst(deck.removeFirst());
        Cards playerCard1 = handPlayer.peekFirst();
        Rank rankP1 = playerCard1.getRank();
        Suit suitP1 = playerCard1.getSuit();
        handDealer.addFirst(deck.removeFirst());
        Cards dealerCard1 = handDealer.peekFirst();
        Rank rankD1 = dealerCard1.getRank();
        Suit suitD1 = dealerCard1.getSuit();
        handPlayer.addFirst(deck.removeFirst());
        Cards playerCard2 = handPlayer.peekFirst();
        Rank rankP2 = playerCard2.getRank();
        Suit suitP2 = playerCard2.getSuit();
        handDealer.addFirst(deck.removeFirst());
        Cards dealerCard2 = handDealer.peekFirst();
        rankD2 = dealerCard2.getRank();
        suitD2 = dealerCard2.getSuit();
        System.out.println("YOUR HAND: " + rankP1 + " " + suitP1 + ", " + rankP2 + " " + suitP2 + "\n");
        System.out.println("DEALER SHOWS: " + rankD1 + " " + suitD1 + "\n");
    }

    public void hit(ArrayDeque<Cards> handToHit, ArrayDeque<Cards> deck, Integer[] sumToAddTo) { // tested
        handToHit.addFirst(deck.removeFirst());
        Cards newCard = handToHit.peekFirst();
        Rank rankNewCard = newCard.getRank();
        Suit suitNewCard = newCard.getSuit();
        System.out.println("NEW CARD: " + rankNewCard + " " + suitNewCard + "\n");
        addCardToSum(newCard, sumToAddTo);
    }

    public void addCardToSum(Cards cardToBeAdded, Integer[] sumToAddTo) { // tested
        int tier = cardToBeAdded.getTier();
        if (tier <= 8) { // CARDS 2-9
            sumToAddTo[0] += (tier + 1);
        } else if (tier > 8 && tier < 13) { // CARDS 10-KING
            sumToAddTo[0] += 10;
        } else if (tier == 13) { // ACE
            sumToAddTo[0] += 1;
        }
    }

    public void sumStartingCards() { // tested
        Cards playerCard1 = handPlayer.peekFirst();
        Cards playerCard2 = handPlayer.peekLast();
        Cards dealerCard1 = handDealer.peekFirst();
        Cards dealerCard2 = handDealer.peekLast();
        addCardToSum(playerCard1, sumPlayer);
        addCardToSum(playerCard2, sumPlayer);
        addCardToSum(dealerCard1, sumDealer);
        addCardToSum(dealerCard2, sumDealer);
    }

    public String buildOutputString(ArrayDeque<Cards> handToConvert, Integer[] sum) {
        StringBuilder sb = new StringBuilder();
        sb.append("PLAYER HAS ");
        int size = handToConvert.size();
        for (int i = 0; i < size; i++) {
            Cards card = handToConvert.removeFirst();
            Rank rank = card.getRank();
            Suit suit = card.getSuit();
            sb.append(rank + " " + suit + ", ");
        }
        sb.append("TOTAL: " + sum[0]);
        String output = sb.toString();
        return output;
    }

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
