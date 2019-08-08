package com.hulksmash.game.fight;

import com.hulksmash.game.Constant;
import com.hulksmash.game.character.Enemy;
import com.hulksmash.game.character.Player;
import com.hulksmash.game.character.Character;

import java.util.concurrent.ThreadLocalRandom;

public class SmashSkill extends AbstractSkill {

    @Override
    String getName() {
        return "Smash";
    }

    @Override
    int damageRadius() {
        return 1;
    }

    @Override
    boolean isDamageAllNearEnemy() {
        return true;
    }

    @Override
    public void calculateDamage(Player player, Enemy enemy, FightRoundResult fightRoundResult) {
        int dmg = attack(player, enemy);
        fightRoundResult.setDamageToEnemy(dmg);
        fightRoundResult.setEnemiesHealth(enemy.getName(), enemy.getHealth());
        dmg = attack(enemy, player);
        fightRoundResult.setDamageToPlayer(dmg);
    }

    private int attack(Character attacker, Character defender) {
        int attackerForce = getForce(attacker.getLuck(), attacker.getStrength());
        int defenderResistToForce = getForce(defender.getLuck(), defender.getResistance());

        int damage = attackerForce - defenderResistToForce;
        if (damage > 0) {
            defender.damage(damage);
            return damage;
        }
        return 0;
    }

    private int getForce(int luck, int attributeValue) {
        boolean doubleDamage = ThreadLocalRandom.current().nextInt(0, Constant.MAX_POINT_IN_ONE_ATTRIBUTES + 1) <= luck;
        return doubleDamage ? attributeValue * 2 : attributeValue;
    }
}
