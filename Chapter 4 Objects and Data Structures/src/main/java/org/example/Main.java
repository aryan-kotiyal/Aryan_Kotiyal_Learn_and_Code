package org.example;

import org.example.model.Customer;
import org.example.service.PaymentService;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter customer's first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter customer's last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter initial wallet amount: ");
        float initialAmount = Float.parseFloat(scanner.nextLine());

        Customer customer = new Customer(firstName, lastName, initialAmount);

        System.out.print("Enter payment amount: ");
        float payment = Float.parseFloat(scanner.nextLine());

        PaymentService paymentService = new PaymentService();

        System.out.println("Customer: " + customer.getFirstName());
        System.out.println("Requesting payment of $" + payment);

        boolean success = paymentService.processPayment(customer, payment);

        if (success) {
            System.out.println("Payment successful. Remaining balance: $" + customer.checkBalance());
        } else {
            System.out.println("Insufficient funds. Come back later.");
        }

        scanner.close();
    }
}
