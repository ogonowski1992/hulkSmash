package com.hulksmash.game.character;

import com.hulksmash.game.Action;

public enum MoveAction implements Action {
    MOVE_UP("w"), MOVE_DOWN("s"), MOVE_LEFT("a"), MOVE_RIGHT("d"), ATTACK("r");

    private String value;

    MoveAction(String value) {
        this.value = value;
    }


    public static MoveAction create(String value) {
        switch (value) {
            case "w":
                return MOVE_UP;
            case "s":
                return MOVE_DOWN;
            case "a":
                return MOVE_LEFT;
            case "d":
                return MOVE_RIGHT;
            default:
                return null;
        }
    }

    public static boolean checkIfActionExist(String value) {
        MoveAction moveAction = create(value);
        return moveAction != null;
    }
}
