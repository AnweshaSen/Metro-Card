package com.example.geektrust.exceptions;

/**
 * Exception to be thrown, when not sufficient balance is available to travel for a card
 */
public class BalanceLowException extends Exception{
    public BalanceLowException(String errorMessage) {
        super(errorMessage);
    }
}
