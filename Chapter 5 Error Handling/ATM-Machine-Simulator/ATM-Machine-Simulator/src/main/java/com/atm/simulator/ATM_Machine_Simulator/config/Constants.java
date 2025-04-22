package com.atm.simulator.ATM_Machine_Simulator.config;

public class Constants {
    // ATM Constants
    public static final int MAX_PIN_ATTEMPTS = 3;
    public static final int ATM_BALANCE = 8000;
    public static final int DAILY_LIMIT = 1000;

    // Error Messages
    public static final String INSUFFICIENT_FUNDS_ACCOUNT_MSG = "Insufficient balance in account.";
    public static final String INSUFFICIENT_FUNDS_ATM_MSG = "ATM has insufficient cash.";
    public static final String DAILY_LIMIT_EXCEEDED_MSG = "Daily withdrawal limit exceeded.";
    public static final String CARD_BLOCKED_MSG = "Card is blocked after 3 invalid attempts.";
    public static final String SERVER_UNAVAILABLE_MSG = "Server unavailable.";
    public static final String INVALID_PIN_MSG = "Invalid PIN entered.";
    
}