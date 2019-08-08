package com.hulksmash.game;

import com.hulksmash.game.character.Player;
import com.hulksmash.game.character.PlayerEditor;
import com.hulksmash.game.fight.FightSystemBasic;
import com.hulksmash.game.menu.Menu;
import com.hulksmash.game.userinterface.ColorsForConsole;
import com.hulksmash.game.userinterface.UserCommandApiService;

import java.io.Serializable;

import static com.hulksmash.game.Constant.WELCOME_MESSAGE;

public class GameUIController implements Serializable {
    private Game game;
    void goToMainMenu() {
        UserCommandApiService.print(ColorsForConsole.ANSI_RED + WELCOME_MESSAGE +ColorsForConsole.ANSI_RESET);
        Menu menu = new Menu(this);
        menu.drawMainMenuOptions();
    }

    void goToMenuAfterGame() {
        Menu menu = new Menu(this);
        menu.drawMenuAfterGameOptions();
    }

    public void goToCreatePlayer() {
        PlayerEditor playerEditor = new PlayerEditor(this);
        playerEditor.create();
    }

    public void goToGame(Player player) {
        game = new Game(player, this, new FightSystemBasic());
        game.mainLoop();
    }

    public void goToGame(Game game) {
        game.mainLoop();
    }

    public Game getGame() {
        return game;
    }
}
