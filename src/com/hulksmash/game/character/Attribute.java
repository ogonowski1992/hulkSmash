package com.hulksmash.game.character;

public enum Attribute {
    RESISTANCE("resistance"), STRENGTH("strength"), LUCK("luck");


    private String value;

    Attribute(String value) {
        this.value = value;
    }

    public static String getAllNames() {
        return String.format("%s, %s, %s", RESISTANCE.value, STRENGTH.value, LUCK.value);
    }


    public String value() {
        return value;
    }
}
