package com.hulksmash.game.character;

import com.hulksmash.game.Action;

public enum FightAction implements Action {
    SMASH("smash");

    private String value;

    FightAction(String value) {
        this.value = value;
    }

    public static FightAction create(String value) {
        if ("smash".equals(value)) {
            return SMASH;
        }
        return null;
    }

    public static boolean checkIfActionExist(String value) {
        FightAction fightAction = create(value);
        return fightAction != null;
    }
}
