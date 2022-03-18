package com.github.zipcodewilmington.casino.games.klondike;

import com.github.zipcodewilmington.casino.dice.Dice;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

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
        Assert.assertEquals(actual, expected);
    }

    @Test
    void getPlayerRoll() {
        KlondikeGame game = new KlondikeGame();
        int expected = 5;
        //when
        ArrayList<Integer> roll1 = game.getPlayerRoll();
        int actual = roll1.size();
        //then
        Assert.assertEquals(actual, expected);
    }
}