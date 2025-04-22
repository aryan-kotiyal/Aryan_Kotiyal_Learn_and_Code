package com.atm.simulator.ATM_Machine_Simulator.exception;

public class CardBlockedException extends RuntimeException {
    public CardBlockedException(String message) {
        super(message);
    }
}
