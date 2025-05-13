package com.atm.simulator.ATM_Machine_Simulator.model;

public class ATMResponse {
    private String message;

    public ATMResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}