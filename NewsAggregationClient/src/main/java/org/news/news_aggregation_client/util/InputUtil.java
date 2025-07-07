package org.news.news_aggregation_client.util;

import java.util.Scanner;
import java.util.regex.Pattern;

public class InputUtil {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    public static String readNonEmpty(Scanner scanner, String prompt) {
        String input;
        do {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
        } while (input.isEmpty());
        return input;
    }

    public static String readEmail(Scanner scanner) {
        String input;
        do {
            System.out.print("Email: ");
            input = scanner.nextLine().trim();
            if (!EMAIL_PATTERN.matcher(input).matches()) {
                System.out.println("Invalid email format.");
                input = "";
            }
        } while (input.isEmpty());
        return input;
    }

    public static String readPassword(Scanner scanner) {
        return readNonEmpty(scanner, "Password: ");
    }
}
