package com.example.geektrust.metro.station;

import com.example.geektrust.enums.PassengerType;
import com.example.geektrust.enums.StationName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class to handle all Metro Station related operations
 */
public class Station {
    StationName stationName;
    Map<PassengerType, Integer> passengerCount;
    Map<PassengerType, Integer> amountCollectedPerPassengerType;
    Integer amountCollected;
    Integer discountGiven;

    public Station(StationName stationName){
        this.stationName = stationName;
        this.passengerCount = new HashMap<PassengerType, Integer>(){{
            put(PassengerType.ADULT, 0);
            put(PassengerType.SENIOR_CITIZEN, 0);
            put(PassengerType.KID, 0);
        }};
        this.amountCollectedPerPassengerType = new HashMap<PassengerType, Integer>(){{
            put(PassengerType.ADULT, 0);
            put(PassengerType.SENIOR_CITIZEN, 0);
            put(PassengerType.KID, 0);
        }};
        this.amountCollected = 0;
        this.discountGiven = 0;
    }

    public StationName getStationName(){
        return stationName;
    }
    public Integer getAmountCollected() { return amountCollected; }

    /**
     * Function to note per station which pa
     * @param passengerType
     * @param amount
     * @param discountGiven
     */
    public void addTrip(PassengerType passengerType, Integer amount, Integer discountGiven) {
        this.passengerCount.put(passengerType, this.passengerCount.get(passengerType) + 1);
        this.amountCollectedPerPassengerType.put(passengerType, this.amountCollectedPerPassengerType.get(passengerType) + amount);
        this.amountCollected += amount;
        this.discountGiven += discountGiven;
    }

    public void printSummary() {
        printTotalCollection();
        printPassengerTypeSummary();
    }

    private void printTotalCollection() {
        System.out.println("TOTAL_COLLECTION " + this.stationName + " " + this.amountCollected + " " + this.discountGiven);
    }

    private void printPassengerTypeSummary() {
        System.out.println("PASSENGER_TYPE_SUMMARY");

        List<PassengerType> sortedPassengerType = sortedPassengerTypeBasedOnAmountCollected();
        for (PassengerType pt: sortedPassengerType) {
            if(passengerCount.get(pt) > 0)
                System.out.println(pt.toString() + " " + passengerCount.get(pt));
        }
    }

    protected List<PassengerType> sortedPassengerTypeBasedOnAmountCollected() {
        List<PassengerType> result = new ArrayList<>();
        PassengerType adltPass = PassengerType.ADULT;
        PassengerType snStPass = PassengerType.SENIOR_CITIZEN;
        PassengerType kidPass = PassengerType.KID;

        result.add(adltPass);
        result.add(kidPass);
        result.add(snStPass);

        /**
         * In case sorting needed on amount collected per passenger tyep
         */
//        if(amountCollectedPerPassengerType.get(adltPass) >= amountCollectedPerPassengerType.get(snStPass))
//        {
//            if(amountCollectedPerPassengerType.get(adltPass) >= amountCollectedPerPassengerType.get(kidPass)){
//                result.add(adltPass);
//                if(amountCollectedPerPassengerType.get(snStPass) > amountCollectedPerPassengerType.get(kidPass)){
//                    result.add(snStPass);
//                    result.add(kidPass);
//                }
//                else{
//                    result.add(kidPass);
//                    result.add(snStPass);
//                }
//            }
//            else{
//                result.add(kidPass);
//                result.add(adltPass);
//                result.add(snStPass);
//            }
//        }
//        else if(amountCollectedPerPassengerType.get(snStPass) >= amountCollectedPerPassengerType.get(kidPass)){
//            if(amountCollectedPerPassengerType.get(snStPass) > amountCollectedPerPassengerType.get(kidPass)){
//                result.add(snStPass);
//                if(amountCollectedPerPassengerType.get(adltPass) >= amountCollectedPerPassengerType.get(kidPass)){
//                    result.add(adltPass);
//                    result.add(kidPass);
//                }
//                else{
//                    result.add(kidPass);
//                    result.add(adltPass);
//                }
//            }
//            else{
//                result.add(kidPass);
//                result.add(snStPass);
//                result.add(adltPass);
//            }
//        }

        return result;
    }
}
