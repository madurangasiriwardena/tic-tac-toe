package com.example.maduranga.myapplication;

public enum Player {
    PLAYER_1("Player 1"),
    PLAYER_2("Player 2");

    private String name;

    Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
