package com.hulksmash.game.save;

import com.hulksmash.game.Game;
import com.hulksmash.game.userinterface.UserCommandApiService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class LoadGameService {
    public Game load(String logName) {
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(SaveGameService.SAVE_DIRECTORY + logName));
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Game game = (Game) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
            return game;

        } catch (ClassNotFoundException | IOException e) {
            UserCommandApiService.print("There was an error during loading save file.");
        }
        return null;
    }

    public List<String> getAllSaveName() {
        List<String> saveNames = new ArrayList<>();
        final File dir = new File(SaveGameService.SAVE_DIRECTORY);
        if (dir.listFiles() != null) {
            for (final File fileEntry : dir.listFiles()) {
                if (!fileEntry.isDirectory()) {
                    saveNames.add(fileEntry.getName());
                }
            }
        }
        return saveNames;
    }
}
