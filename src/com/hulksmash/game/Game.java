package com.hulksmash.game;

import com.hulksmash.game.board.Board;
import com.hulksmash.game.character.*;
import com.hulksmash.game.fight.AbstractSkill;
import com.hulksmash.game.fight.FightRoundResult;
import com.hulksmash.game.fight.FightSystem;
import com.hulksmash.game.fight.SkillFactory;
import com.hulksmash.game.userinterface.UserCommandApiService;

import java.io.Serializable;
import java.util.List;

public class Game implements Serializable {
    private final Player player;
    private List<Enemy> enemies;
    private final GameUIController gameUIController;
    private Board board;
    private GameStatus gameStatus;
    private FightSystem fightSystem;
    private SkillFactory skillFactory;

    Game(Player player, GameUIController gameUIController, FightSystem fightSystem) {
        this.player = player;
        this.gameUIController = gameUIController;
        this.fightSystem = fightSystem;
        skillFactory = new SkillFactory();
        initBoard();
        initEnemies();
    }

    private void initEnemies() {
        EnemyFactory enemyFactory = new EnemyFactory();
        enemies = enemyFactory.create(board.getWidth() - 1, board.getHeight() - 1, Constant.ENEMY_TU_CREATE_AMOUNT);
    }

    private void initBoard() {
        board = new Board(Constant.BOARD_WIDTH, Constant.BOARD_HEIGHT);
    }

    void mainLoop() {
        ActionParser actionParser = new ActionParser();
        gameStatus = GameStatus.PLAYING;

        while (gameStatus != GameStatus.END) {
            board.clareBoardForNextFrame();
            player.draw(board);
            for (Enemy e : enemies) {
                e.draw(board);
            }
            String mapToPrint = board.draw();
            UserCommandApiService.printMap(mapToPrint);
            UserCommandApiService.print(player.toString());

            String actionCode = UserCommandApiService.readWithPrompt("Action: ");
            actionParser.parse(actionCode);
            Action action = actionParser.getAction();
            if (action instanceof MoveAction) {
                player.makeAction((MoveAction) action, board);
            } else if (action instanceof GameAction) {
                makeAction((GameAction) action);
            } else if (action instanceof FightAction) {
                FightAction fightAction = (FightAction) action;
                AbstractSkill skill = skillFactory.create(fightAction);
                FightRoundResult fightResult = fightSystem.fight(board, player, enemies, skill);
                UserCommandApiService.print(fightResult.getSummary());
                if (fightResult.isEnemyDown()) {
                    UserCommandApiService.print(String.format("Enemy down ! Got %d EX points! ", fightResult.getExperience()));
                }

            }
        }

        gameUIController.goToMenuAfterGame();

    }

    private void makeAction(GameAction action) {
        if (action == GameAction.QUITE_GAME) {
            gameStatus = GameStatus.END;
        }
    }
}
