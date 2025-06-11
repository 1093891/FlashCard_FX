package org.example.flashcard_fx.util;

import org.example.flashcard_fx.model.CardList;
import java.io.IOException;


public class SystemLoader {
    private final FileHandler fileHandler;
    private  String FILE_NAME;

    public SystemLoader() throws IOException {
        fileHandler = new FileHandler();
        initiateFileLoad();
    }
    public void initiateFileLoad() throws IOException {
         FILE_NAME = "cards.txt";
         CardList.setCardList(fileHandler.readCard(FILE_NAME));
    }
    public void closeSystem() throws IOException {
        System.out.println("saving the data...");
        fileHandler.saveCard(FILE_NAME);
    }
}
