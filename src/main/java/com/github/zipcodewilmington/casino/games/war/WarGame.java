package com.github.zipcodewilmington.casino.games.war;

import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.GameInterface;
import com.github.zipcodewilmington.casino.Player;
import com.github.zipcodewilmington.casino.cards.Cards;
import com.github.zipcodewilmington.casino.cards.Deck;
import com.github.zipcodewilmington.casino.cards.Rank;
import com.github.zipcodewilmington.casino.cards.Suit;

import java.util.Collections;
import java.util.*;

import static java.sql.DriverManager.println;

public class WarGame implements GameInterface<WarPlayer> { // NON-GAMBLING
    // TODO - 1 player vs computer

    Boolean isCardGame = true;

    // Scanner scanner = new Scanner(System.in);

    ArrayDeque<Cards> temporary = new ArrayDeque<Cards>();
    ArrayDeque<Cards> handPlayer1 = new ArrayDeque<Cards>();
    ArrayDeque<Cards> handPlayer2 = new ArrayDeque<Cards>();
    int gameStatus = 0; // do I actually need this? --- useful within gameplay???

    public static void main(String[] args) { // I don't think I need a main method here as the game will be initiated in another class.
        WarGame wargame = new WarGame();
        wargame.run();
        System.exit(0);
    }

    // ============================= SUB-METHODS =============================

    public static String getStringInput(String prompt) { // no test
        Scanner scanner = new Scanner(System.in);
        System.out.println(prompt);
        String userInput = scanner.nextLine();
        return userInput;
    }

    public ArrayDeque<Cards> generateWarDeck() { // no test
        ArrayList<Cards> unShuffled = createWarDeck();
        ArrayList<Cards> shuffled = shuffle(unShuffled);
        ArrayDeque<Cards> deck = convertDeckToDeque(shuffled);
        return deck;
    }

    public ArrayList<Cards> createWarDeck() { // tested
        Deck deck = new Deck();
        ArrayList<Cards> unShuffledWarDeck = deck.createDeck();
        return unShuffledWarDeck;
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
        for (int i = 1; i <= 26; i++) {
            handPlayer1.addFirst(deck.removeFirst());
            handPlayer2.addFirst(deck.removeFirst());
        }
    }

    public void distributeTemporaryCards(ArrayDeque<Cards> handOfWinner) { // tested
        int x = 0;
        while (x == 0) {
            if (temporary.isEmpty()) {
                x = 1;
            } else {
                handOfWinner.addLast(temporary.removeFirst());
            }
        }
    }

    public void addThreeCardsToTemporary(ArrayDeque<Cards> handOfPlayer) { // tested
        temporary.addFirst(handOfPlayer.removeFirst());
        temporary.addFirst(handOfPlayer.removeFirst());
        temporary.addFirst(handOfPlayer.removeFirst());
    }

    public void compareAndRedistribute(Cards player1Card, Cards player2Card) {
        int tierCard1 = player1Card.getTier();
        int tierCard2 = player2Card.getTier();
        Rank rank1 = player1Card.getRank();
        Suit suit1 = player1Card.getSuit();
        Rank rank2 = player2Card.getRank();
        Suit suit2 = player2Card.getSuit();
        if (tierCard1 > tierCard2) {
            handPlayer1.addLast(player1Card);
            handPlayer1.addLast(player2Card);
            distributeTemporaryCards(handPlayer1);
            System.out.println("Player 1: " + rank1 + " " + suit1); // rename players to account names
            System.out.println("Player 2: " + rank2 + " " + suit2 + "\n"); // rename players to account names
            System.out.println("Player 1 Wins This Round!" + "\n"); // rename players to account names
            System.out.println("Player 1 Has " + handPlayer1.size() + " cards."); // rename players
            // to account names
            System.out.println("Player 2 Has " + handPlayer2.size() + " cards." + "\n"); // rename players
            // to account names

        } else if (tierCard1 < tierCard2) {
            handPlayer2.addLast(player1Card);
            handPlayer2.addLast(player2Card);
            distributeTemporaryCards(handPlayer2);
            System.out.println("Player 1: " + rank1 + " " + suit1); // rename players to account names
            System.out.println("Player 2: " + rank2 + " " + suit2 + "\n"); // rename players to account names
            System.out.println("Player 2 Wins This Round!" + "\n"); // rename players to account names
            System.out.println("Player 1 Has " + handPlayer1.size() + " cards."); // rename players
            // to account names
            System.out.println("Player 2 Has " + handPlayer2.size() + " cards." + "\n"); // rename players
            // to account names

        } else if (tierCard1 == tierCard2) {
            System.out.println("Player 1: " + rank1 + " " + suit1); // rename players to account names
            System.out.println("Player 2: " + rank2 + " " + suit2 + "\n"); // rename players to account names
            if (handPlayer1.size() > 0 && handPlayer2.size() == 0) {
                gameStatus = 1;
                return;
            } else if (handPlayer1.size() == 0 && handPlayer2.size() > 0) {
                gameStatus = 1;
                return;
            } else if (handPlayer1.size() > 3 && handPlayer2.size() > 3) {
                System.out.println("WAR!" + "\n");
                temporary.addFirst(player1Card);
                temporary.addFirst(player2Card);
                addThreeCardsToTemporary(handPlayer1);
                addThreeCardsToTemporary(handPlayer2);
                return;
            } else if (handPlayer1.size() > 3 && handPlayer2.size() < 3) {
                System.out.println("WAR!" + "\n");
                temporary.addFirst(player1Card);
                temporary.addFirst(player2Card);
                addThreeCardsToTemporary(handPlayer1);
                while (handPlayer2.size() > 1) {
                    temporary.addFirst(handPlayer2.removeFirst());
                }
                return;
            } else if (handPlayer1.size() < 3 && handPlayer2.size() > 3) {
                System.out.println("WAR!" + "\n");
                temporary.addFirst(player1Card);
                temporary.addFirst(player2Card);
                addThreeCardsToTemporary(handPlayer2);
                while (handPlayer1.size() > 1) {
                    temporary.addFirst(handPlayer1.removeFirst());
                }
                return;
            } else {
                System.out.println("WAR!" + "\n");
                temporary.addFirst(player1Card);
                temporary.addFirst(player2Card);
                while (handPlayer1.size() > 1) {
                    temporary.addFirst(handPlayer1.removeFirst());
                }
                while (handPlayer2.size() > 1) {
                    temporary.addFirst(handPlayer2.removeFirst());
                }
                return;
            }
        }
    }


    @Override
    public Boolean isOver() {
        return null;
    }

    @Override
    public void addPlayer(WarPlayer player) {

    }

    @Override
    public void addPlayers(List<? extends WarPlayer> player) {

    }

    @Override
    public void evaluateTurn(WarPlayer player) {

    }

    @Override
    public void remove(WarPlayer player) {

    }

    @Override
    public void run() {
        while (gameStatus == 0) { // do I actually need this? -- useful within gameplay???
            // assign players
            String player1 = "PLAYER1"; // TODO - work on this
            String player2 = "PLAYER2"; // TODO - work on this

            // generate deck and deal cards
            ArrayDeque<Cards> deck = generateWarDeck();
            deal(deck);

            // gameplay
            while (handPlayer1.size() < 52 && handPlayer2.size() < 52 && gameStatus == 0) {
                String player1Input = getStringInput("Player 1: Input FLIP to flip the next card " +
                        "or QUIT to exit the game.");
                String player2Input = getStringInput("Player 2: Input FLIP to flip the next card " +
                        "or QUIT to exit the game.");
                if (player1Input.equals("FLIP") && player2Input.equals("FLIP")) {
                    // do nothing/continue
                } else if (player1Input.equals("QUIT") || player2Input.equals("QUIT")) {
                    gameStatus = 1;
                    break;
                }
                Cards player1Card = handPlayer1.removeFirst();
                Cards player2Card = handPlayer2.removeFirst();
                compareAndRedistribute(player1Card, player2Card);
            }

            // declare a winner
            if (handPlayer1.size() == 0) {
                System.out.println("PLAYER 2 WINS!"); // TODO - change this to reflect username
                gameStatus = 1;
            } else if (handPlayer2.size() == 0) {
                System.out.println("PLAYER 2 WINS!"); // TODO - change this to reflect username
                gameStatus = 1;
            }
        }
        temporary.clear();
        handPlayer1.clear();
        handPlayer2.clear();
        System.exit(0);
        // return;  // TODO - fix this so it redirects to game selection or handle in other clas??
    }

}
