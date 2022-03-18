package com.github.zipcodewilmington.casino.games.klondike;

import com.github.zipcodewilmington.casino.GameInterface;
import com.github.zipcodewilmington.casino.dice.Dice;

import java.util.*;

public class KlondikeGame implements GameInterface<KlondikePlayer> {

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

    public String getKlondikeHand() {

        if (houseHand.containsValue(5)) {
            klondikeHand = "Five of a Kind";
            System.out.println(klondikeHand);
            return klondikeHand;
        } else if (houseHand.containsValue(4)) {
            klondikeHand = "Four of a Kind";
            System.out.println(klondikeHand);
            return klondikeHand;
        } else if (houseHand.containsValue(3) && houseHand.containsValue(2)) {
            klondikeHand = "Full House";
            System.out.println(klondikeHand);
            return klondikeHand;
        } else if (houseHand.containsValue(3)) {
            klondikeHand = "Three of a Kind";
            System.out.println(klondikeHand);
            return klondikeHand;
        } else if (houseHand.containsValue(2)) {
            if (isTwoPair(houseRoll)) {
                klondikeHand = "Two Pair";
                System.out.println(klondikeHand);
            } else {
                klondikeHand = "Pair";
                System.out.println(klondikeHand);
                return klondikeHand;
            }
        } else {
            klondikeHand = "Tie, house wins...";
        }
        return klondikeHand;
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


    public boolean isTwoPair(ArrayList<Integer> hand) {
        HashSet<Integer> newHash = new HashSet<>(hand);
        System.out.println(newHash);
        return newHash.size() == 3;
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
