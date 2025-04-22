package com.atm.simulator.ATM_Machine_Simulator.exception;

public class InvalidPinException extends RuntimeException {
    public InvalidPinException(String message) {
        super(message);
    }
}
