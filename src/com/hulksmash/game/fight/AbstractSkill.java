package com.hulksmash.game.fight;

import com.hulksmash.game.character.Enemy;
import com.hulksmash.game.character.Player;

import java.io.Serializable;

public abstract class AbstractSkill implements Serializable {
    abstract  String getName();
    abstract int damageRadius();
    abstract boolean isDamageAllNearEnemy();

    public abstract void calculateDamage(Player player, Enemy e, FightRoundResult fightRoundResult);
}
