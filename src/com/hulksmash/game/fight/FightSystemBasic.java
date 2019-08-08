package com.hulksmash.game.fight;

import com.hulksmash.game.board.Board;
import com.hulksmash.game.character.Enemy;
import com.hulksmash.game.character.Player;
import com.hulksmash.game.character.Position;

import java.util.List;
import java.util.stream.Collectors;

public class FightSystemBasic extends FightSystem {
    @Override
    public FightRoundResult applySkill(Board board, Player player, List<Enemy> enemies, AbstractSkill skill) {
        FightRoundResult fightRoundResult = new FightRoundResult();
        List<Enemy> enemiesInRadiusInFourDirection = null;
        if (skill.isDamageAllNearEnemy()) {
            enemiesInRadiusInFourDirection = getEnemiesInRadiusInFourDirection(player.getPosition(), enemies, skill.damageRadius());
        }
        enemiesInRadiusInFourDirection.forEach(e -> skill.calculateDamage(player, e, fightRoundResult));
        return fightRoundResult;
    }

    private List<Enemy> getEnemiesInRadiusInFourDirection(Position position, List<Enemy> enemies, int damageRadius) {
        return enemies.stream().filter(e -> e.getPosition().isNextToInFourDirectionWithRadius(position, damageRadius)).collect(Collectors.toList());
    }
}
