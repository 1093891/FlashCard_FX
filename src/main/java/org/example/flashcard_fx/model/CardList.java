package org.example.flashcard_fx.model;

import java.util.ArrayList;
import java.util.List;

public class CardList {
    private static List<FlashCard> CardList;

    public CardList(){
        CardList = new ArrayList<>();
    }

    public  CardList(List<FlashCard> CardList){
        this.CardList = CardList;
    }
    public static List<FlashCard> getCardList() {
        return CardList;
    }
    public static void setCardList(List<FlashCard> cardList) {
        CardList = cardList;
    }

}
