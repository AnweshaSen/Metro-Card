package com.example.geektrust;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class MainTest {

    @Test
    public void testMain() throws Exception {
        List<String> commandStrings = new ArrayList<String>() {{
            add("BALANCE MC1 600");
            add("BALANCE MC2 500");
            add("BALANCE MC3 50");
            add("BALANCE MC4 50");
            add("BALANCE MC5 200");
            add("CHECK_IN MC1 ADULT CENTRAL");
            add("CHECK_IN MC2 SENIOR_CITIZEN CENTRAL");
            add("CHECK_IN MC1 ADULT AIRPORT");
            add("CHECK_IN MC3 KID AIRPORT");
            add("CHECK_IN MC4 ADULT AIRPORT");
            add("CHECK_IN MC5 KID AIRPORT");
            add("PRINT_SUMMARY");
        }};
        Main main = new Main();
        main.execute(commandStrings);
    }

    @Test
    public void testMain2() throws Exception {
        List<String> commandStrings = new ArrayList<String>() {{
            add("BALANCE MC1 400");
            add("BALANCE MC2 100");
            add("BALANCE MC3 50");
            add("BALANCE MC4 50");
            add("CHECK_IN MC1 SENIOR_CITIZEN AIRPORT");
            add("CHECK_IN MC2 KID AIRPORT");
            add("CHECK_IN MC3 ADULT CENTRAL");
            add("CHECK_IN MC1 SENIOR_CITIZEN CENTRAL");
            add("CHECK_IN MC3 ADULT AIRPORT");
            add("CHECK_IN MC3 ADULT CENTRAL");
            add("PRINT_SUMMARY");
        }};
        Main main = new Main();
        main.execute(commandStrings);
    }

    @Test
    public void testMain3() throws Exception {
        List<String> commandStrings = new ArrayList<String>() {{
            add("BALANCE MC1 100");
            add("BALANCE MC2 100");
            add("BALANCE MC3 250");
            add("BALANCE MC4 400");
            add("CHECK_IN MC1 KID CENTRAL");
            add("CHECK_IN MC2 KID AIRPORT");
            add("CHECK_IN MC3 ADULT CENTRAL");
            add("CHECK_IN MC1 SENIOR_CITIZEN CENTRAL");
            add("CHECK_IN MC3 ADULT AIRPORT");
            add("CHECK_IN MC3 ADULT CENTRAL");
            add("PRINT_SUMMARY");
        }};
        Main main = new Main();
        main.execute(commandStrings);
    }
}
