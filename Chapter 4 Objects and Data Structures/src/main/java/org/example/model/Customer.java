package org.example.model;

public class Customer {

    private String firstName;
    private String lastName;
    private Wallet myWallet;

    public Customer(String firstName, String lastName, float initialAmount) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.myWallet = new Wallet(initialAmount);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public boolean pay(float amount) {
        if (myWallet.hasEnoughMoney(amount)) {
            myWallet.debitMoney(amount);
            return true;
        }
        return false;
    }

    public float checkBalance() {
        return myWallet.getTotalMoney();
    }
}
