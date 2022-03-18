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
    }

    @Test
    void getPlayerRollTest() {
        KlondikeGame game1 = new KlondikeGame();
        int expected = 5;
        //when
        ArrayList<Integer> roll1 = game1.getPlayerRoll();
        int actual = roll1.size();
        //then
        Assert.assertEquals(actual, expected);
    }

    @Test
    void evaluateHouseHandTest() {
        //given
        KlondikeGame game2 = new KlondikeGame();

        //when
        ArrayList<Integer> roll1 = game2.getHouseRoll();

        //then
        HashMap<String, Integer> expected = game2.evaluateHouseHand();

    }

    @Test
    void evaluatePlayerHand() {
        //given
        KlondikeGame game3 = new KlondikeGame();

        //when
        ArrayList<Integer> roll1 = game3.getPlayerRoll();

        //then
        HashMap<String, Integer> expected = game3.evaluatePlayerHand();
    }
}