package com.hulksmash.game.menu;

public enum MenuOption {

    NEW_GAME("c"), LOAD_GAME("l"), SAVE_GAME("s"), QUIT("q");

    private String value;

    MenuOption(String value) {
        this.value = value;
    }

    public static MenuOption create(String userChoice) {
        switch (userChoice) {
            case "c":
                return NEW_GAME;
            case "l":
                return LOAD_GAME;
            case "s":
                return SAVE_GAME;
            case "q":
                return QUIT;
        }
        return null;
    }

    public String getValue() {
        return value;
    }


}
