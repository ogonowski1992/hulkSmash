package com.hulksmash.game.fight;

import com.hulksmash.game.board.Board;
import com.hulksmash.game.character.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class FightSystemTest {
    private Board board = new Board(7, 7);
    private Player player = new Player(100, "", 100, 10, 0, 0);
    private AbstractSkill skill = new SmashSkill();
    private List<Enemy> enemies = new ArrayList<>();
    private FightSystem fightSystem = new FightSystemBasic();
    private Enemy enemy;

    @Test
    public void fightDamageOnlyToEnemy() {
        setupGameObjects(10, 15);
        FightRoundResult fightRoundResult = fightSystem.fight(board, player, enemies, skill);
        Assert.assertEquals(15, enemy.getHealth(), 0);
        Assert.assertEquals(100, player.getHealth(), 0);
    }

    private void setupGameObjects(int enemyStrength, int enemyResistance) {
        Position position = new Position(player.getPosition());
        position.increaseX();
        EnemyAttributeValueGeneratorRandomlyMock enemyStatGeneratorRandomly = new EnemyAttributeValueGeneratorRandomlyMock();
        enemyStatGeneratorRandomly.generateAttributesSet(0);
        enemyStatGeneratorRandomly.setStrength(enemyStrength);
        enemyStatGeneratorRandomly.setResistance(enemyResistance);
        enemy = new Enemy(enemyStatGeneratorRandomly, position, "Blood");
        enemies.add(enemy);
    }


    @Test
    public void fightKillEnemy() {
        int experienceStart = player.getExperience();
        setupGameObjects(1, 0);
        FightRoundResult fightRoundResult = fightSystem.fight(board, player, enemies, skill);
        Assert.assertEquals(0, enemy.getHealth(), 0);
        Assert.assertTrue(fightRoundResult.isEnemyDown());
        int experienceAfterFight = player.getExperience();
        Assert.assertEquals(experienceStart + 10, experienceAfterFight);
    }

    @Test
    public void fightEnemyAttackAlso() {
        setupGameObjects(20, 10);
        FightRoundResult fightRoundResult = fightSystem.fight(board, player, enemies, skill);
        Assert.assertEquals(90, player.getHealth(), 0);
    }
}
