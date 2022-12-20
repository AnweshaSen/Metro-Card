package com.example.geektrust.command;

import com.example.geektrust.enums.CommandType;

/**
 * Class for the "PRINT_SUMMARY" type command
 */
public class PrintSummaryCommand implements Command{
    private final CommandType commandType = CommandType.PRINT_SUMMARY;

    @Override
    public CommandType getType(){
        return commandType;
    }
}
