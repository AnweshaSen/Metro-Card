package com.example.geektrust;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.example.geektrust.command.Command;
import com.example.geektrust.command.CommandParser;
import com.example.geektrust.metro.MetroAuthority;

public class Main {
    public static void main(String[] args) {
        List<String> commandStrings = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(args[0]);
            Scanner sc = new Scanner(fis);
            while (sc.hasNextLine()) {
                commandStrings.add(sc.nextLine());
            }
            sc.close();
            Main main = new Main();
            main.execute(commandStrings);
        } catch (Exception e) {
            System.out.println("Program terminated with exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void execute(List<String> commandStrings) throws Exception {
        MetroAuthority metroAuthority = new MetroAuthority();
        for (String commandStr : commandStrings) {
            List<String> commandTokens = Arrays.asList(commandStr.split(" "));
            CommandParser commandParser = new CommandParser();
            Command command = commandParser.parseCommand(commandTokens);
            metroAuthority.executeCommand(command);
        }
    }
}
