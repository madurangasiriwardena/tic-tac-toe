package com.example.maduranga.myapplication;

/**
 * Represents rows, columns and diagonals.
 */
public class Entry {
    private int player_1;
    private int player_2;

    public int getPlayer_1() {
        return player_1;
    }

    public void setPlayer_1() {
        player_1++;
    }

    public int getPlayer_2() {
        return player_2;
    }

    public void setPlayer_2() {
        player_2++;
    }
}
