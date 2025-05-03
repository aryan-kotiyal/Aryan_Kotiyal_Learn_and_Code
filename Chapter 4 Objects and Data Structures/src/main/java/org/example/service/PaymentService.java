package org.example.service;

import org.example.model.Customer;

public class PaymentService {

    public boolean processPayment(Customer customer, float amount) {

        return customer.pay(amount);
    }

}
