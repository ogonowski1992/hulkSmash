package com.hulksmash.game.userinterface;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class UserCommandApiService {
    private static BufferedReader br;
    private static BufferedWriter bufferedWriter;

    private UserCommandApiService() {
    }

    static {
        br = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
        bufferedWriter = UserCommandApiService.getBufferedWriter(1000);
    }

    public static String read() {
        try {
            return br.readLine();
        } catch (IOException e) {
            print("An unexpected error occurred. Please, restart the game.");
            return "";
        }
    }

    public static void print(String text) {
        System.out.println(text);
    }


    public static String readWithPrompt(String prompt) {
        print(prompt);
        return read();
    }

    public static String readWithPromptWithOptionCode(final String prompt, final String code) {
        promptWithOptionCode(prompt, code);
        return read();
    }

    public static Integer readWithPromptForInt(String prompt) {
        String text = readWithPrompt(prompt);

            StringParserService stringParserService = new StringParserService();
            if (stringParserService.tryParseToInt(text)) {
                return stringParserService.getIntNumber();
            }

        return null;
    }

    public static void promptWithOptionCode(String prompt, String code) {
        print(String.format("[%s] - %s", code, prompt));
    }

    private static BufferedWriter getBufferedWriter(final int bufferSize) {
        return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FileDescriptor.out), StandardCharsets.US_ASCII), bufferSize);
    }

    public static void printMap(String map) {

        try {
            bufferedWriter.write(map);
            bufferedWriter.flush();
        } catch (IOException e) {
            print("An unexpected error occurred. Please, restart the game.");
        }
    }

    public static String promptWithCodeGeneratedByIndex(List<String> allSaveName) {
        for (int i = 0; i < allSaveName.size(); i++) {
            print(String.format("[%d] - %s", i + 1, allSaveName.get(i)));
        }
        return read();
    }
}
