package org.logging;

public class App {
    public static void main(String[] args) {
        Logger logger1 = Logger.getInstance();
        logger1.logInfo("Application started.");

        Logger logger2 = Logger.getInstance();
        logger2.logDebug("Debugging module loaded.");

        Logger logger3 = Logger.getInstance();
        logger3.logError("An error occurred.");

        System.out.println("Logger1 == Logger2? " + (logger1 == logger2));
        System.out.println("Logger2 == Logger3? " + (logger2 == logger3));
    }
}