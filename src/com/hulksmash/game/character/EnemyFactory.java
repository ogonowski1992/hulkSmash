package com.hulksmash.game.character;

import com.hulksmash.game.Constant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class EnemyFactory {
    public List<Enemy> create(int boundX, int boundY, int amountToCreate) {
        List<Enemy> enemies = new ArrayList<>(amountToCreate);
        List<Position> takenPositions = new ArrayList<>(amountToCreate);
        EnemyAttributeValueGeneratorRandomly enemyStatGeneratorRandomly = new EnemyAttributeValueGeneratorRandomly();
        for (int i = 0; i < amountToCreate; i++) {
            Position position = getNewPosition(boundX, boundY, takenPositions);
            enemyStatGeneratorRandomly.generateAttributesSet(Constant.ENEMY_POINT_TO_ASSIGN_TO_ATTRIBUTES);
            Enemy enemy = new Enemy(enemyStatGeneratorRandomly, position, getName(i));
            enemies.add(enemy);
        }
        return enemies;
    }

    private List<String> names = Arrays.asList("Arcane Past",
            "Brave Desciple",
            "Love of Witchcraft",
            "Codes of Hope",
            "Search and Tale",
            "Knights and War",
            "Mortalforce",
            "Embercry",
            "Bloodmind",
            "Runedoom");

    private String getName(int i) {
        i = i % names.size();
        return names.get(i);
    }

    private Position getNewPosition(int boundX, int boundY, List<Position> takenPositions) {
        while (true) {
            int x = ThreadLocalRandom.current().nextInt(1, boundX);
            int minY = 1;
            boolean isEnemyToCloseToPlayerInOneDimension = x < 5;
            if (isEnemyToCloseToPlayerInOneDimension) {
                minY = 5;
            }
            int y = ThreadLocalRandom.current().nextInt(minY, boundY);
            Position position = new Position(x, y);
            if (takenPositions.stream().allMatch(p -> p.getY() != position.getY() || p.getX() != position.getX())) {
                takenPositions.add(position);
                return position;
            }
        }
    }
}
