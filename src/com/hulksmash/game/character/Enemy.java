package com.hulksmash.game.character;

import com.hulksmash.game.Constant;
import com.hulksmash.game.board.Board;
import com.hulksmash.game.userinterface.ColorsForConsole;

public class Enemy extends Character {
    private final String appearance = "8";
    private String name;

    public Enemy(EnemyAttributeValueGenerator enemyAttributeValueGenerator, Position position, String name) {
        super(enemyAttributeValueGenerator.getHealth(), enemyAttributeValueGenerator.getStrength(), enemyAttributeValueGenerator.getResistance(),
                enemyAttributeValueGenerator.getLuck(), Constant.ENEMY_INITIAL_EXPERIENCE, position);
        this.name = name;
    }

    public void draw(Board board) {
        board.setEnemy(position, ColorsForConsole.ANSI_RED_BACKGROUND + appearance + ColorsForConsole.ANSI_RESET);
    }

    @Override
    public void damage(int damage) {
        health -= damage;
    }

    public String getName() {
        return name;
    }
}
