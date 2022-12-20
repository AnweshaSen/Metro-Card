package com.example.geektrust.exceptions;

/**
 * Exception to be thrown, when commands are not in their expected structure or having an invalid value
 */
public class InvalidCommandException extends Exception{
    public InvalidCommandException(String errorMessage) { super(errorMessage); }
}
