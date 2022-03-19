package com.github.zipcodewilmington.casino.games.klondike;

import com.github.zipcodewilmington.casino.GameInterface;
import com.github.zipcodewilmington.casino.WagingGame;
import com.github.zipcodewilmington.casino.dice.Dice;

import java.util.*;

public class KlondikeGame extends WagingGame implements GameInterface<KlondikePlayer> {

    /*
    given
    house roll
    player roll

    dice roll of 1 is the highest score, then 6, 5, 4, 3, 2
    scoring is:
    five of a kind
    four of a kind
    full house (3 of a kind, pair
    three of a kind
    two pair
    one pair
    house rolls, if tie, house wins
    */

    ArrayList<Integer> houseRoll = new ArrayList<>();
    ArrayList<Integer> playerRoll = new ArrayList<>();
    HashMap<String, Integer> houseHand = new HashMap<>();
    HashMap<String, Integer> playerHand = new HashMap<>();
    String klondikeHand = "";
    String playerklondikeHand = "";
    int houseHandRank;
    int playerHandRank;

    //Dice dice = new Dice(5);

    public ArrayList<Integer> getHouseRoll() {
        houseRoll = Dice.roll(5);
        Collections.sort(houseRoll);
        System.out.println(houseRoll);
        return houseRoll;
    }

    public ArrayList<Integer> getPlayerRoll() {
        playerRoll = Dice.roll(5);
        Collections.sort(playerRoll);
        System.out.println(playerRoll);
        return playerRoll;
    }

    public HashMap<String, Integer> evaluateHouseHand() {
        houseHand.put("ones", Collections.frequency(houseRoll, 1));
        houseHand.put("twos", Collections.frequency(houseRoll, 2));
        houseHand.put("threes", Collections.frequency(houseRoll, 3));
        houseHand.put("fours", Collections.frequency(houseRoll, 4));
        houseHand.put("fives", Collections.frequency(houseRoll, 5));
        houseHand.put("sixes", Collections.frequency(houseRoll, 6));
        System.out.println(houseHand);
        return houseHand;

    }

    public int getKlondikeHand() {

        if (houseHand.containsValue(5)) {
            klondikeHand = "Five of a Kind";
            houseHandRank = 6;
            System.out.println(klondikeHand);
            return houseHandRank;
        } else if (houseHand.containsValue(4)) {
            klondikeHand = "Four of a Kind";
            houseHandRank = 5;
            System.out.println(klondikeHand);
            return houseHandRank;
        } else if (houseHand.containsValue(3) && houseHand.containsValue(2)) {
            klondikeHand = "Full House";
            houseHandRank = 4;
            System.out.println(klondikeHand);
            return houseHandRank;
        } else if (houseHand.containsValue(3)) {
            klondikeHand = "Three of a Kind";
            houseHandRank = 3;
            System.out.println(klondikeHand);
            return houseHandRank;
        } else if (houseHand.containsValue(2)) {
            if (isTwoPair(houseRoll)) {
                klondikeHand = "Two Pair";
                houseHandRank = 2;
                System.out.println(klondikeHand);
            } else {
                klondikeHand = "Pair";
                houseHandRank = 1;
                System.out.println(klondikeHand);
                return houseHandRank;
            }
        } else {
            klondikeHand = "Tie, house wins...";
            houseHandRank = 7;
        }
        return houseHandRank;
    }

    public HashMap<String, Integer> evaluatePlayerHand() {
        playerHand.put("ones", Collections.frequency(playerRoll, 1));
        playerHand.put("twos", Collections.frequency(playerRoll, 2));
        playerHand.put("threes", Collections.frequency(playerRoll, 3));
        playerHand.put("fours", Collections.frequency(playerRoll, 4));
        playerHand.put("fives", Collections.frequency(playerRoll, 5));
        playerHand.put("sixes", Collections.frequency(playerRoll, 6));
        System.out.println(playerHand);
        return playerHand;
    }

    public int getPlayerKlondikeHand() {

        if (playerHand.containsValue(5)) {
            playerklondikeHand = "Five of a Kind";
            playerHandRank = 6;
            System.out.println(playerklondikeHand);
            return playerHandRank;
        } else if (playerHand.containsValue(4)) {
            playerklondikeHand = "Four of a Kind";
            playerHandRank = 5;
            System.out.println(playerklondikeHand);
            return playerHandRank;
        } else if (playerHand.containsValue(3) && houseHand.containsValue(2)) {
            playerklondikeHand = "Full House";
            playerHandRank = 4;
            System.out.println(playerklondikeHand);
            return playerHandRank;
        } else if (playerHand.containsValue(3)) {
            playerklondikeHand = "Three of a Kind";
            playerHandRank = 3;
            System.out.println(playerklondikeHand);
            return playerHandRank;
        } else if (playerHand.containsValue(2)) {
            if (isTwoPair(playerRoll)) {
                playerklondikeHand = "Two Pair";
                playerHandRank = 2;
                System.out.println(playerklondikeHand);
            } else {
                playerklondikeHand = "Pair";
                playerHandRank = 1;
                System.out.println(playerklondikeHand);
                return playerHandRank;
            }
        } else {
            playerklondikeHand = "Tie, house wins...";
            playerHandRank = 0;
        }
        return playerHandRank;
    }

    public boolean isTwoPair(ArrayList<Integer> hand) {
        HashSet<Integer> newHash = new HashSet<>(hand);
        System.out.println(newHash);
        return newHash.size() == 3;
    }

    public String getWinner() {
        if (houseHandRank > playerHandRank) {
            return "HOUSE WINS!!!!";
        } else if (playerHandRank > houseHandRank) {
            return "YOU WIN!!!";
        } return "HOUSE WINS!!";
    }



    @Override
    public Boolean isOver() {
        return null;
    }

    @Override
    public void addPlayer(KlondikePlayer player) {

    }

    @Override
    public void addPlayers(List<? extends KlondikePlayer> player) {

    }

    @Override
    public void evaluateTurn(KlondikePlayer player) {

    }

    @Override
    public void remove(KlondikePlayer player) {

    }

    @Override
    public void run() {

    }
}
