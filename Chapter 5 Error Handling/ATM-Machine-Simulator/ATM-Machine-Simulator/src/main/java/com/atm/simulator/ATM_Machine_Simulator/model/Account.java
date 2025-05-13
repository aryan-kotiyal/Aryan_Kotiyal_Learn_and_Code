package com.atm.simulator.ATM_Machine_Simulator.model;

public class Account {
    private String cardNumber;
    private Integer pin;
    private int balance;
    private int dailyWithdrawn = 0;
    private boolean blocked = false;
    private int invalidAttempts = 0;

    public Account(String cardNumber, Integer pin, int balance) {
        this.cardNumber = cardNumber;
        this.pin = pin;
        this.balance = balance;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Integer getPin() {
        return pin;
    }

    public void setPin(Integer pin) {
        this.pin = pin;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getDailyWithdrawn() {
        return dailyWithdrawn;
    }

    public void setDailyWithdrawn(int dailyWithdrawn) {
        this.dailyWithdrawn = dailyWithdrawn;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public int getInvalidAttempts() {
        return invalidAttempts;
    }

    public void setInvalidAttempts(int invalidAttempts) {
        this.invalidAttempts = invalidAttempts;
    }
}
