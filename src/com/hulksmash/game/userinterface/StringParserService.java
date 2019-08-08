package com.hulksmash.game.userinterface;

public class StringParserService {
    private int intNumber;

    public boolean tryParseToInt(String text) {
        if (text == null || text.equals("")) {
            return false;
        }
        try {
            intNumber = Integer.parseInt(text);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public int getIntNumber() {
        return intNumber;
    }
}
