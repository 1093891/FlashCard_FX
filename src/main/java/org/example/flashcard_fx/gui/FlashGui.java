package org.example.flashcard_fx.gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.flashcard_fx.model.CardList;
import org.example.flashcard_fx.model.FlashCard;
import org.example.flashcard_fx.util.SystemLoader;


public class FlashGui extends Application {
    private BorderPane mainPane;
    private VBox front;
    private VBox back;
    private GridPane gridLayout;
    private GridPane addCardLayout;

    private SystemLoader systemLoader;
    private int currentCard = 0;
    private boolean isFront = true;

    public FlashGui(SystemLoader systemLoader){
        this.systemLoader = systemLoader;
        mainPane = new BorderPane();
        front = new VBox();
        back = new VBox();
        gridLayout = new GridPane();
        addCardLayout= new GridPane();


        initialize();

    }
    public void initialize(){
        Label title =new Label("Welcome to my flashcard");
        title.setStyle("-fx-font-size: 20; -fx-text-fill: #000000;-fx-font-weight: bold; -fx-start-margin: 10px; ");
        VBox titleBox = new VBox();
        titleBox.getChildren().add(title);
        titleBox.setAlignment(Pos.CENTER);
        mainPane.setTop(titleBox);
        mainPane.setStyle("-fx-content-display: center; -fx-alignment: center; -fx-background-color: #5d989d");
        mainPane.setCenter(front);
        // button section
        Button nextButton = new Button("Next");
        nextButton.setOnAction(e -> nextCard());
        Button flipCard = new Button("Flip Card");
        flipCard.setOnAction(e -> flipCard());

        Button addButton = new Button("Add");
        addButton.setOnAction(e -> addCard());
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> deleteCard());


        gridLayout.add(flipCard,0,0);
        gridLayout.add(nextButton,1,0);
        gridLayout.add(addButton,0,1);
        gridLayout.add(deleteButton,1,1);
        gridLayout.setHgap(10);
        gridLayout.setVgap(10);
        gridLayout.setAlignment(Pos.CENTER);
        gridLayout.setPadding(new javafx.geometry.Insets(10,10,10,10));

        // styling buttons
        for(Node node : gridLayout.getChildren()){
            if(node instanceof Button){
                Button button = (Button) node;
                button.setStyle("-fx-font-size: 15; -fx-text-alignment: center; -fx-text-fill: #0a0a0a;-fx-font-weight: bold; -fx-start-margin: 10px; ");
                button.setPrefSize(100,50);
            }
        }
        mainPane.setBottom(gridLayout);




        setFrontCard();
    }
    public void flipCard(){
       if(isFront){
           isFront = false;
           setBackCard();
       }
       else{
           isFront = true;
           setFrontCard();
       }
    }
    public void nextCard(){
        if(currentCard < CardList.getCardList().size() - 1){
            currentCard++;
            isFront = true;
            setFrontCard();
        }
        else{
            System.out.println("No more cards available.");
            currentCard = 0; // Reset to the first card if at the end
            isFront = true;
            setFrontCard();
        }

    }
    public void addCard(){
        addCardLayout.getChildren().clear();

        Label frontLabel = new Label("Front:");
        Label backLabel = new Label("Back:");
        TextField frontField = new TextField();
        TextField backField = new TextField();
        Button saveButton = new Button("Save");
        saveButton.setOnAction(e ->{
           String frontText = frontField.getText();
           String backText = backField.getText();
           if(!(frontText.isEmpty() || backText.isEmpty())){
               CardList.getCardList().add(new FlashCard(frontText,backText));
               new Alert(Alert.AlertType.INFORMATION, "The card added successfully").showAndWait();
               setFrontCard();
           }
          else {
            new Alert(Alert.AlertType.ERROR, "Please fill in both fields.").showAndWait();
            setFrontCard();
          }
        });
        addCardLayout.add(frontLabel, 0, 0);
        addCardLayout.add(frontField, 1, 0);
        addCardLayout.add(backLabel, 0, 1);
        addCardLayout.add(backField, 1, 1);
        addCardLayout.add(saveButton, 1, 2);
        addCardLayout.setHgap(10);
        addCardLayout.setVgap(10);
        addCardLayout.setAlignment(Pos.CENTER);
        addCardLayout.setPadding(new Insets(10,10,10,10));
        for(Node node : addCardLayout.getChildren()){
            if(node instanceof Button){
                Button button = (Button) node;
                button.setStyle("-fx-font-size: 15; -fx-text-alignment: center; -fx-text-fill: #0a0a0a;-fx-font-weight: bold; -fx-start-margin: 10px; ");
                button.setPrefSize(100,50);
            }
            else if(node instanceof TextField){
                TextField textField = (TextField) node;
                textField.setStyle("-fx-font-size: 15; -fx-text-alignment: center; -fx-text-fill: #0a0a0a;-fx-font-weight: bold; -fx-start-margin: 10px; ");
            }
        }
        mainPane.setCenter(addCardLayout);
        mainPane.getCenter().setStyle("-fx-background-color: #9d8b5d");

    }
    public void deleteCard(){
        if(!CardList.getCardList().isEmpty() && currentCard < CardList.getCardList().size()){
            Dialog dialog = new Dialog();
            dialog.setTitle("Delete Card");
            dialog.setContentText("Are you sure you want to delete this card?");
            ButtonType deleteButtonType = new ButtonType("Delete", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(deleteButtonType, ButtonType.CANCEL);
            dialog.setOnCloseRequest(e -> {
                if(dialog.getResult() == deleteButtonType){
                    deleteCurrentCard();
                }
            });
            dialog.showAndWait();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No Cards");
            alert.setHeaderText(null);
            alert.setContentText("There are no cards to delete.");
            alert.showAndWait();
        }

    }
    public void deleteCurrentCard(){
        if(!CardList.getCardList().isEmpty() && currentCard < CardList.getCardList().size()){
            CardList.getCardList().remove(currentCard);
            if(currentCard >= CardList.getCardList().size()) {
                currentCard = CardList.getCardList().size() - 1; // Adjust current card index
            }
            new Alert(Alert.AlertType.INFORMATION, "The card deleted successfully").showAndWait();
            setFrontCard();
        } else {
            System.out.println("No cards to delete.");
        }
    }
    public void setFrontCard(){
        System.out.println("set front card");
        try{
            front.getChildren().clear();
            front.getChildren().add(new Label(CardList.getCardList().get(currentCard).getFrontCard()));
            front.setAlignment(Pos.CENTER);
            mainPane.setCenter(front);
        }
        catch (IndexOutOfBoundsException e){
            System.out.println(e.getMessage());
        }
        front.setStyle("-fx-background-color: #e3cd7f; -fx-padding: 10px; -fx-font-size: 20; -fx-text-alignment: center; -fx-font-weight: bold; -fx-start-margin: 10px; ");
    }

    public void setBackCard(){
        System.out.println("set back card");
        try{
            back.getChildren().clear();
            back.getChildren().add(new Label(CardList.getCardList().get(currentCard).getBackCard()));
            back.setAlignment(Pos.CENTER);
            
            mainPane.setCenter(back);
        }
        catch (IndexOutOfBoundsException e){
            System.out.println(e.getMessage());
        }
        back.setStyle("-fx-background-color: #00ff00; -fx-padding: 10px; -fx-font-size: 20; -fx-text-alignment: center; -fx-font-weight: bold; -fx-start-margin: 10px; ");

    }

    public BorderPane getView(){
        return mainPane;
    }


    @Override
    public void start(Stage primaryStage) {

    }
}
