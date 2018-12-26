package com.example.maduranga.myapplication;

import android.graphics.Rect;

public class Square {
    private Rect rect;
    private Player owner;

    public Square(Rect rect) {
        this.rect = rect;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public Rect getRect() {
        return rect;
    }

    public Player getOwner() {
        return owner;
    }
}
