package com.github.arduinoencoder;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Properties;
import java.util.ResourceBundle;

public class Resources {

    public static final ResourceBundle LANGUAGE_BUNDLE;
    public static final Properties DICTIONARY_BUNDLE;

    static {
        LANGUAGE_BUNDLE = ResourceBundle.getBundle("locale", new CustomResourceBundleControl());
        DICTIONARY_BUNDLE = new Properties();
        InputStream inputStream = Resources.class.getClassLoader().getResourceAsStream("dictionary.properties");
        try (Reader reader = new InputStreamReader(Objects.requireNonNull(inputStream), StandardCharsets.UTF_8)) {
            DICTIONARY_BUNDLE.load(reader);
        } catch (Exception ignored) {
        }
    }
}
