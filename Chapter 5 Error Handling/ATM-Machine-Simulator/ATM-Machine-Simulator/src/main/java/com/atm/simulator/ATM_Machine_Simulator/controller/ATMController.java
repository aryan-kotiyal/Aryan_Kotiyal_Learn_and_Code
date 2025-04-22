package com.atm.simulator.ATM_Machine_Simulator.controller;

import com.atm.simulator.ATM_Machine_Simulator.config.Constants;
import com.atm.simulator.ATM_Machine_Simulator.exception.*;
import com.atm.simulator.ATM_Machine_Simulator.model.ATMRequest;
import com.atm.simulator.ATM_Machine_Simulator.model.ATMResponse;
import com.atm.simulator.ATM_Machine_Simulator.service.ATMService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/atm")
public class ATMController {

    private final ATMService atmService;

    public ATMController(ATMService atmService) {
        this.atmService = atmService;
    }

    @PostMapping("/withdraw")
    public ResponseEntity<ATMResponse> withdraw(@RequestBody ATMRequest request) {
        try {
            String message = atmService.withdrawCash(request);
            return ResponseEntity.ok(new ATMResponse(message));
        } catch (InvalidPinException | InsufficientFundsException |
                 DailyLimitExceededException | CardBlockedException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ATMResponse(e.getMessage()));
        } catch (ServerConnectionException e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(new ATMResponse(Constants.SERVER_UNAVAILABLE_MSG));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ATMResponse("Unexpected error occurred"));
        }
    }
}