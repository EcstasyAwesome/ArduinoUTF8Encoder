package com.github.arduinoencoder;

import java.util.Scanner;

public class InnerApp {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                if (scanner.hasNext()) {
                    String text = scanner.next();
                    if (text.equals("exit")) break;
                    else System.out.println(SymbolHandler.getInstance().processText(text));
                }
            }
        }
    }
}
