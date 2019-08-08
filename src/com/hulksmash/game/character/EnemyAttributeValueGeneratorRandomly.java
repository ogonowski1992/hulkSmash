package com.hulksmash.game.character;

import com.hulksmash.game.Constant;

import java.util.concurrent.ThreadLocalRandom;

public class EnemyAttributeValueGeneratorRandomly extends EnemyAttributeValueGenerator {

    @Override
    public void generateAttributesSet(int pointAmountToSet) {
        int usedPoints = 0;
        int skillAmount = 3;
        if (pointAmountToSet > Constant.MAX_POINT_IN_ONE_ATTRIBUTES * skillAmount) {
            throw new IllegalArgumentException("pointAmountToSet to large");
        }
        health = Constant.ENEMY_INITIAL_HEALTH;

        int[] attributes = new int[skillAmount];
        while (usedPoints < pointAmountToSet) {
            int skillIndex = getSkillIndexToIncrement(skillAmount);
            if (attributes[skillIndex] == Constant.MAX_POINT_IN_ONE_ATTRIBUTES) {
                continue;
            }
            attributes[skillIndex] += 1;
            usedPoints++;
        }
        strength = attributes[0];
        resistance = attributes[1];
        luck = attributes[2];
    }

    private int getSkillIndexToIncrement(int skillAmount) {
        return ThreadLocalRandom.current().nextInt(0, skillAmount);
    }


}
