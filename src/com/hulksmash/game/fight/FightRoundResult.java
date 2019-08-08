package com.hulksmash.game.fight;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class FightRoundResult implements Serializable {
    private int damageToPlayer;
    private int damageToEnemy;
    private boolean enemyDown;
    private long experience;
    private String skillName;
    private Map<String, Double> enemiesHealth = new HashMap<>();

    void setDamageToPlayer(int damageToPlayer) {
        this.damageToPlayer = damageToPlayer;
    }

    void setDamageToEnemy(int damageToEnemy) {
        this.damageToEnemy = damageToEnemy;
    }

    void setEnemyDown(boolean enemyDown) {
        this.enemyDown = enemyDown;
    }

    void setExperience(long experience) {
        this.experience = experience;
    }

    public String getSummary() {
        StringBuilder message = new StringBuilder(String.format("%s deal %d damage to enemy. Enemy did %d damage to you " +
                "as counterattack.\r\n", skillName, damageToEnemy, damageToPlayer));
        for (String name : enemiesHealth.keySet()) {
            double hp = enemiesHealth.get(name);
            message.append(name).append(": ").append(hp).append(" hp").append("\r\n");

        }
        return message.toString();
    }

    void setSkillName(String name) {
        skillName = name;
    }

    public boolean isEnemyDown() {
        return enemyDown;
    }

    public long getExperience() {
        return experience;
    }


    void setEnemiesHealth(String name, Double hp) {
        if (enemiesHealth.containsKey(name)) {
            enemiesHealth.replace(name, hp);
        } else {
            enemiesHealth.put(name, hp);
        }
    }

}
