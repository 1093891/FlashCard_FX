package org.example.flashcard_fx.util;

import org.example.flashcard_fx.model.CardList;
import org.example.flashcard_fx.model.FlashCard;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    //private final String FILE_NAME = "cards.txt";

    public void creteNewFile(String FILE_NAME) throws IOException {
        File file = new File(FILE_NAME);
        if(file.createNewFile()){
            System.out.println("File created successfully!");

        }
        else {
            System.out.println("File already exists!");
        }
    }

    public List<FlashCard> readCard(String FILE_NAME) throws IOException {
        File file = new File(FILE_NAME);
        creteNewFile(FILE_NAME);
        List<FlashCard> cardItem =  new ArrayList<>();
        if(file.isFile() && file.canRead()){
            try(BufferedReader br = new BufferedReader(new FileReader(file))){
                String line;
                while((line=br.readLine())!= null){
                    String DELMITER = ":::";
                    String[] lineSplit = line.split(DELMITER);
                    String cardName = lineSplit[0];
                    String cardValue = lineSplit[1];


                    cardItem.add(new FlashCard(cardName,cardValue) );
                }

            }
            catch(Exception e){
                System.out.println("Error while reading text file: "+e.getMessage());
            }


        }
        return cardItem;
    }

    public void saveCard( String filePath) throws IOException {
        File file = new File(filePath);
        creteNewFile(filePath);
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file))){
            for(FlashCard card : CardList.getCardList()){
                String DELMITER = ":::";
                // Write the card in the format "frontCard:::backCard"
                bw.write(card.getFrontCard() + DELMITER + card.getBackCard());
                bw.newLine();
            }
        }
        catch(Exception e){
            System.out.println("Error while writing text file: "+e.getMessage());
        }


    }

}
