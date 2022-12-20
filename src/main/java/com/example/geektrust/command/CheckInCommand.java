package com.example.geektrust.command;

import com.example.geektrust.enums.CommandType;
import com.example.geektrust.enums.PassengerType;
import com.example.geektrust.enums.StationName;

/**
 * Class for the "CHECK_IN" type command
 */
public class CheckInCommand implements Command{
    private final CommandType commandType = CommandType.CHECK_IN;
    private String metroCardNumber;
    private PassengerType passengerType;
    private StationName sourceStationName;

    public CheckInCommand(final String metroCardNumber, final PassengerType passengerType, final StationName sourceStationName){
        this.metroCardNumber = metroCardNumber;
        this.passengerType = passengerType;
        this.sourceStationName = sourceStationName;
    }

    @Override
    public CommandType getType(){
        return commandType;
    }

    public String getMetroCardNumber(){
        return metroCardNumber;
    }

    public PassengerType getPassengerCategory(){
        return passengerType;
    }

    public StationName getSourceStation(){
        return sourceStationName;
    }

}
