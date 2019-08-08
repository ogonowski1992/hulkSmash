package com.hulksmash.game.character;

import com.hulksmash.game.Constant;
import org.junit.Assert;
import org.junit.Test;

public class EnemyAttributeValueGeneratorRandomlyTest {
    private EnemyAttributeValueGeneratorRandomly enemyStatGeneratorRandomly = new EnemyAttributeValueGeneratorRandomly();

    @Test
    public void generateAttributesSetForZeroPointsToSet() {
        int pointToSet = 0;
        test(pointToSet);
    }

    @Test
    public void generateAttributesSetForThreePointsToSet() {
        int pointToSet = 3;
        test(pointToSet);
    }

    @Test
    public void generateAttributesSetForALotPointsToSet() {
        int pointToSet = 300;
        test(pointToSet);
    }

    @Test(expected = IllegalArgumentException.class)
    public void generateAttributesSetForToMuchPointsToSet() {
        int skillAmount = 3;
        int pointToSet = skillAmount * Constant.MAX_POINT_IN_ONE_ATTRIBUTES + 1;
        test(pointToSet);
    }


    private void test(int pointToSet) {
        enemyStatGeneratorRandomly.generateAttributesSet(pointToSet);
        int setPointSum = enemyStatGeneratorRandomly.getStrength() + enemyStatGeneratorRandomly.getLuck() + enemyStatGeneratorRandomly.getResistance();
        Assert.assertEquals(pointToSet, setPointSum, 0.0);
    }
}
