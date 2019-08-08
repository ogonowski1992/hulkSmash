package com.hulksmash.game.character;

import java.io.Serializable;

public class Position implements Serializable {
    private int x;
    private int y;

    public Position(Position position) {
        x = position.x;
        y = position.y;
    }

    public Position(int x, int y) {

        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    void decreaseY() {
        y--;
    }

    void increaseY() {
        y++;
    }

    void decreaseX() {
        x--;
    }

    public void increaseX() {
        x++;
    }

    private boolean isNextToInFourDirection(Position position, int damageRadius) {
        return Math.abs(x - position.getX()) + Math.abs(y - position.getY()) == damageRadius;
    }

    public boolean isNextToInFourDirectionWithRadius(Position position, int damageRadius) {
        return isNextToInFourDirection(position, damageRadius);
    }
}
