package com.github.arduinoencoder;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Encoder {

    private final static Encoder INSTANCE = new Encoder();
    private final Map<Character, String> symbols = new HashMap<>();

    private Encoder() {
        loadSymbols();
    }

    public static Encoder getInstance() {
        return INSTANCE;
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

    public String processText(String text) {
        StringBuilder stringBuilder = new StringBuilder();
        for (char symbol : text.toCharArray()) {
            stringBuilder.append(symbols.getOrDefault(symbol, String.valueOf(symbol)));
        }
        return stringBuilder.toString();
    }
}