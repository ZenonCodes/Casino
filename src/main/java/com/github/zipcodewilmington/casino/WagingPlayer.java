package com.github.zipcodewilmington.casino;

import com.github.zipcodewilmington.casino.Player;
import com.github.zipcodewilmington.casino.PlayerInterface;

public abstract class WagingPlayer extends Player implements PlayerInterface {


    @Override
    public <SomeReturnType> SomeReturnType play() {
        return null;
    }

}