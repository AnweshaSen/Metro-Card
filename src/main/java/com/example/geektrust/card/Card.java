package com.example.geektrust.card;

/**
 * General structure of a card, which can be extended to any type of card later.
 */
public interface Card {
    public String getCardNumber();
    public int getBalance();
    public void pay(int amount) throws Exception;
    public void recharge(int amount) throws Exception;
}
