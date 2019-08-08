package com.hulksmash.game.character;

abstract class EnemyAttributeValueGenerator {

    double health;
    int strength;
    int resistance;
    int luck;

    abstract void generateAttributesSet(int pointAmountToSet);

    double getHealth() {
        return health;
    }

    int getStrength() {
        return strength;
    }

    int getResistance() {
        return resistance;
    }

    int getLuck() {
        return luck;
    }
}
