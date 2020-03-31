package com.github.arduinoencoder;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SymbolHandler implements TextHandler {

    private static SymbolHandler instance;
    private Map<Character, String> symbols = new HashMap<>();

    private SymbolHandler() {
        loadSymbols();
    }

    public static SymbolHandler getInstance() {
        if (instance == null) instance = new SymbolHandler();
        return instance;
    }

    private void loadSymbols() {
        try (Scanner scanner = new Scanner(getClass().getResourceAsStream("/ABC.txt"), "UTF-8")) {
            scanner.useDelimiter(";");
            while (scanner.hasNext()) {
                String data = scanner.next().replaceAll("[^а-яА-Яa-zA-ZёЁ\\d=\\\\]", "");
                if (data.matches("^.=(\\\\\\d{3}|.)$")) symbols.putIfAbsent(data.charAt(0), data.substring(2));
            }
        }
    }

    @Override
    public String processText(String text) {
        StringBuilder stringBuilder = new StringBuilder();
        for (char symbol : text.toCharArray()) {
            stringBuilder.append(symbols.getOrDefault(symbol, String.valueOf(symbol)));
        }
        return stringBuilder.toString();
    }
}