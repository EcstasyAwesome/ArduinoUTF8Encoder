package com.github.ecstasyawesome.arduinoencoder;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Properties;
import java.util.ResourceBundle;

public class Resources {

  public static final ResourceBundle LANGUAGE_BUNDLE;
  public static final Properties DICTIONARY_BUNDLE;

  static {
    LANGUAGE_BUNDLE = ResourceBundle.getBundle("locale");
    DICTIONARY_BUNDLE = new Properties();
    var inputStream =
        Resources.class.getClassLoader().getResourceAsStream("dictionary.properties");
    try (var reader =
        new InputStreamReader(Objects.requireNonNull(inputStream), StandardCharsets.UTF_8)) {
      DICTIONARY_BUNDLE.load(reader);
    } catch (Exception exception) {
      throw new ExceptionInInitializerError(exception);
    }
  }
}
