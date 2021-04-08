package com.github.ecstasyawesome.arduinoencoder;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

  @Override
  public void start(Stage stage) {
    var root = new VBox();
    root.setPadding(new Insets(5, 10, 5, 10));
    root.setSpacing(5);

    var labelText = new Label(Resources.LANGUAGE_BUNDLE.getString("app.text"));
    var labelResult = new Label(Resources.LANGUAGE_BUNDLE.getString("app.result"));
    labelResult.setPadding(new Insets(5, 0, 0, 0));

    var resultField = new TextField();
    resultField.setAlignment(Pos.CENTER_LEFT);
    resultField.setEditable(false);
    resultField.setPrefSize(500, 25);

    var textField = new TextField();
    textField.setPromptText(Resources.LANGUAGE_BUNDLE.getString("app.help"));
    textField.setAlignment(Pos.CENTER_LEFT);
    textField.setPrefSize(500, 25);
    textField.setOnKeyPressed(event -> {
      if (event.getCode() == KeyCode.ENTER) {
        processText(textField, resultField);
      }
    });

    var buttonProcess = new Button(Resources.LANGUAGE_BUNDLE.getString("app.process"));
    buttonProcess.setFocusTraversable(false);
    buttonProcess.setOnAction(event -> processText(textField, resultField));

    var buttonCopy = new Button(Resources.LANGUAGE_BUNDLE.getString("app.copy"));
    buttonCopy.setFocusTraversable(false);
    buttonCopy.setOnAction(event -> {
      if (resultField.getText().length() > 0) {
        copyToClipboard(resultField);
      }
    });

    root.getChildren()
        .addAll(labelText, textField, buttonProcess, labelResult, resultField, buttonCopy);

    var scene = new Scene(root);
    stage.setTitle(Resources.LANGUAGE_BUNDLE.getString("app.title"));
    stage.setScene(scene);
    stage.setResizable(false);
    stage.show();
  }

  private void processText(TextField textField, TextField resultField) {
    var text = textField.getText();
    if (!text.isEmpty()) {
      var builder = new StringBuilder();
      for (char character : text.toCharArray()) {
        var symbol = String.valueOf(character);
        builder.append(Resources.DICTIONARY_BUNDLE.getProperty(symbol, symbol));
      }
      resultField.setText(builder.toString());
    } else if (!resultField.getText().isEmpty()) {
      resultField.clear();
    }
  }

  private void copyToClipboard(TextField result) {
    var content = new ClipboardContent();
    content.putString(result.getText());
    Clipboard.getSystemClipboard().setContent(content);
  }

  public static void main(String[] args) {
    App.launch(args);
  }
}