package com.example.geektrust.exceptions;

/**
 * Exception to be thrown, when an invalid amount is asked to be debited from a card
 */
public class InvalidAmountException extends Exception{
    public InvalidAmountException(String errorMessage) { super(errorMessage); }
}
