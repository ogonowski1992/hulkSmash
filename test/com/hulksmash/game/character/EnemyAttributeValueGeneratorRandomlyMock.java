package com.hulksmash.game.character;

public class EnemyAttributeValueGeneratorRandomlyMock extends EnemyAttributeValueGenerator {


    @Override
    public void generateAttributesSet(int pointAmountToSet) {
        strength = 0;
        health = 100;
        luck = 0;
        resistance = 0;
    }

    public void setResistance(int value) {
        resistance = value;
    }

    public void setStrength(int value) {
        strength = value;
    }
}
