package org.example.flashcard_fx.model;

public class FlashCard {
    private String frontCard;
    private String backCard;


    public FlashCard() {

    }

    public FlashCard( String cardName, String cardValue) {
        this.frontCard = cardName;
        this.backCard =  cardValue;

    }

    public void setFrontCard(String frontCard) {
        this.frontCard = frontCard;
    }
    public void setBackCard(String backCard) {
        this.backCard = backCard;
    }

    public String getFrontCard() {
        return frontCard;
    }
    public String getBackCard() {
        return backCard;
    }
    @Override
    public String toString() {
        return getFrontCard()+" "+getBackCard();
    }

}
