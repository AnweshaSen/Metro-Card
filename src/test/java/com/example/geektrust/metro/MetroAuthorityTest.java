package com.example.geektrust.metro;

import com.example.geektrust.enums.PassengerType;
import com.example.geektrust.enums.StationName;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class MetroAuthorityTest {

    @Test
    public void executeCheckInCommandTest() throws Exception{
        MetroAuthority m1 = new MetroAuthority();

        m1.addBalance("MC1", 300);

        m1.executeCheckInCommand("MC1", PassengerType.KID, StationName.AIRPORT);
        m1.executeCheckInCommand("MC1", PassengerType.KID, StationName.CENTRAL);

        assertEquals(225, m1.cardList.get(0).getBalance());     // 300 - (50+(50*(50/100))) is the left over amount
        assertEquals(2, m1.cardNumberToTripCount.get("MC1"));   // that card travelled two times as of now
    }

}
