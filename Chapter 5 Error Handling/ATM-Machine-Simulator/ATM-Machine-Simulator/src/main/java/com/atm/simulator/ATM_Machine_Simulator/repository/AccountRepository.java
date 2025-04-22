package com.atm.simulator.ATM_Machine_Simulator.repository;

import com.atm.simulator.ATM_Machine_Simulator.config.Constants;
import com.atm.simulator.ATM_Machine_Simulator.model.Account;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class AccountRepository {

    private Map<String, Account> accounts = new HashMap<>();
    private int atmBalance = Constants.ATM_BALANCE;
    private boolean serverConnected = true;

    public AccountRepository() {
        accounts.put("1234-5678-9876-5432", new Account("1234-5678-9876-5432", 1234, 3500));
    }

    public Account getAccount(String cardNumber) {
        return accounts.get(cardNumber);
    }

    public boolean isServerConnected() {
        return serverConnected;
    }

    public int getATMBalance() {
        return atmBalance;
    }

    public void reduceATMBalance(int amount) {
        atmBalance -= amount;
    }

    public void incrementPinAttempts(String cardNumber) {
        Account acc = accounts.get(cardNumber);
        if (acc != null) {
            acc.setInvalidAttempts(acc.getInvalidAttempts() + 1);
        }
    }
}