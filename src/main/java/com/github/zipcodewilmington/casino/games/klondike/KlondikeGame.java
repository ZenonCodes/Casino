package com.github.zipcodewilmington.casino.games.klondike;

import com.github.zipcodewilmington.casino.GameInterface;
import com.github.zipcodewilmington.casino.dice.Dice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

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
    //Dice dice = new Dice(5);

    public ArrayList<Integer> getHouseRoll(){
        houseRoll = Dice.roll(0);
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

    public HashMap<String, Integer> evaluateHouseHand(){
        HashMap <String, Integer> houseHand = new HashMap<>();
        houseHand.put("ones", Collections.frequency(houseRoll, 1));
        houseHand.put("twos", Collections.frequency(houseRoll, 2));
        houseHand.put("threes", Collections.frequency(houseRoll, 3));
        houseHand.put("fours", Collections.frequency(houseRoll, 4));
        houseHand.put("fives", Collections.frequency(houseRoll, 5));
        houseHand.put("sixes", Collections.frequency(houseRoll, 6));
        System.out.println(houseHand);
        return houseHand;

    }

    public HashMap<String, Integer> evaluatePlayerHand(){
        HashMap <String, Integer> playerHand = new HashMap<>();
        playerHand.put("ones", Collections.frequency(playerRoll, 1));
        playerHand.put("twos", Collections.frequency(playerRoll, 2));
        playerHand.put("threes", Collections.frequency(playerRoll, 3));
        playerHand.put("fours", Collections.frequency(playerRoll, 4));
        playerHand.put("fives", Collections.frequency(playerRoll, 5));
        playerHand.put("sixes", Collections.frequency(playerRoll, 6));
        System.out.println(playerHand);
        return playerHand;

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
