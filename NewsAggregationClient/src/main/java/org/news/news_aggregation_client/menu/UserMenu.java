package org.news.news_aggregation_client.menu;

import org.news.news_aggregation_client.model.NewsArticle;
import org.news.news_aggregation_client.model.Notification;
import org.news.news_aggregation_client.service.NewsService;
import org.news.news_aggregation_client.service.NotificationService;
import org.news.news_aggregation_client.session.UserContext;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class UserMenu {
    private final Scanner scanner = new Scanner(System.in);
    private final NewsService newsService = new NewsService();
    private final NotificationService notificationService = new NotificationService();

    public void start() {
        while (true) {
            String username = UserContext.getUsername();
            System.out.printf("\nWelcome to the News Application, %s! Date: %s Time:%s\n",
                    username,
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy")),
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("h:mma")));

            System.out.println("Please choose the options below");
            System.out.println("1. Headlines");
            System.out.println("2. Saved Articles");
            System.out.println("3. Search");
            System.out.println("4. Notifications");
            System.out.println("5. Logout");
            System.out.print("Choice: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> showHeadlinesMenu();
                case "2" -> showSavedArticlesMenu();
                case "3" -> showSearchMenu();
                case "4" -> showNotificationMenu();
                case "5" -> {
                    System.out.println("Logging out...");
                    return;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void showHeadlinesMenu() {
        while (true) {
            String username = UserContext.getUsername();
            System.out.printf("\nWelcome to the News Application, %s! Date: %s Time:%s\n",
                    username,
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy")),
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("h:mma")));
            System.out.println("Please choose the options below");
            System.out.println("1. Today");
            System.out.println("2. Date range");
            System.out.println("3. Back");
            System.out.print("Choice: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> showTodaysHeadlinesMenu();
                case "2" -> {
                    System.out.print("Enter start date (yyyy-MM-dd): ");
                    String startInput = scanner.nextLine();
                    System.out.print("Enter end date (yyyy-MM-dd): ");
                    String endInput = scanner.nextLine();
                    try {
                        LocalDate startDate = LocalDate.parse(startInput);
                        LocalDate endDate = LocalDate.parse(endInput);

                        showCategoryMenu(startDate.atStartOfDay(), endDate.plusDays(1).atStartOfDay());

                    } catch (Exception e) {
                        System.out.println("Invalid date format. Please use yyyy-MM-dd.");
                    }
                }
                case "3" -> {
                    System.out.println("Returning to previous menu...");
                    return;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void showTodaysHeadlinesMenu() {
        List<NewsArticle> articles = newsService.getTodaysNews();

        if (articles.isEmpty()) {
            System.out.println("No news articles found for today or yesterday.");
            return;
        }

        while (true) {
            System.out.println("\n____________________________________________________");
            System.out.println("H E A D L I N E S");
            System.out.println("____________________________________________________");
            for (NewsArticle article : articles) {
                System.out.println("Article Id: " + article.getId());
                System.out.println(article.getTitle());
                System.out.println(article.getContent());
                System.out.println("source: " + article.getSource());
                System.out.println("URL: " + article.getUrl());
                System.out.println("Category: " + article.getCategoryNames());
                System.out.println("____________________________________________________");
            }

            System.out.println("Options:");
            System.out.println("1. Back");
            System.out.println("2. Logout");
            System.out.println("3. Save Article");
            System.out.println("4. Read Full Article");
            System.out.print("Choice: ");

            String option = scanner.nextLine();
            switch (option) {
                case "1" -> { return; }
                case "2" -> {
                    System.out.println("Logging out...");
                    System.exit(0);
                }
                case "3" -> {
                    System.out.print("Enter article ID to save: ");
                    Long articleId = Long.parseLong(scanner.nextLine());
                    newsService.saveArticle(articleId, UserContext.getUserId());
                }
                case "4" -> {
                    System.out.print("Enter article ID to read: ");
                    Long articleId = Long.parseLong(scanner.nextLine());
                    NewsArticle article = newsService.readFullArticle(articleId, UserContext.getUserId());
                    showFullArticle(article);
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void showFullArticle(NewsArticle article) {
        if (article == null) {
            System.out.println("Article could not be loaded.");
            return;
        }

        System.out.println("\n==================== FULL ARTICLE ====================");
        System.out.println("Title: " + article.getTitle());
        System.out.println(article.getContent());
        System.out.println("Source: " + article.getSource());
        System.out.println("URL: " + article.getUrl());
        System.out.println("Category: " + article.getCategoryNames());
        System.out.println("======================================================");

        while (true) {
            System.out.println("\n1. Back");
            System.out.println("2. Logout");
            System.out.print("Choice: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1" -> {
                    return;
                }
                case "2" -> {
                    System.out.println("Logging out...");
                    System.exit(0);
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void showSavedArticlesMenu() {
        while (true) {
            List<NewsArticle> saved = newsService.getSavedArticles(UserContext.getUserId());

            String username = UserContext.getUsername();
            System.out.printf("\nWelcome to the News Application, %s! Date: %s Time:%s\n",
                    username,
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy")),
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("h:mma")));

            System.out.println("S A V E D");
            System.out.println("__________________________________________________");
            for (NewsArticle article : saved) {
                System.out.println("Article Id: " + article.getId());
                System.out.println(article.getTitle());
                System.out.println(article.getContent());
                System.out.println("Source: " + article.getSource());
                System.out.println("URL: " + article.getUrl());
                System.out.println("Category: " + article.getCategoryNames());
                System.out.println("__________________________________________________");
            }

            System.out.println("1. Back");
            System.out.println("2. Logout");
            System.out.println("3. Unsave Article");
            System.out.print("Choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> {
                    return;
                }
                case "2" -> {
                    System.out.println("Logging out...");
                    System.exit(0);
                }
                case "3" -> {
                    System.out.print("Enter Article ID to unsave: ");
                    long articleId = Long.parseLong(scanner.nextLine());
                    newsService.unsaveArticle(UserContext.getUserId(), articleId);
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void showCategoryMenu(LocalDateTime start, LocalDateTime end) {
        outerloop:
        while (true) {
            System.out.printf("\nWelcome to the News Application, %s! Date: %s Time:%s\n",
                    UserContext.getUsername(),
                    LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy")),
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("h:mma")));

            List<String> categories = newsService.getVisibleCategories();

            System.out.println("Please choose the options below for Headlines");
            for (int i = 0; i < categories.size(); i++) {
                System.out.println((i + 1) + ". " + categories.get(i));
            }
            System.out.println((categories.size() + 1) + ". Back");
            System.out.print("Choice: ");

            int selected;

            try {
                selected = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            if (selected == categories.size() + 1) {
                return;
            }

            if (selected < 1 || selected > categories.size()) {
                System.out.println("Invalid choice.");
                continue;
            }

            String category = categories.get(selected - 1); // dynamic category

            newsService.getNewsByDateRangeAndCategory(start, end, category);

            while (true) {
                System.out.println("\n____________________________________________________");
                System.out.println("1. Read Full Article");
                System.out.println("2. Save Article");
                System.out.println("3. Back");
                System.out.println("4. Logout");
                System.out.println("____________________________________________________");
                System.out.print("Enter your option: ");
                String option = scanner.nextLine();

                switch (option) {
                    case "1" -> {
                        System.out.print("Enter Article ID to read: ");
                        Long articleId = Long.parseLong(scanner.nextLine());
                        showFullArticle(newsService.readFullArticle(articleId, UserContext.getUserId()));
                    }
                    case "2" -> {
                        System.out.print("Enter Article ID to save: ");
                        Long articleId = Long.parseLong(scanner.nextLine());
                        newsService.saveArticle(articleId, UserContext.getUserId());
                    }
                    case "3" -> {
                        continue outerloop;
                    }
                    case "4" -> {
                        System.out.println("Logging out...");
                        System.exit(0);
                    }
                    default -> System.out.println("Invalid option.");
                }
            }
        }
    }

    private void showSearchMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a search keyword: ");
        String keyword = scanner.nextLine().trim();

        List<NewsArticle> results = newsService.searchArticles(keyword);

        if (results.isEmpty()) {
            System.out.println("No articles found for: " + keyword);
            return;
        }

        while (true) {
            System.out.println("\nS E A R C H   R E S U L T S");
            System.out.println("_____________________________________________________");

            for (NewsArticle article : results) {
                System.out.println("Article Id: " + article.getId());
                System.out.println(article.getTitle());
                System.out.println(article.getContent());
                System.out.println("source: " + article.getSource());
                System.out.println("URL: " + article.getUrl());
                System.out.println("Category: " + article.getCategoryNames());
                System.out.println("_____________________________________________________");
            }

            System.out.println("1. Read Full Article");
            System.out.println("2. Save Article");
            System.out.println("3. Back");
            System.out.println("4. Logout");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> {
                    System.out.print("Enter Article ID to read: ");
                    long articleId = Long.parseLong(scanner.nextLine());
                    showFullArticle(newsService.readFullArticle(articleId, UserContext.getUserId()));
                }
                case "2" -> {
                    System.out.print("Enter Article ID to save: ");
                    long articleId = Long.parseLong(scanner.nextLine());
                    newsService.saveArticle(articleId, UserContext.getUserId());
                }
                case "3" -> {
                    System.out.println("Returning to previous menu...");
                    return;
                }
                case "4" -> {
                    System.out.println("Logging out...");
                    System.exit(0);
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void showNotificationMenu() {
        while (true) {
            System.out.printf("\nWelcome to the News Application, %s! Date: %s Time:%s\n",
                    UserContext.getUsername(),
                    LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy")),
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("h:mma")));

            System.out.println("NOTIFICATIONS");
            System.out.println("1. View Notifications");
            System.out.println("2. Configure Notifications");
            System.out.println("3. Back");
            System.out.println("4. Logout");
            System.out.print("Choice: ");

            String input = scanner.nextLine();
            switch (input) {
                case "1" -> viewNotificationHistory();
                case "2" -> configureNotifications();
                case "3" -> { return; }
                case "4" -> {
                    System.out.println("Logging out...");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void viewNotificationHistory() {
        List<Notification> records = notificationService.getNotificationHistory(UserContext.getUserId());

        if (records.isEmpty()) {
            System.out.println("No notifications found.");
            return;
        }

        System.out.println("\n================== NOTIFICATIONS ==================");
        for (Notification record : records) {
            System.out.println("Title: " + record.getTitle());
            System.out.println("Source: " + record.getSource());
            System.out.println("Categories: " + record.getCategories());
            System.out.println("Received At: " + record.getNotifiedAt());
            System.out.println("URL: " + record.getUrl());
            System.out.println("---------------------------------------------------");
        }

        System.out.println("Press Enter to go back...");
        scanner.nextLine();
    }


    private void configureNotifications() {
    }

}
