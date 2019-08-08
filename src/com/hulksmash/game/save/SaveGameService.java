package com.hulksmash.game.save;


import com.hulksmash.game.Game;
import com.hulksmash.game.userinterface.UserCommandApiService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;

public class SaveGameService {
    static final String SAVE_DIRECTORY = "save/";

    public void save(Game game) {

        try {
            crateSaveDirectoryIfNotExist();
            FileOutputStream fileOutputStream = new FileOutputStream(new File((SAVE_DIRECTORY + "hulkSmashSave_" + LocalTime.now()).replaceAll(":", "_").replaceAll("\\.", "_") + ".txt"));
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(game);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            UserCommandApiService.print("There was an error during saving save file.");
        }
    }

    private void crateSaveDirectoryIfNotExist() throws IOException {
        File f = new File(SAVE_DIRECTORY);
        if (!f.exists() || !f.isDirectory()) {
            Path path = Paths.get(SAVE_DIRECTORY);
            Files.createDirectories(path);
        }
    }

}
