package com.github.arduinoencoder;

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

    private void processText(TextField textField, TextField resultField) {
        String text = textField.getText();
        if (!text.isEmpty()) resultField.setText(Encoder.getInstance().processText(text));
        else if (!resultField.getText().isEmpty()) resultField.clear();
    }

    private void copyToClipboard(TextField result) {
        ClipboardContent content = new ClipboardContent();
        content.putString(result.getText());
        Clipboard.getSystemClipboard().setContent(content);
    }

    @Override
    public void start(Stage stage) {
        VBox root = new VBox();
        root.setPadding(new Insets(5, 10, 0, 10));
        root.setSpacing(5);
        Label labelText = new Label("Enter text:");
        Label labelResult = new Label("Result:");
        labelResult.setPadding(new Insets(5, 0, 0, 0));
        TextField resultField = new TextField();
        resultField.setAlignment(Pos.CENTER_LEFT);
        resultField.setEditable(false);
        resultField.setPrefSize(500, 25);
        TextField textField = new TextField();
        textField.setAlignment(Pos.CENTER_LEFT);
        textField.setPrefSize(500, 25);
        textField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) processText(textField, resultField);
        });
        Button buttonProcess = new Button("Process text");
        buttonProcess.setFocusTraversable(false);
        buttonProcess.setOnAction(event -> processText(textField, resultField));
        Button buttonCopy = new Button("Copy result");
        buttonCopy.setFocusTraversable(false);
        buttonCopy.setOnAction(event -> {
            if (resultField.getText().length() > 0) copyToClipboard(resultField);
        });
        root.getChildren().addAll(labelText, textField, buttonProcess, labelResult, resultField, buttonCopy);
        Scene scene = new Scene(root);
        stage.setTitle("Arduino UTF-8 Encoder");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        App.launch(args);
    }

}