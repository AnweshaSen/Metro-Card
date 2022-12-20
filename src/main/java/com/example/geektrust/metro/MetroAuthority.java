package com.example.geektrust.metro;

import com.example.geektrust.card.Card;
import com.example.geektrust.card.MetroCard;
import com.example.geektrust.command.BalanceCommand;
import com.example.geektrust.command.CheckInCommand;
import com.example.geektrust.command.Command;
import com.example.geektrust.enums.CommandType;
import com.example.geektrust.enums.PassengerType;
import com.example.geektrust.enums.StationName;
import com.example.geektrust.exceptions.BalanceLowException;
import com.example.geektrust.metro.station.AirportStation;
import com.example.geektrust.metro.station.CentralStation;
import com.example.geektrust.metro.station.Station;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Class that represents the Metro Authority and handles the commands and transactions
 */
public class MetroAuthority {
    private Map<PassengerType, Integer> costMap;
    protected List<Card> cardList;
    private List<Station> stationList;
    protected Map<String, Integer> cardNumberToTripCount;

    public MetroAuthority(){
        this.costMap = new HashMap<PassengerType, Integer>() {{
            put(PassengerType.ADULT, 200);
            put(PassengerType.SENIOR_CITIZEN, 100);
            put(PassengerType.KID, 50);
        }};
        this.cardList = new ArrayList<>();
        this.stationList = new ArrayList<>();
        this.stationList.add(new AirportStation());
        this.stationList.add(new CentralStation());
        this.cardNumberToTripCount = new HashMap<>();
    }

    public void executeCommand(Command command) throws Exception {
        if (command.getType().equals(CommandType.BALANCE)) {
            BalanceCommand balanceCommand = (BalanceCommand) command;
            addBalance(balanceCommand.getMetroCardNumber(), balanceCommand.getBalance());
        } else if (command.getType().equals(CommandType.CHECK_IN)) {
            CheckInCommand checkInCommand = (CheckInCommand) command;
            executeCheckInCommand(checkInCommand.getMetroCardNumber(), checkInCommand.getPassengerCategory(), checkInCommand.getSourceStation());
        } else if (command.getType().equals(CommandType.PRINT_SUMMARY)) {
            printSummary();
        }
    }

    protected void addBalance(String metroCardNumber, Integer balance){
        int index = 0;
        for(;index<this.cardList.size();++index){
            if(this.cardList.get(index).getCardNumber().equals(metroCardNumber))
                break;
        }
        if(index < this.cardList.size()){  // Card already exists
            this.cardList.set(index, new MetroCard(metroCardNumber, balance)); // Replacing existing card balance. Sample not given in question
            return;
        }
        this.cardList.add(new MetroCard(metroCardNumber, balance));
        this.cardNumberToTripCount.put(metroCardNumber, 0);
    }

    protected void executeCheckInCommand(String metroCardNumber, PassengerType passengerType, StationName sourceStationName) throws Exception {
        Card card = cardList.stream().filter(c -> c.getCardNumber().equals(metroCardNumber)).collect(Collectors.toList()).get(0);
        Station station = stationList.stream().filter(s -> s.getStationName() == sourceStationName).collect(Collectors.toList()).get(0);

        boolean eligibleForDiscount = isEligibleForDiscount(metroCardNumber, sourceStationName);
        Integer cost = this.costMap.get(passengerType);
        Integer amountToDeduct = eligibleForDiscount ? cost / 2 : cost;
        Integer discount = eligibleForDiscount ? cost / 2 : 0;

        try {
            card.pay(amountToDeduct);
            station.addTrip(passengerType, amountToDeduct, discount);
        } catch (BalanceLowException exception) {   // Recharge and pay
            Integer difference = amountToDeduct - card.getBalance();
            Integer serviceFee = difference * 2 / 100;
            Integer amountToRecharge = difference + serviceFee;
            card.recharge(amountToRecharge);
            amountToDeduct += serviceFee;
            card.pay(amountToDeduct);
            station.addTrip(passengerType, amountToDeduct, discount);
        }
        this.cardNumberToTripCount.put(metroCardNumber, this.cardNumberToTripCount.get(metroCardNumber)+1);
    }

    protected Boolean isEligibleForDiscount(String metroCardNumber, StationName currentStationName){
        return (this.cardNumberToTripCount.get(metroCardNumber) != 0 && this.cardNumberToTripCount.get(metroCardNumber) % 2 != 0);
    }

    private void printSummary(){
        Station airportStation = stationList.stream().filter(s-> s.getStationName().equals(StationName.AIRPORT)).collect(Collectors.toList()).get(0);
        Station centralStation = stationList.stream().filter(s-> s.getStationName().equals(StationName.CENTRAL)).collect(Collectors.toList()).get(0);

        centralStation.printSummary();
        airportStation.printSummary();
    }

}
