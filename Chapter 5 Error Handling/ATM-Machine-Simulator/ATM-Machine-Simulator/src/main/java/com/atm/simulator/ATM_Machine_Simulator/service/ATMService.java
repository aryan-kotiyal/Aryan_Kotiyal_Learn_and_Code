package com.atm.simulator.ATM_Machine_Simulator.service;

import com.atm.simulator.ATM_Machine_Simulator.config.Constants;
import com.atm.simulator.ATM_Machine_Simulator.exception.*;
import com.atm.simulator.ATM_Machine_Simulator.model.ATMRequest;
import com.atm.simulator.ATM_Machine_Simulator.model.Account;
import com.atm.simulator.ATM_Machine_Simulator.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class ATMService {

    private final AccountRepository repo;
    private final int dailyLimit = Constants.DAILY_LIMIT;
    private final int maxPinAttempts = Constants.MAX_PIN_ATTEMPTS;

    public ATMService(AccountRepository repo) {
        this.repo = repo;
    }

    public String withdrawCash(ATMRequest request) {
        Account acc = repo.getAccount(request.getCardNumber());

        if (acc == null || !repo.isServerConnected()) {
            throw new ServerConnectionException();
        }

        if (acc.isBlocked()) {
            throw new CardBlockedException("Card is blocked due to multiple invalid attempts.");
        }

        if (!acc.getPin().equals(request.getPin())) {
            repo.incrementPinAttempts(request.getCardNumber());
            if (acc.getInvalidAttempts() >= maxPinAttempts) {
                acc.setBlocked(true);
                throw new CardBlockedException(Constants.CARD_BLOCKED_MSG);
            }
            throw new InvalidPinException(Constants.INVALID_PIN_MSG);
        }

        if (request.getAmount() > repo.getATMBalance()) {
            throw new InsufficientFundsException(Constants.INSUFFICIENT_FUNDS_ATM_MSG);
        }

        if (request.getAmount() > acc.getBalance()) {
            throw new InsufficientFundsException(Constants.INSUFFICIENT_FUNDS_ACCOUNT_MSG);
        }

        if (acc.getDailyWithdrawn() + request.getAmount() > dailyLimit) {
            throw new DailyLimitExceededException(Constants.DAILY_LIMIT_EXCEEDED_MSG);
        }

        acc.setBalance(acc.getBalance() - request.getAmount());
        acc.setDailyWithdrawn(acc.getDailyWithdrawn() + request.getAmount());
        repo.reduceATMBalance(request.getAmount());

        return "Withdrawal successful. Remaining balance: " + acc.getBalance();
    }
}