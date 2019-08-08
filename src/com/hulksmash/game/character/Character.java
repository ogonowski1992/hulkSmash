package com.hulksmash.game.character;

import java.io.Serializable;

public abstract class Character implements Serializable {
    Character(double health, int strength, int resistance, Integer luck, int experience,  Position position) {
        this.health = health;
        this.strength = strength;
        this.resistance = resistance;
        this.luck = luck;
        this.experience = experience;
        this.position = position;
    }

    double health;
    int strength;
    int resistance;
    int luck;
    int experience;
    Position position;

    public double getHealth() {
        return health;
    }

    public int getStrength() {
        return strength;
    }

    public int getResistance() {
        return resistance;
    }

    public int getLuck() {
        return luck;
    }

    public Position getPosition() {
        return position;
    }

    public int getExperience() {
        return experience;
    }

    public abstract void damage(int damage);
}
