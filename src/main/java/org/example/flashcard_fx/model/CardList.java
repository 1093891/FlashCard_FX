package org.example.flashcard_fx.util;

import org.example.flashcard_fx.FlashCard;

import java.util.ArrayList;
import java.util.List;

public class CardList {
    private List<FlashCard> CardList;

    public CardList(){
        CardList = new ArrayList<>();
    }

    public CardList(List<FlashCard> CardList){
        this.CardList = CardList;
    }
    public List<FlashCard> getCardList() {
        return CardList;
    }
    public void setCardList(List<FlashCard> cardList) {
        CardList = cardList;
    }

}
