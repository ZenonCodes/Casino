package com.github.zipcodewilmington.casino;
import java.util.Scanner;


public abstract class WagingGame  {
    int pot;
    public int getPot() {
        return pot;
    }

    public void setPot(int pot) {
        this.pot = pot;
    }
    public void getWagers(int wagers){
        pot += wagers;
    }
    public int disperseWinnings() {
        return pot;
    }

}

