package org.example;

import org.example.config.Messages;
import org.example.model.GeoLocation;
import org.example.service.GeocodeService;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GeocodeService geocodeService = new GeocodeService();

        System.out.print(Messages.ENTER_PLACE);
        String place = scanner.nextLine();

        try {
            GeoLocation location = geocodeService.getLocationFromPlace(place);
            System.out.println(location);
        } catch (IOException e) {
            System.err.println(Messages.ERROR_OCCURRED + Messages.IO_ERROR + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println(Messages.ERROR_OCCURRED + Messages.INVALID_INPUT_ERROR + e.getMessage());
        } catch (RuntimeException e) {
            System.err.println(Messages.ERROR_OCCURRED + Messages.RUNTIME_ERROR + e.getMessage());
        }

        scanner.close();
    }
}
