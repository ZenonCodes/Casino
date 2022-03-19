package com.github.zipcodewilmington.casino.games.numberguess;

import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.Player;
import com.github.zipcodewilmington.casino.WagingPlayer;

/**
 * Created by leon on 7/21/2020.
 */
public class NumberGuessPlayer extends WagingPlayer {

    @Override
    public CasinoAccount getCasinoAccount() {

        return this.casinoAccount;
    }

    @Override
    public <SomeReturnType> SomeReturnType play() {
        return null;
    }


}