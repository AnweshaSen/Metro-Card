package com.example.geektrust.command;

import com.example.geektrust.enums.CommandType;

/**
 * Class for the "BALANCE" type command
 */
public class BalanceCommand implements Command{
    private final CommandType commandType = CommandType.BALANCE;
    private String metroCardNumber;
    private Integer balance;

    public BalanceCommand(String metroCardNumber, Integer balance){
        this.metroCardNumber = metroCardNumber;
        this.balance = balance;
    }

    @Override
    public CommandType getType(){
        return commandType;
    }

    public String getMetroCardNumber(){
        return metroCardNumber;
    }

    public Integer getBalance(){
        return balance;
    }
}
