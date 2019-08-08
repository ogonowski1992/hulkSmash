package com.hulksmash.game.character;

import com.hulksmash.game.Constant;
import com.hulksmash.game.userinterface.ColorsForConsole;
import com.hulksmash.game.board.Board;

public class Player extends Character {
    private String name;
    private int notUsedAttributePoints;
    private String appearance;

    public Player(double health, String name, int strength, int resistance, int luck, int notUsedAttributePoints) {
        super(health, strength, resistance, luck, Constant.PLAYER_INITIAL_EXPERIENCE, new Position(1, 1));
        this.name = name;
        this.notUsedAttributePoints = notUsedAttributePoints;
        appearance = ColorsForConsole.ANSI_GREEN_BACKGROUND + "O" + ColorsForConsole.ANSI_RESET;
    }

    public void draw(Board board) {
        board.setPlayer(position, appearance);
    }

    @Override
    public String toString() {
        return String.format("%s, strength: %d, resistance: %d, luck: %d \r\nhealth: %.02f", name, strength, resistance, luck, health);
    }

    public void makeAction(MoveAction action, Board board) {
        Position possibleNewPosition = new Position(position);
        switch (action) {
            case MOVE_UP:
                possibleNewPosition.decreaseY();
                break;
            case MOVE_DOWN:
                possibleNewPosition.increaseY();
                break;
            case MOVE_LEFT:
                possibleNewPosition.decreaseX();
                break;
            case MOVE_RIGHT:
                possibleNewPosition.increaseX();
                break;
            case ATTACK:
                break;
        }

        if (board.isPositionAllowedForPlayer(possibleNewPosition)) {
            position = possibleNewPosition;
        }

    }

    @Override
    public void damage(int damage) {
        health -= damage;
    }


    public void addExperience(long experience) {
        this.experience += experience;
    }
}
