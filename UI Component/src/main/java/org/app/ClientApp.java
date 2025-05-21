package org.app;

import component.Button;
import component.Checkbox;
import component.TextField;
import factory.MacOSUIFactory;
import factory.UIComponentFactory;
import factory.WindowsUIFactory;

import java.util.Scanner;

public class ClientApp {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your OS type (windows/mac): ");
        String osType = scanner.nextLine().trim().toLowerCase();

        UIComponentFactory factory;

        switch (osType) {
            case "windows":
                factory = new WindowsUIFactory();
                break;
            case "mac":
                factory = new MacOSUIFactory();
                break;
            default:
                System.out.println("Unsupported OS type. Please enter 'windows' or 'mac'.");
                return;
        }

        Button button = factory.createButton();
        Checkbox checkbox = factory.createCheckbox();
        TextField textField = factory.createTextField();

        System.out.println("\n--- Rendering UI Components ---");
        button.render();
        checkbox.render();
        textField.render();
    }
}