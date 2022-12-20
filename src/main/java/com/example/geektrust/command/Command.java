package com.example.geektrust.command;

import com.example.geektrust.enums.CommandType;

/**
 * General structre of an input command
 */
public interface Command {
    public CommandType getType();
}
