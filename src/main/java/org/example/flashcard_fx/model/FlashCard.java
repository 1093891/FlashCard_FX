package org.example.flashcard_fx;

import java.util.HashMap;

public class FlashCard {
    private int cardId;
    private String frontCard;
    private String backCard;



    public FlashCard() {

    }

    public FlashCard(int cardId, String cardName, String cardValue) {
        this.cardId = cardId;
        this.frontCard = cardName;
        this.backCard =  cardValue;

    }


    // setter methods
    public void setCardId(int cardId) {
        this.cardId = cardId;
    }
    public void setFrontCard(String frontCard) {
        this.frontCard = frontCard;
    }
    public void setBackCard(String backCard) {
        this.backCard = backCard;
    }

    // getter methods
    public int getCardId() {
        return cardId;
    }
    public String getFrontCard() {
        return frontCard;
    }
    public String getBackCard() {
        return backCard;
    }
    @Override
    public String toString() {
        return getCardId()+" "+getFrontCard()+" "+getBackCard();
    }

}
