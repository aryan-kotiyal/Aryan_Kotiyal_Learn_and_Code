package com.atm.simulator.ATM_Machine_Simulator.exception;

public class ServerConnectionException extends RuntimeException {
    public ServerConnectionException() {
        super("Unable to connect with server.");
    }
}
