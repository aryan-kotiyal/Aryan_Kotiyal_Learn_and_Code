package com.atm.simulator.ATM_Machine_Simulator.exception;

public class DailyLimitExceededException extends RuntimeException{
    public DailyLimitExceededException(String message) {
        super(message);
    }
}
