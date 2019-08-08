package com.hulksmash.game;

import com.hulksmash.game.character.FightAction;
import com.hulksmash.game.character.MoveAction;

class ActionParser {
    private MoveAction moveAction;
    private GameAction gameAction;
    private FightAction fightAction;

    void parse(String actionCode) {
        reset();
        if (MoveAction.checkIfActionExist(actionCode)) {
            moveAction = MoveAction.create(actionCode);
        } else if (GameAction.checkIfActionExist(actionCode)) {
            gameAction = GameAction.create(actionCode);
        } else if (FightAction.checkIfActionExist(actionCode)) {
            fightAction = FightAction.create(actionCode);
        }
    }

    Action getAction() {
        if (moveAction != null) {
            return moveAction;
        } else if (gameAction != null) {
            return gameAction;
        } else if (fightAction != null) {
            return fightAction;
        }
        return null;
    }

    private void reset() {
        moveAction = null;
        gameAction = null;
        fightAction = null;
    }
}
