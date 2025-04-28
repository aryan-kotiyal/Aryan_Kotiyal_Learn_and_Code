package org.example.model;

public class Wallet {

    private float value;

    public Wallet(float initialAmount) {
        this.value = initialAmount;
    }

    public float getTotalMoney() {
        return value;
    }

    public void creditMoney(float deposit) {
        value += deposit;
    }

    public void debitMoney(float debit) {
        value -= debit;
    }

    public boolean hasEnoughMoney(float amount) {
        return value >= amount;
    }

}
