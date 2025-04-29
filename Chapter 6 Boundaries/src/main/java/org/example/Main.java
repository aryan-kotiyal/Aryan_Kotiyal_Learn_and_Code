package org.example;

import org.example.model.GeoLocation;
import org.example.service.GeocodeService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GeocodeService geocodeService = new GeocodeService();

        System.out.print("Enter place name: ");
        String place = scanner.nextLine();

        try {
            GeoLocation location = geocodeService.getLocationFromPlace(place);
            System.out.println(location);
        } catch (Exception e) {
            System.err.println("Error occurred: " + e.getMessage());
        }

        scanner.close();
    }
}
