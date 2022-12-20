package com.example.geektrust.card;

import com.example.geektrust.exceptions.BalanceLowException;
import com.example.geektrust.exceptions.InvalidAmountException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MetroCardTest {

    @Test
    public void pay_success() {
        MetroCard m2 = new MetroCard("MC1", 600);

        try{
            m2.pay(200);    // Card balance is 200, and we are trying to pay 100. So a valid scenario
        }catch (Exception ex){
            ex.printStackTrace();
        }

        assertEquals(400, m2.getBalance());
    }

    @Test
    public void pay_fail_InvalidAmountException() {
        MetroCard m1 = new MetroCard("MC1", 600);

        assertThrows(
                InvalidAmountException.class,
                () -> m1.pay(-100),         // Negative amount to pay should not be a valid scenario
                "Expected pay() to throw, but it didn't"
        );
    }

    @Test
    public void pay_fail_BalanceLowException() {
        MetroCard m1 = new MetroCard("MC1", 100);

        assertThrows(
                BalanceLowException.class,
                () -> m1.pay(200),      // Card balance 100, but we are expecting it to pay 200. So recharge required
                "Expected pay() to throw, but it didn't"
        );
    }

    @Test
    public void rechargeTest() {
        MetroCard m1 = new MetroCard("MC1", 20);

        try{
            m1.recharge(100);
        }catch (Exception ex){
            ex.printStackTrace();
        }

        assertEquals(120, m1.getBalance());     // Initial balance was 20 bucks, and added 100 bucks to it. So ultimate balance left is 120 bucks
    }

}
