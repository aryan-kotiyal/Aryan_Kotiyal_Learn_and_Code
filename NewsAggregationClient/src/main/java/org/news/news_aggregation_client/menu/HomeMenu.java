package org.news.news_aggregation_client.menu;

import org.news.news_aggregation_client.model.UserLoginRequest;
import org.news.news_aggregation_client.model.UserSignupRequest;
import org.news.news_aggregation_client.service.AuthService;
import org.news.news_aggregation_client.util.InputUtil;

import java.util.Scanner;

public class HomeMenu {
    private final Scanner scanner = new Scanner(System.in);
    private final AuthService authService = new AuthService();

    public void start() {
        while (true) {
            System.out.println("\nWelcome to the News Aggregator application. Please choose the options below.");
            System.out.println("1. Login");
            System.out.println("2. Sign up");
            System.out.println("3. Exit");
            System.out.print("Choice: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> login();
                case "2" -> signup();
                case "3" -> {
                    System.out.println("Exiting application. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void login() {
        System.out.println("\n--- Login ---");
        String email = InputUtil.readNonEmpty(scanner, "Email: ");
        String password = InputUtil.readNonEmpty(scanner, "Password: ");
        UserLoginRequest request = new UserLoginRequest(email, password);
        String role = authService.login(request);
        if ("ADMIN".equalsIgnoreCase(role)) {
            new AdminMenu().start();
        } else if ("USER".equalsIgnoreCase(role)) {
            new UserMenu().start();
        }
    }

    private void signup() {
        System.out.println("\n--- Sign Up ---");
        String username = InputUtil.readNonEmpty(scanner, "Username: ");
        String email = InputUtil.readEmail(scanner);
        String password = InputUtil.readPassword(scanner);
        UserSignupRequest request = new UserSignupRequest(username, email, password);
        authService.signup(request);
    }
}
