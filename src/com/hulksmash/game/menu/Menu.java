package com.hulksmash.game.menu;

import com.hulksmash.game.Game;
import com.hulksmash.game.GameUIController;
import com.hulksmash.game.userinterface.StringParserService;
import com.hulksmash.game.userinterface.UserCommandApiService;
import com.hulksmash.game.save.LoadGameService;
import com.hulksmash.game.save.SaveGameService;

import java.util.List;

public class Menu {
    private GameUIController gameUIController;

    public Menu(GameUIController gameUIController) {
        this.gameUIController = gameUIController;
    }

    public void drawMainMenuOptions() {
        MenuOption option;
        do {
            UserCommandApiService.promptWithOptionCode("Create player", MenuOption.NEW_GAME.getValue());
            String userChoice = UserCommandApiService.readWithPromptWithOptionCode("Load game", MenuOption.LOAD_GAME.getValue());
            option = MenuOption.create(userChoice);
        } while (option != MenuOption.NEW_GAME && option != MenuOption.LOAD_GAME);
        executeOption(option);
    }

    public void drawMenuAfterGameOptions() {
        MenuOption option;
        do {
            UserCommandApiService.promptWithOptionCode("Start new game by creating new player", MenuOption.NEW_GAME.getValue());
            UserCommandApiService.promptWithOptionCode("Save game", MenuOption.SAVE_GAME.getValue());
            String userChoice = UserCommandApiService.readWithPromptWithOptionCode("Quit", MenuOption.QUIT.getValue());
            option = MenuOption.create(userChoice);
        } while (option != MenuOption.NEW_GAME && option != MenuOption.SAVE_GAME && option != MenuOption.QUIT);
        executeOption(option);
    }

    private void executeOption(MenuOption userChoice) {
        if (MenuOption.NEW_GAME == userChoice) {
            gameUIController.goToCreatePlayer();
        } else if (MenuOption.LOAD_GAME == userChoice) {
            LoadGameService loadGameService = new LoadGameService();
            String saveFileToLoadName = getFileNameToLoad(loadGameService.getAllSaveName());
            if (saveFileToLoadName == null) {
                drawMainMenuOptions();
            }
            Game g = loadGameService.load(saveFileToLoadName);
            gameUIController.goToGame(g);
        } else if (MenuOption.SAVE_GAME == userChoice) {
            SaveGameService saveGameService = new SaveGameService();
            saveGameService.save(gameUIController.getGame());
            System.out.println("Game saved.");
        } else if (MenuOption.QUIT == userChoice) {
            System.exit(0);
        }
    }

    private String getFileNameToLoad(List<String> allSaveName) {

        if (allSaveName.isEmpty()) {
            UserCommandApiService.print("There is no save files.");
            return null;
        }

        String chosenIndex = UserCommandApiService.promptWithCodeGeneratedByIndex(allSaveName);
        StringParserService stringParserService = new StringParserService();
        while (!stringParserService.tryParseToInt(chosenIndex) || stringParserService.getIntNumber() <= 0 || stringParserService.getIntNumber() > allSaveName.size()) {
            UserCommandApiService.print("Chosen save file doesn't exist, try again.");
            chosenIndex = UserCommandApiService.read();
        }
        return allSaveName.get(stringParserService.getIntNumber() - 1);
    }
}
