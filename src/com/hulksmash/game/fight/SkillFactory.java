package com.hulksmash.game.fight;

import com.hulksmash.game.character.FightAction;

import java.io.Serializable;

public class SkillFactory implements Serializable {
    public AbstractSkill create(FightAction fightAction) {
        if (fightAction == FightAction.SMASH) {
            return new SmashSkill();
        }
        return null;
    }
}
