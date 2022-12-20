package com.example.geektrust.metro.station;

import com.example.geektrust.enums.PassengerType;
import com.example.geektrust.enums.StationName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class StationTest {
    @Test
    public void testsortedPassengerTypeBasedOnAmountCollected() {

        Station mc1 = new Station(StationName.CENTRAL);
        Station mc2 = new Station(StationName.AIRPORT);

        mc1.addTrip(PassengerType.ADULT, 200, 0);
        mc1.addTrip(PassengerType.SENIOR_CITIZEN, 100, 0);
        mc1.addTrip(PassengerType.SENIOR_CITIZEN, 50, 50);
        mc1.addTrip(PassengerType.SENIOR_CITIZEN, 100, 0);
        mc2.addTrip(PassengerType.ADULT, 100, 100);
        mc2.addTrip(PassengerType.KID, 50, 0);
        mc2.addTrip(PassengerType.ADULT, 200, 0);
        mc2.addTrip(PassengerType.KID, 50, 0);

        List<PassengerType> expectedResultmc1 = Arrays.asList(PassengerType.ADULT, PassengerType.KID, PassengerType.SENIOR_CITIZEN);
        List<PassengerType> resultFromFunctionmc1 = mc1.sortedPassengerTypeBasedOnAmountCollected();

        List<PassengerType> expectedResultmc2 = Arrays.asList(PassengerType.ADULT, PassengerType.KID);
        List<PassengerType> resultFromFunctionmc2 = mc2.sortedPassengerTypeBasedOnAmountCollected();

        assertEquals(expectedResultmc1, resultFromFunctionmc1);
        assertNotEquals(expectedResultmc2, resultFromFunctionmc2);

    }
}
