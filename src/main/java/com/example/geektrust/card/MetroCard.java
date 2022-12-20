package com.example.geektrust.card;

import com.example.geektrust.exceptions.BalanceLowException;
import com.example.geektrust.exceptions.InvalidAmountException;

public class MetroCard implements Card {
    private String cardNumber;
    private int balance;

    public MetroCard(final String cardNumber, final int balance) {
        this.cardNumber = cardNumber;
        this.balance = balance;
    }

    @Override
    public String getCardNumber() {
        return this.cardNumber;
    }

    @Override
    public int getBalance() {
        return balance;
    }

    /**
     * Function to pay amount using the card, if having sufficient balance left. Else throws exception
     * @param amount ->
     * @throws InvalidAmountException
     * @throws BalanceLowException
     */
    @Override
    public void pay(final int amount) throws InvalidAmountException, BalanceLowException {
        if (amount <= 0) {
            throw new InvalidAmountException("Amount should be positive integer");
        }
        if (balance < amount) {
            throw new BalanceLowException("Balance is low");
        }
        balance -= amount;
    }

    /**
     * Function to recharge with the exact amount to travel next, if not sufficient balance available for that card
     * @param amount
     * @throws InvalidAmountException
     */
    @Override
    public void recharge(int amount) throws InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Amount should be positive integer");
        }
        balance += amount;
    }
}
