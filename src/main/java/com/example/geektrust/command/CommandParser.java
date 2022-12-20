package com.example.geektrust.command;

import com.example.geektrust.enums.PassengerType;
import com.example.geektrust.enums.StationName;
import com.example.geektrust.exceptions.InvalidCommandException;

import java.util.List;

/**
 * Class to parse the input command file and initialize objects with that values
 */
public class CommandParser {
    public Command parseCommand(List<String> commandTokens) throws InvalidCommandException {
        Command command;
        switch (commandTokens.get(0)) {
            case "BALANCE":
                command = getBalanceCommand(commandTokens);
                break;
            case "CHECK_IN":
                command = getCheckInCommand(commandTokens);
                break;
            case "PRINT_SUMMARY":
                command = getPrintSummaryCommand(commandTokens);
                break;
            default:
                throw new InvalidCommandException("Invalid Command");
        }
        return command;
    }

    protected BalanceCommand getBalanceCommand(List<String> commandTokens) throws InvalidCommandException {
        try {
            String cardNumber = commandTokens.get(1);
            Integer balance = Integer.parseInt(commandTokens.get(2));
            if(balance < 0)
                throw new InvalidCommandException("Invalid Command");
            return new BalanceCommand(cardNumber, balance);
        } catch (Exception e) {
            throw new InvalidCommandException("Invalid Command");
        }
    }

    protected CheckInCommand getCheckInCommand(List<String> commandTokens) throws InvalidCommandException {
        try {
            return new CheckInCommand(commandTokens.get(1), PassengerType.valueOf(commandTokens.get(2)), StationName.valueOf(commandTokens.get(3)));
        } catch (Exception e) {
            throw new InvalidCommandException("Invalid Command");
        }
    }

    protected PrintSummaryCommand getPrintSummaryCommand(List<String> commandTokens) throws InvalidCommandException {
        try {
            return new PrintSummaryCommand();
        } catch (Exception e) {
            throw new InvalidCommandException("Invalid Command");
        }
    }
}
