package com.hulksmash.game;


public enum GameAction implements Action {
    QUITE_GAME("q");

    private String value;

    GameAction(String value) {
        this.value = value;
    }


    public static GameAction create(String value) {
        if ("q".equals(value)) {
            return QUITE_GAME;
        }
        return null;
    }

    public static boolean checkIfActionExist(String value) {
        GameAction gameAction = create(value);
        return gameAction != null;

    }
}
