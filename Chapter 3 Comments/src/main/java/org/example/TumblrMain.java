package org.example;

import org.json.JSONObject;
import java.util.Scanner;

import static org.example.constants.Messages.*;

public class TumblrMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print(INPUT_BLOG_NAME);
        String blogName = scanner.nextLine();

        System.out.print(INPUT_BLOG_RANGE);
        String range = scanner.nextLine();

        String[] parts = range.split("-");
        int start = Integer.parseInt(parts[0]);
        int end = Integer.parseInt(parts[1]);

        if (start > end || start < 0) {
            System.out.println(INVALID_INPUT);
            return;
        }

        int num = end - start + 1;

        JSONObject blogData = TumblrAPIClient.fetchBlogData(blogName, start, num);

        TumblrService service = new TumblrService();
        service.displayBlogInfo(blogData);
        service.displayImageLinks(blogData, start);

        scanner.close();
    }
}