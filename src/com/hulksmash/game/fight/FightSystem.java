package com.hulksmash.game.fight;

import com.hulksmash.game.board.Board;
import com.hulksmash.game.character.Enemy;
import com.hulksmash.game.character.Player;

import java.io.Serializable;
import java.util.List;

public abstract class FightSystem implements Serializable {
    public FightRoundResult fight(Board board, Player player, List<Enemy> enemies, AbstractSkill skill) {
        FightRoundResult fightRoundResult = applySkill(board, player, enemies, skill);
        long deadCount = countDeadEnemy(enemies);
        fightRoundResult.setEnemyDown(deadCount > 0);
        fightRoundResult.setExperience(deadCount * 10);
        player.addExperience(fightRoundResult.getExperience());
        removeDeadEnemies(enemies);
        fightRoundResult.setSkillName(skill.getName());
        return fightRoundResult;
    }

    private long countDeadEnemy(List<Enemy> enemies) {
        return enemies.stream().filter(e -> e.getHealth() <= 0).count();
    }

    private void removeDeadEnemies(List<Enemy> enemies) {
        enemies.removeIf(e -> e.getHealth() <= 0);
    }

    protected abstract FightRoundResult applySkill(Board board, Player player, List<Enemy> enemies, AbstractSkill skill);


}
