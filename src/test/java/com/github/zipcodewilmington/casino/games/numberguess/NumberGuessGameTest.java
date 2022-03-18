package com.github.zipcodewilmington.casino.games.numberguess;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumberGuessGameTest {

    @Test
    void findWinnerTest() {
        //given
        NumberGuessGame game = new NumberGuessGame();


        //when
        String expected = game.findWinner(10, 10);

        //then
        String actual = "Jackpot!!!!";
        Assert.assertEquals(expected, actual);

    }

    @Test
    void isOverTest() {
    }

    @Test
    void addPlayerTest() {
    }

    @Test
    void addPlayersTest() {
    }

    @Test
    void evaluateTurnTest() {
    }

    @Test
    void removeTest() {
    }

    @Test
    void run() {
    }
}