package org.news.news_aggregation_client.menu;

import org.news.news_aggregation_client.model.NewsApiSource;
import org.news.news_aggregation_client.service.AdminService;
import org.news.news_aggregation_client.session.UserContext;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class AdminMenu {
    private final Scanner scanner = new Scanner(System.in);
    private final AdminService adminService = new AdminService();
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy hh:mm a");

    public void start() {
        while (true) {
            System.out.printf("\nWelcome Admin %s!%n", UserContext.getUsername());
            System.out.println("Please choose the options below:");
            System.out.println("1. View the list of external servers and status");
            System.out.println("2. View the external server’s details");
            System.out.println("3. Update/Edit the external server’s details");
            System.out.println("4. Add new News Category");
            System.out.println("5. Logout");
            System.out.print("Choice: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> viewServers();
                case "2" -> viewServerDetails();
                case "3" -> updateServer();
                case "4" -> addCategory();
                case "5" -> {
                    System.out.println("Logging out...");
                    return;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }
    private void viewServers() {
        List<NewsApiSource> sources = adminService.getAllApiSources();
        System.out.println("\nEXTERNAL SERVERS:");
        for (NewsApiSource source : sources) {
            System.out.printf("ID: %d | Name: %s | Active: %b | Last Accessed: %s\n",
                    source.getId(),
                    source.getName(),
                    source.isActive(),
                    source.getLastAccessedAt() != null ? source.getLastAccessedAt().format(formatter) : "N/A");
        }
    }

    private void viewServerDetails() {
        System.out.print("Enter server ID to view details: ");
        Long id = Long.parseLong(scanner.nextLine());
        NewsApiSource source = adminService.getApiSourceDetails(id);
        if (source != null) {
            System.out.println("\n--- Server Details ---");
            System.out.println("ID: " + source.getId());
            System.out.println("Name: " + source.getName());
            System.out.println("Base URL: " + source.getUrl());
            System.out.println("API Key: " + source.getApiKey());
            System.out.println("Active: " + source.isActive());
            System.out.println("-----------------------");
        } else {
            System.out.println("Server not found.");
        }
    }

    private void updateServer() {
        System.out.print("Enter Server ID to update: ");
        Long id = Long.parseLong(scanner.nextLine());

        NewsApiSource existing = adminService.getApiSourceDetails(id);
        if (existing == null) {
            System.out.println("Sever not found.");
            return;
        }

        System.out.print("Enter New Name [" + existing.getName() + "]: ");
        String name = scanner.nextLine();
        System.out.print("Enter New Base URL [" + existing.getUrl() + "]: ");
        String url = scanner.nextLine();
        System.out.print("Enter New API Key [" + existing.getApiKey() + "]: ");
        String key = scanner.nextLine();

        existing.setName(name.isBlank() ? existing.getName() : name);
        existing.setUrl(url.isBlank() ? existing.getUrl() : url);
        existing.setApiKey(key.isBlank() ? existing.getApiKey() : key);


        adminService.updateApiSource(existing);
        System.out.println("Server updated successfully.");
    }

    private void addCategory() {
        System.out.print("Enter Category Name: ");
        String name = scanner.nextLine();
        adminService.addCategory(name);
    }
}
