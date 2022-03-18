package com.github.zipcodewilmington.casino.games.klondike;

import com.github.zipcodewilmington.casino.GameInterface;
import com.github.zipcodewilmington.casino.dice.Dice;

import java.util.ArrayList;
import java.util.Collections;
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

    public ArrayList<Integer> getHouseRoll(){
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

    public void evaluateHouseHand(){

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
