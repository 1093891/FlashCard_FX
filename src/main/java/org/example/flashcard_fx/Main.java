package org.example.flashcard_fx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.flashcard_fx.gui.FlashGui;
import org.example.flashcard_fx.model.CardList;
import org.example.flashcard_fx.util.SystemLoader;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        SystemLoader systemLoader = new SystemLoader();

        Scene scene = new Scene(new FlashGui(systemLoader).getView(), 500, 500);
        stage.setTitle("FlashCard FX");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.setOnCloseRequest(e -> {
            try {
                systemLoader.closeSystem();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}