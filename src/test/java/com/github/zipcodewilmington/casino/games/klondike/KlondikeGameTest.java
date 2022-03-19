package com.github.zipcodewilmington.casino.games.klondike;

import com.github.zipcodewilmington.casino.dice.Dice;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class KlondikeGameTest {

    @Test
    void getHouseRollTest() {
        //given
        KlondikeGame game = new KlondikeGame();
        int expected = 5;
        //when
        ArrayList<Integer> roll = game.getHouseRoll();
        int actual = roll.size();
        //then
        Assert.assertEquals(expected, actual);
        roll.clear();
    }

    @Test
    void getPlayerRollTest() {
        KlondikeGame game = new KlondikeGame();
        int expected = 5;
        //when
        ArrayList<Integer> roll = game.getPlayerRoll();
        int actual = roll.size();
        //then
        Assert.assertEquals(actual, expected);
        roll.clear();
    }

    @Test
    void evaluateHouseHandTest() {
        //given
        KlondikeGame game = new KlondikeGame();

        //when
        ArrayList<Integer> handEval = game.getHouseRoll();

        //then
        HashMap<String, Integer> expected = game.evaluateHouseHand();
        handEval.clear();

    }

    @Test
    void evaluatePlayerHand() {
        //given
        KlondikeGame game = new KlondikeGame();

        //when
        ArrayList<Integer> handEval = game.getPlayerRoll();

        //then
        HashMap<String, Integer> expected = game.evaluatePlayerHand();
        handEval.clear();
    }

    @Test
    void getKlondikeHand() {
        KlondikeGame game = new KlondikeGame();
        ArrayList<Integer> roll = game.getHouseRoll();
        HashMap<String, Integer> expected = game.evaluateHouseHand();
        game.getKlondikeHand();
        roll.clear();
    }

    @Test
    void isTwoPairTest() {
        ///given
        KlondikeGame game = new KlondikeGame();
        ArrayList<Integer> newArr = new ArrayList<>();
        //when
        newArr.add(2);
        newArr.add(3);
        newArr.add(3);
        newArr.add(2);
        newArr.add(6);

        //then
        Assert.assertTrue(game.isTwoPair(newArr));
    }

    @Test
    void getPlayerKlondikeHandTest() {
        KlondikeGame game = new KlondikeGame();
        ArrayList<Integer> roll = game.getHouseRoll();
        HashMap<String, Integer> expected = game.evaluatePlayerHand();
        game.getPlayerKlondikeHand();
        roll.clear();
    }

    @Test
    void getWinner() {
        //given
        KlondikeGame game = new KlondikeGame();
        game.houseHandRank = 5;
        game.playerHandRank = 4;
        String expected = "HOUSE WINS!!!!";
        //when
        String actual = game.getWinner();
        //then
        Assert.assertEquals(actual, expected);
    }


}