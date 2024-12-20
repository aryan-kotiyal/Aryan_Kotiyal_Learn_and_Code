package org.example;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.List;
import java.util.Scanner;

public class Country {

    public static void main(String[] args) {
        String countryCodesPath = "D:\\Aryan_Kotiyal_Learn_and_Code\\assignment1\\src\\main\\resources\\CountryCodes.json";
        String adjacentCountriesPath = "D:\\Aryan_Kotiyal_Learn_and_Code\\assignment1\\src\\main\\resources\\AdjacentCountries.json";

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a country code:");
        String countryCode = scanner.nextLine().toUpperCase();

        try {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, String> countryCodes = mapper.readValue(new File(countryCodesPath), Map.class);
            Map<String, List<String>> adjacentCountries = mapper.readValue(new File(adjacentCountriesPath), Map.class);

            if (countryCodes.containsKey(countryCode)) {
                String countryName = countryCodes.get(countryCode);
                List<String> adjacentCountryList = adjacentCountries.get(countryName);

                if (adjacentCountryList != null && !adjacentCountryList.isEmpty()) {
                    System.out.println("Country: " + countryName);
                    System.out.println("Adjacent Countries: " + String.join(", ", adjacentCountryList));
                } else {
                    System.out.println("No adjacent countries found for " + countryName + ".");
                }
            } else {
                System.out.println("Invalid country code.");
            }
        } catch (IOException e) {
            System.err.println("Error reading JSON files: " + e.getMessage());
        }
    }
}