package com.example.geektrust.command;

import com.example.geektrust.enums.CommandType;
import com.example.geektrust.exceptions.BalanceLowException;
import com.example.geektrust.exceptions.InvalidCommandException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CommandParserTest {

    @Test
    public void parseCommand_success() throws Exception{
        List<String> commandStrings = new ArrayList<String>() {{
            add("BALANCE MC1 600");
        }};

        List<CommandType> commandTypesExpected = Arrays.asList(CommandType.BALANCE);
        List<CommandType> commandTypesActual = new ArrayList<>();

            List<String> commandTokens = Arrays.asList(commandStrings.get(0).split(" "));
            CommandParser commandParser = new CommandParser();
            Command command = commandParser.parseCommand(commandTokens);
            commandTypesActual.add(command.getType());

        assertEquals(commandTypesExpected, commandTypesActual);
    }

    @Test
    public void parseCommand_fail_InvalidCommandException() {
        List<String> commandStrings = new ArrayList<String>() {{
            add("BALANCE 600");     // structure of the BALANCE query is wrong
        }};

        try{
            for (String commandStr : commandStrings) {
                List<String> commandTokens = Arrays.asList(commandStr.split(" "));
                CommandParser commandParser = new CommandParser();
                assertThrows(
                        InvalidCommandException.class,
                        () -> commandParser.parseCommand(commandTokens),
                        "Expected parseCommand to throw, but it didn't"
                );
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

    @Test
    public void getBalanceCommand_success() throws Exception{
        List<String> commandStrings = new ArrayList<String>() {{
                add("BALANCE MC1 600");
                add("BALANCE MC2 500");
            }};

        List<CommandType> commandTypesExpected = Arrays.asList(CommandType.BALANCE, CommandType.BALANCE);
        List<CommandType> commandTypesActual = new ArrayList<>();

        for (String commandStr : commandStrings) {
            List<String> commandTokens = Arrays.asList(commandStr.split(" "));
            CommandParser commandParser = new CommandParser();
            Command command = commandParser.parseCommand(commandTokens);
            commandTypesActual.add(command.getType());
        }

        assertEquals(commandTypesExpected, commandTypesActual);
    }

    @Test
    public void getBalanceCommand_fail_InvalidCommandException() {
        List<String> commandStrings = new ArrayList<String>() {{
            add("BALANCE MC1 -600");        // negative balance should not be a valid input
        }};

        try{
            for (String commandStr : commandStrings) {
                List<String> commandTokens = Arrays.asList(commandStr.split(" "));
                CommandParser commandParser = new CommandParser();
                assertThrows(
                        InvalidCommandException.class,
                        () -> commandParser.parseCommand(commandTokens),
                        "Expected getBalanceCommand to throw, but it didn't"
                );
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
