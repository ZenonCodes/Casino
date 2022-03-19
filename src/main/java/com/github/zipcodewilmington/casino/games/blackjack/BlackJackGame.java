
package com.github.zipcodewilmington.casino.games.blackjack;


import com.github.zipcodewilmington.casino.GameInterface;
import com.github.zipcodewilmington.casino.WagingGame;

import com.github.zipcodewilmington.casino.cards.Cards;
import com.github.zipcodewilmington.casino.cards.Deck;
import com.github.zipcodewilmington.casino.cards.Rank;
import com.github.zipcodewilmington.casino.cards.Suit;

import java.util.*;

// ===== TODO: ADDITIONAL FEATURES
//--------- additional wagers
//--------- splitting pairs
//--------- doubling down

public class BlackJackGame extends WagingGame implements GameInterface<BlackJackPlayer> {
    Boolean isCardGame = true;
    ArrayDeque<Cards> handPlayer = new ArrayDeque<Cards>();
    ArrayDeque<Cards> handDealer = new ArrayDeque<Cards>();
    Integer[] sumPlayer = {0};
    Integer[] sumDealer = {0};
    Rank rankD2;
    Suit suitD2;
    BlackJackPlayer player;
    int playerBet;

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
        if (handToConvert.equals(handPlayer)) {
            sb.append("PLAYER HAS ");
        } else {
            sb.append("DEALER HAS ");
        }
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

    public int checkForNatural(ArrayDeque<Cards> handToBeChecked) { // tested
        int isNatural = 0;
        Cards card1 = handToBeChecked.peekFirst();
        int tier1 = card1.getTier();
        if (tier1 <= 8) { // CARDS 2-9
            isNatural += (tier1 + 1);
        } else if (tier1 > 8 && tier1 < 13) { // CARDS 10-KING
            isNatural += 10;
        } else if (tier1 == 13) { // ACE
            isNatural += 11;
        }
        Cards card2 = handToBeChecked.peekLast();
        int tier2 = card2.getTier();
        if (tier2 <= 8) { // CARDS 2-9
            isNatural += (tier2 + 1);
        } else if (tier2 > 8 && tier2 < 13) { // CARDS 10-KING
            isNatural += 10;
        } else if (tier2 == 13) { // ACE
            isNatural += 11;
        }
        return isNatural;
    }

    public String checkForAce(Cards cardToBeChecked) {
        String output = "";
        int tierCard = cardToBeChecked.getTier();
        if (tierCard == 13) { // ACE
            output = "YES";
        } else {
            output = "NO";
        }
        return output;
    }

    @Override
    public Boolean isOver() {
        return null;
    }

    @Override
    public void addPlayer(BlackJackPlayer player) {
        this.player = player;
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
        System.out.println("\n" + "WELCOME TO BLACKJACK" + "\n");
        int playerBalance = player.getAccountBalance();

        // take bet
        player.setBet();
        playerBet = player.getBet();

        // generate and deal deck
        ArrayDeque<Cards> deck = generateBlackJackDeck();
        deal(deck);

        // sum starting hands and check for naturals
        sumStartingCards();
        int naturalPlayer = checkForNatural(handPlayer);
        int naturalDealer = checkForNatural(handDealer);

        // gameplay
        if (naturalPlayer == 21 && naturalDealer == 21) {
            System.out.println("STAND-OFF - BET RETURNED TO PLAYER");
            return;
        } else if (naturalPlayer < 21 && naturalDealer == 21) {
            System.out.println("DEALER HAS NATURAL, PLAYER HAS " + sumPlayer[0] + ". PLAYER LOSES.");
            int newPlayerBalance = playerBalance - playerBet;
            player.setAccountBalance(newPlayerBalance);
            return;
        } else if (naturalPlayer == 21 && naturalDealer < 21) {
            int winnings = (int) (playerBet * 1.5);
            System.out.println("PLAYER HAS NATURAL, DEALER HAS " + sumDealer[0] + ". PLAYER WINS " +
                    winnings);
            int newPlayerBalance = playerBalance + winnings;
            player.setAccountBalance(newPlayerBalance);
            return;
        } else if (naturalPlayer < 21 && naturalDealer < 21) {
            // ----- player's turn
            System.out.println("YOUR TURN" + "\n");
            while (sumPlayer[0] < 21) {
                String playerInput = getStringInput("HIT or STAND");
                if (playerInput.equals("HIT")) {
                    hit(handPlayer, deck, sumPlayer);
                } else if (playerInput.equals("STAND")) {
                    break;
                }
            }
            // ----- dealer's turn
            System.out.println("\n" + "DEALER'S TURN" + "\n");
            System.out.println("DEALER'S BOTTOM CARD: " + rankD2 + " " +
                    suitD2 + "\n");
            if (sumDealer[0] < 17) {
                while (sumDealer[0] < 17) {
                    System.out.println("DEALER HITS" + "\n");
                    hit(handDealer, deck, sumDealer);
                }
                System.out.println("DEALER STANDS" + "\n");
            } else {
                System.out.println("DEALER STANDS" + "\n");
            }

            // TODO: if dealer has ace --- probably need to account for before now
            // ACES
            int handSize = handPlayer.size();
            for (int a = 0; a < handSize; a++) {
                Cards card = handPlayer.removeFirst();
                Rank rank = card.getRank();
                Suit suit = card.getSuit();
                String isAce = checkForAce(card);
                handPlayer.addLast(card);
                if (isAce.equals("YES")) {
                    System.out.println(rank + " " + suit);
                    String inputAce = getStringInput("Use card as ONE or ELEVEN?");
                    if (inputAce.equals("ELEVEN")) {
                        sumPlayer[0] += 10;
                    }
                }
            }

            // declare winner
            // ----- print hands and totals
            String playerOutput = buildOutputString(handPlayer, sumPlayer);
            System.out.println(playerOutput);
            String dealerOutput = buildOutputString(handDealer, sumDealer);
            System.out.println(dealerOutput);

            // ----- print winner
            if (sumPlayer[0] <= 21 && sumDealer[0] <= 21) {
                if (sumPlayer[0] > sumDealer[0]) {
                    System.out.println("\n" + "PLAYER WINS");
                }
                if (sumPlayer[0] < sumDealer[0]) {
                    System.out.println("\n" + "DEALER WINS");
                }
                if (sumPlayer[0] == sumDealer[0]) {
                    System.out.println("\n" + "TIE");
                }
            } else if (sumPlayer[0] <= 21 && sumDealer[0] > 21) {
                System.out.println("\n" + "DEALER BUST - PLAYER WINS!" + "\n");
            } else if (sumPlayer[0] > 21 && sumDealer[0] <= 21) {
                System.out.println("\n" + "PLAYER BUST - DEALER WINS!" + "\n");
            } else if (sumPlayer[0] > 21 && sumDealer[0] > 21) {
                System.out.println("\n" + "PLAYER AND DEALER BUST" + "\n");

                // TODO - update account balance at end - not already coded for, correct?
            }
        }
    }
}
